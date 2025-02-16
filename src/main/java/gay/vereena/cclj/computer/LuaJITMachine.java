package gay.vereena.cclj.computer;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.Nullable;

import dan200.computercraft.core.lua.*;


public final class LuaJITMachine implements ILuaMachine {
    @SuppressWarnings("unused") // Used in native code.
    public static String decodeString(byte[] bytes) {
        return new String(bytes, StandardCharsets.US_ASCII);
    }

    static {
        System.load("/run/media/vereena/ldata/Projects/ccluajit/vendor/luajit/src/libluajit.so");
        System.load("/run/media/vereena/ldata/Projects/ccluajit/src/main/cpp/cclj.so");
        System.out.println("loaded natives");
    }

    private volatile boolean yieldRequested;

    private volatile boolean aborted;

    private volatile long luaState;
    private volatile long mainRoutine;

    public LuaJITMachine(MachineEnvironment env, InputStream biosIn) {

    }

    @Override
    public MachineResult handleEvent(@Nullable String s, @Nullable Object[] objects) {
        return null;
    }

    @Override
    public void printExecutionState(StringBuilder stringBuilder) {

    }

    @Override
    public void close() {

    }
}