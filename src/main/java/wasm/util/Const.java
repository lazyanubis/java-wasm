package wasm.util;

public class Const {

    public static final int MAGIC_NUMBER = 0x6D736100; // \0asm 文件魔数
    public static final int VERSION = 0x00000001; // 1 文件版本

    public static final byte SECTION_CUSTOM_ID      = 0x00;
    public static final byte SECTION_TYPE_ID        = 0x01;
    public static final byte SECTION_IMPORT_ID      = 0x02;
    public static final byte SECTION_FUNCTION_ID    = 0x03;
    public static final byte SECTION_TABLE_ID       = 0x04;
    public static final byte SECTION_MEMORY_ID      = 0x05;
    public static final byte SECTION_GLOBAL_ID      = 0x06;
    public static final byte SECTION_EXPORT_ID      = 0x07;
    public static final byte SECTION_START_ID       = 0x08;
    public static final byte SECTION_ELEMENT_ID     = 0x09;
    public static final byte SECTION_CODE_ID        = 0x0A;
    public static final byte SECTION_DATA_ID        = 0x0B;
    public static final byte SECTION_DATA_COUNT_ID  = 0x0C;

    public static final byte EXPRESS_END = 0x0B;
    public static final byte EXPRESS_ELSE = 0x05; // 若是else也应该结束当前指令读取

    // 65536B 标准中每个内存页64KB  真正使用的时候，需要减小到1KB
    public static final int MEMORY_PAGE_SIZE = 1024 * 64;
    // 65536 标准最大页面数  真正使用的时候，可以增加为 1024 * 64 * 64 = 4194304
    public static final int MEMORY_MAX_PAGE_COUNT = 1024 * 64;

}
