package wasm.core.util;

/**
 * 一些需要用到的数字
 */
public class ConstNumber {

    public static final int MAGIC_NUMBER = 0x6D736100; // \0asm 文件魔数 固定4字节
    public static final int VERSION      = 0x00000001; // wasm 文件版本 目前为32位数字 1

    public static final byte SECTION_ID_CUSTOM      = 0x00; //  0 自定义段
    public static final byte SECTION_ID_TYPE        = 0x01; //  1 函数签名段
    public static final byte SECTION_ID_IMPORT      = 0x02; //  2 导入段
    public static final byte SECTION_ID_FUNCTION    = 0x03; //  3 函数计数段
    public static final byte SECTION_ID_TABLE       = 0x04; //  4 表
    public static final byte SECTION_ID_MEMORY      = 0x05; //  5 内存
    public static final byte SECTION_ID_GLOBAL      = 0x06; //  6 全局变量
    public static final byte SECTION_ID_EXPORT      = 0x07; //  7 导出段
    public static final byte SECTION_ID_START       = 0x08; //  8 启动函数序号
    public static final byte SECTION_ID_ELEMENT     = 0x09; //  9 元素段 表初始化用
    public static final byte SECTION_ID_DATA_COUNT  = 0x0C; // 12 数据长度 memory.init和data.drop指令参数必须小于该长度
    public static final byte SECTION_ID_CODE        = 0x0A; // 10 代码段 函数具体代码
    public static final byte SECTION_ID_DATA        = 0x0B; // 11 数据段 内存初始化

    public static final byte EXPRESSION_END  = 0x0B;    // 指令末尾必须以0B结尾
    public static final byte EXPRESSION_ELSE = 0x05;    // 对于if指令，第一段指令也可以由else结尾

    public static final int MEMORY_PAGE_SIZE      = 1024 * 64;  // 65536B 标准中每个内存页64KB
    public static final int MEMORY_MAX_PAGE_COUNT = 1024 * 64;  // 65536页 标准中最大页面数 最大4G内存

}
