package gay.vereena.cclj.asm;

import gay.vereena.cclj.asm.transformers.ComputerThreadTransformer;
import gay.vereena.cclj.asm.transformers.ComputerTransformer;
import gay.vereena.cclj.asm.transformers.PullEventScanner;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @TODO: Modify OSAPI queueEvent to prepend a sentinel object to arguments in case event filter is a special event?

public final class CCLJClassTransformer implements IClassTransformer {
    private final Map<String, ITransformer> transformers;
    private final List<ITransformer> transformersForAll;

    public CCLJClassTransformer() {
        this.transformers = new HashMap<>();
        this.transformersForAll = new ArrayList<>();

        this.addTransformer(new PullEventScanner());
        this.addTransformer(new ComputerTransformer());
        this.addTransformer(new ComputerThreadTransformer());
    }

    private void addTransformer(final ITransformer transformer) {
        final String clazz = transformer.clazz();

        if(clazz == null) {
            this.transformersForAll.add(transformer);
            return;
        }

        if(this.transformers.containsKey(clazz))
            throw new IllegalArgumentException("Transformer for " + clazz + " already registered!");

        this.transformers.put(clazz, transformer);
    }

    @Override
    public byte[] transform(final String name, final String transformedName, final byte[] basicClass) {
        if(basicClass == null) return null;

        try {
            final ClassNode cn = new ClassNode();
            final ClassReader cr = new ClassReader(basicClass);
            cr.accept(cn, 0);

            boolean transformed = this.transformersForAll.stream()
                    .map(t -> t.transform(cn))
                    .reduce(false, (a, b) -> a || b);

            if(this.transformers.containsKey(name)) {
                final ITransformer transformer = this.transformers.get(name);
                if(transformer.transform(cn)) {
                    transformed = true;
                }
            }

            if(transformed) {
                final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                cn.accept(cw);
                return cw.toByteArray();
            } else {
                return basicClass;
            }
        } catch(final Throwable t) {
            t.printStackTrace();
            return basicClass;
        }
    }
}