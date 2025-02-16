package gay.vereena.cclj;

import gay.vereena.cclj.computer.LuaJITMachine;import net.fabricmc.api.ModInitializer;

import dan200.computercraft.shared.computer.core.ServerContext;


public class CCLuaJIT implements ModInitializer {
	public static final String MOD_ID = "ccluajit";

	@Override
	public void onInitialize() {
        ServerContext.luaMachine = LuaJITMachine::new;
	}
}