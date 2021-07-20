package wasm.model.section.util;

public class Const {

    public static final int MAGIC_NUMBER = 0x6D736100; // \0asm 文件魔数
    public static final int VERSION = 0x00000001; // 1 文件版本

    public static final byte SECTION_CUSTOM_ID = 0x00;
    public static final byte SECTION_TYPE_ID = 0x01;
    public static final byte SECTION_IMPORT_ID = 0x02;
    public static final byte SECTION_FUNCTION_ID = 0x03;
    public static final byte SECTION_TABLE_ID = 0x04;
    public static final byte SECTION_MEMORY_ID = 0x05;
    public static final byte SECTION_GLOBAL_ID = 0x06;
    public static final byte SECTION_EXPORT_ID = 0x07;
    public static final byte SECTION_START_ID = 0x08;
    public static final byte SECTION_ELEMENT_ID = 0x09;
    public static final byte SECTION_CODE_ID = 0x0A;
    public static final byte SECTION_DATA_ID = 0x0B;

}
