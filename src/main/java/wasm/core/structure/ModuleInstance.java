package wasm.core.structure;

import wasm.core.numeric.*;
import wasm.core.instruction.Action;
import wasm.core.instruction.Expression;
import wasm.core.instruction.Instruction;
import wasm.core.model.section.FunctionType;
import wasm.core.model.index.FunctionIndex;
import wasm.core.model.index.GlobalIndex;
import wasm.core.model.index.MemoryIndex;
import wasm.core.model.index.TableIndex;
import wasm.core.instruction.dump.DumpMemory;

import java.util.HashMap;
import java.util.Map;

/**
 * 每一个模块都必须对外提供导出类型
 */
public interface ModuleInstance {

    Map<String, ModuleInstance> MODULES = new HashMap<>();

    // =================== 模块操作 ===================


    /**
     * 取出指定名称的导出内容
     */
    Object getMember(String name);

    /**
     * 直接调用导出的函数
     */
    USize[] invoke(String name, USize... args);


    // =================== 操作数栈操作 ===================

    /**
     * 清除栈上所有值
     */
    void clearOperandStack();

    /** 向栈上推入值 */
    void pushUSize(USize value);
    void pushU8(U8 value);
    void pushU16(U16 value);
    void pushU32(U32 value);
    void pushU64(U64 value);
    void pushBool(boolean value);
    void pushS32(int value);
    void pushS64(long value);
    void pushUSizes(USize[] values);

    /** 弹出值 */
    USize popUSize();
    U8 popU8();
    U16 popU16();
    U32 popU32();
    U64 popU64();
    boolean popBool();
    int popS32();
    long popS64();
    USize[] popUSizes(int size);

    /**
     * 获取操作数栈某个值
     */
    <T extends USize> T getOperand(int index, Class<T> c);
    /**
     * 设置操作数栈某个值
     */
    void setOperand(int index, USize value);

    /**
     * 获取当前控制栈帧操作数起始位置，也就是第一个函数参数位置
     */
    int getFrameOffset();

    // =================== 控制栈操作 ===================

    /**
     * 清除栈上所有值
     */
    void clearControlStack();

    /**
     * 弹出顶部帧
     */
    ControlFrame popFrame();

    /**
     * 顶部帧
     */
    ControlFrame topFrame();

    /**
     * 顶部函数帧 index是计数
     */
    ControlFrame topCallFrame(int[] index);

    // =================== 内存操作 ===================

    /**
     * 取出全局变量
     */
    Memory getMemory(MemoryIndex index);

    /**
     * 设置全局变量
     */
    void setMemory(MemoryIndex index, Memory value);

    /**
     * 写入内存
     */
    void write(MemoryIndex index, U64 offset, byte[] data);

    /**
     * 读取内存
     */
    void read(MemoryIndex index, U64 offset, byte[] buffer);

    /**
     * 写入内存
     */
    void writeBytes(MemoryIndex index, DumpMemory args, byte[] data);

    /**
     * 读取内存
     */
    byte[] readBytes(MemoryIndex index, DumpMemory args, int size);

    /**
     * 内存页大小
     */
    U32 memorySize(MemoryIndex index);

    /**
     * 内存扩页
     */
    U32 memoryGrow(MemoryIndex index, U32 grow);

    // =================== 全局变量操作 ===================

    /**
     * 取出全局变量
     */
    Global getGlobal(GlobalIndex index);

    /**
     * 设置全局变量
     */
    void setGlobal(GlobalIndex index, Global value);

    // =================== 函数集合操作 ===================

    /**
     * 取出函数集合
     */
    Function getFunction(FunctionIndex index);

    /**
     * 设置函数
     */
    void setFunction(FunctionIndex index, Function function);


    // =================== 表操作 ===================

    /**
     * 取出表
     */
    Table getTable(TableIndex index);

    /**
     * 设置表
     */
    void setTable(TableIndex index, Table table);


    // =================== 代码操作 ===================

    /**
     * 循环执行函数帧代码
     */
    void loop();

    /**
     * 执行表达式
     */
    void executeExpression(Expression expression);

    /**
     * 执行单个指令
     */
    void executeAction(Action action);

    /**
     * 进入块帧
     */
    void enterBlock(Instruction instruction, FunctionType type, Expression expression);

    /**
     * 退出块帧
     */
    void exitBlock();

    /**
     * 重置块帧
     */
    void resetBlock(ControlFrame frame);

    // =================== 模块信息 ===================

    /**
     * 获取模块信息
     */
    ModuleInfo getModuleInfo();



    // =================== 模块初始化 ===================

    /**
     * 链接导入模块
     */
    void linkImports();

    /**
     * 初始化函数集合
     */
    void initFunctions();

    /**
     * 初始化表
     */
    void initTables();

    /**
     * 初始化内存
     */
    void initMemories();

    /**
     * 初始化全局变量
     */
    void initGlobals();

    /**
     * 执行启动函数
     */
    void execStartFunction();

}
