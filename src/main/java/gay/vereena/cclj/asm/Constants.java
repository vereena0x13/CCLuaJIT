package gay.vereena.cclj.asm;

public final class Constants {
    // @TODO: better-ify this

    public static final String COMPUTER_CLASS = "dan200.computercraft.core.computer.Computer";
    public static final String COMPUTER_DESC = COMPUTER_CLASS.replace('.', '/');
    public static final String COMPUTERTHREAD_CLASS = "dan200.computercraft.core.computer.ComputerThread";
    public static final String ILUAMACHINE_DESC = "dan200/computercraft/core/lua/ILuaMachine";
    public static final String LUAJ_MACHINE_DESC = "dan200/computercraft/core/lua/LuaJLuaMachine";

    public static final String CCLJ_MACHINE_DESC = "gay/vereena/cclj/computer/LuaJITMachine";
    public static final String TASKSCHEDULER_DESC = "gay/vereena/cclj/computer/TaskScheduler";
    public static final String PULLEVENTSCANNER_DESC = "gay/vereena/cclj/asm/transformers/PullEventScanner";

    public static final String ILUAMACHINE_HANDLEEVENT_DESC = "(Ljava/lang/String;[Ljava/lang/Object;)V";

    public static final String ILUACONTEXT_DESC = "dan200/computercraft/api/lua/ILuaContext";
    public static final String PULLEVENT_DESC = "(Ljava/lang/String;)[Ljava/lang/Object;";

    private Constants() {
    }
}