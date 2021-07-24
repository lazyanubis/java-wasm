package wasm.core.instance;

import wasm.core.instruction.Expression;
import wasm.core.instruction.Instruction;
import wasm.core.model.index.FunctionIndex;
import wasm.core.model.index.GlobalIndex;
import wasm.core.model.index.MemoryIndex;
import wasm.core.model.index.TableIndex;
import wasm.core.model.section.FunctionType;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;
import wasm.core.structure.*;
import wasm.instruction2.dump.DumpMemory;

public class Module implements ModuleInstance {

    private final ModuleInfo moduleInfo;                            // 模块信息
    private Memory[] memories;                                      // 内存实例
    private Global[] globals;                                       // 存放全局变量
    private FunctionInstance[] functions;                           // 整个模块的函数集合
    private Table[] tables;                                         // 表

    private final OperandStack operandStack = new OperandStack();   // 操作数栈
    private final ControlStack controlStack = new ControlStack();   // 控制块栈
    private U32 local0Index;                                        // 如果是函数调用，记录函数可用部分的起始位置

    public Module(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    @Override
    public Object getMember(String name) {
        return null;
    }

    @Override
    public U64[] invoke(String name, U64... args) {
        return new U64[0];
    }

    @Override
    public void clearOperandStack() {

    }

    @Override
    public void pushU64(U64 value) {

    }

    @Override
    public void pushS64(long value) {

    }

    @Override
    public void pushU32(U32 value) {

    }

    @Override
    public void pushS32(int value) {

    }

    @Override
    public void pushBool(boolean value) {

    }

    @Override
    public void pushU64s(U64[] values) {

    }

    @Override
    public U64 popU64() {
        return null;
    }

    @Override
    public long popS64() {
        return 0;
    }

    @Override
    public U32 popU32() {
        return null;
    }

    @Override
    public int popS32() {
        return 0;
    }

    @Override
    public boolean popBool() {
        return false;
    }

    @Override
    public U64[] popU64s(int size) {
        return new U64[0];
    }

    @Override
    public U64 getOperand(int index) {
        return null;
    }

    @Override
    public void setOperand(int index, U64 value) {

    }

    @Override
    public int getFrameOffset() {
        return 0;
    }

    @Override
    public ControlFrame popFrame() {
        return null;
    }

    @Override
    public ControlFrame topFrame() {
        return null;
    }

    @Override
    public ControlFrame topCallFrame(int[] index) {
        return null;
    }

    @Override
    public void write(MemoryIndex index, U64 offset, byte[] data) {

    }

    @Override
    public void read(MemoryIndex index, U64 offset, byte[] buffer) {

    }

    @Override
    public void writeBytes(MemoryIndex index, DumpMemory args, byte[] data) {

    }

    @Override
    public byte[] readBytes(MemoryIndex index, DumpMemory args, int size) {
        return new byte[0];
    }

    @Override
    public U32 size(MemoryIndex index) {
        return null;
    }

    @Override
    public U32 grow(MemoryIndex index, U32 grow) {
        return null;
    }

    @Override
    public U64 getGlobal(String name) {
        return null;
    }

    @Override
    public void setGlobal(String name, U64 value) {

    }

    @Override
    public U64 getGlobal(GlobalIndex index) {
        return null;
    }

    @Override
    public void setGlobal(GlobalIndex index, U64 value) {

    }

    @Override
    public Function getFunction(FunctionIndex index) {
        return null;
    }

    @Override
    public void setFunction(FunctionIndex index, Function function) {

    }

    @Override
    public Table getTable(TableIndex index) {
        return null;
    }

    @Override
    public void setTable(TableIndex index, Table table) {

    }

    @Override
    public void loop() {

    }

    @Override
    public void executeExpression(Expression expression) {

    }

    @Override
    public void enterBlock(Instruction instruction, FunctionType type, Expression expression) {

    }

    @Override
    public void exitBlock() {

    }

    @Override
    public void resetBlock(ControlFrame frame) {

    }

    @Override
    public ModuleInfo getModuleInfo() {
        return null;
    }


//    public void loop() {
//        int depth = controlStack.depth();
//        while (controlStack.depth() >= depth) {
//            ControlFrame frame = controlStack.top();
//            if (frame.pc == frame.expression.length()) {
//                exitBlock();
//            } else {
//                Action action = frame.expression.get(frame.pc);
//                frame.pc++;
//                executeExpression(action);
//            }
//        }
//    }
//
//    public void executeExpression(Expression expression) {
//        for (Action action : expression) {
//            executeExpression(action);
//        }
//    }
//
//    private void executeExpression(Action action) {
//        action.getInstruction().operate(this, action.getArgs());
//    }
//
//    public void enterBlock(Instruction instruction, FunctionType functionType, Expression expression) {
//        int bp = operandStack.stackSize() - functionType.parameters.length; // 除去参数剩下的栈的长度
//        ControlFrame frame = new ControlFrame(bp, instruction, functionType, expression);
//        controlStack.push(frame);
//        if (instruction == Instruction.CALL) {
//            local0Index = new U32(bp);
//        }
//    }
//
//    public void exitBlock() {
//        ControlFrame frame = controlStack.pop();
//        clearBlock(frame);
//    }
//
//    private void clearBlock(ControlFrame frame) {
//        // 取出结果
//        U64[] results = popU64s(frame.functionType.results.length);
//        // 弹出参数
//        popU64s(operandStack.stackSize() - frame.bp);
//        // 装入结果
//        pushU64s(results);
//        if (frame.instruction == Instruction.CALL && controlStack.depth() > 0) {
//            ControlFrame callFrame = controlStack.topCallFrame(new int[1]);
//            local0Index = new U32(callFrame.bp);
//        }
//    }
//
//    public void resetBlock(ControlFrame frame) {
//        U64[] results = popU64s(frame.functionType.parameters.length);
//        popU64s(operandStack.stackSize() - frame.bp);
//        pushU64s(results);
//    }
//
//    private void initMemory() {
//        this.memories = new MemoryInstance[moduleInfo.memorySections.length];
//        for (int i = 0; i < this.memories.length; i++) {
//            this.memories[i] = new MemoryInstance(moduleInfo.memorySections[i]);
//        }
//        for (DataSection d : moduleInfo.dataSections) {
//            d.value.initMemory(this);
//        }
//    }
//
//    private void initGlobals() {
//        globals = new GlobalInstance[moduleInfo.globalSections.length];
//        for (int i = 0; i < globals.length; i++) {
//            // 执行初始化指令
//            executeExpression(moduleInfo.globalSections[i].init);
//            // 将执行结果存到对应位置
//            globals[i] = new GlobalInstance(moduleInfo.globalSections[i].type, popU64());
//        }
//    }
//
//    private void initFunctions() {
//        this.functions = new FunctionInstance[moduleInfo.importSections.length + moduleInfo.functionSections.length];
//
//        this.linkNativeFunctions(); // 链接本地函数
//
//        for (int i = 0; i < moduleInfo.functionSections.length; i++) {
//            TypeIndex index = moduleInfo.functionSections[i];
//            FunctionType type = moduleInfo.typeSections[index.intValue()];
//            CodeSection codeSection = moduleInfo.codeSections[i];
//            this.functions[moduleInfo.importSections.length + i] = new FunctionInstance(type, codeSection);
//        }
//    }
//
//    private void linkNativeFunctions() {
//        for (int i = 0; i < moduleInfo.importSections.length; i++) {
//            ImportSection importSection = moduleInfo.importSections[i];
//
//            if (importSection.describe.tag.value() == 0x00) {
//                // 导入类型是函数 FUNCTION
//                if (importSection.module.equals("env")) {
//                    // 导入环境 本地函数
//                    FunctionType type = moduleInfo.typeSections[i];
//                    switch (importSection.name) {
//                        // env模块的哪个函数
//                        case "print_char": functions[i] = new FunctionInstance(type, NativeFunction.PRINT_CHAR); break;
//                        case "assert_true": functions[i] = new FunctionInstance(type, NativeFunction.ASSERT_TRUE); break;
//                        case "assert_false": functions[i] = new FunctionInstance(type, NativeFunction.ASSERT_FALSE); break;
//                        case "assert_eq_i32": functions[i] = new FunctionInstance(type, NativeFunction.ASSERT_EQUAL_INT); break;
//                        case "assert_eq_i64": functions[i] = new FunctionInstance(type, NativeFunction.ASSERT_EQUAL_LONG); break;
//                        default:
//                            throw new RuntimeException("what a function: " + importSection.name + " for module env.");
//                    }
//                } else {
//                    throw new RuntimeException("what a module: " + moduleInfo);
//                }
//            } else {
//                throw new RuntimeException("what a import tag: " + importSection.describe.tag.value());
//            }
//        }
//    }
//
//    private void initTable() {
//        tables = new TableInstance[moduleInfo.tableSections.length];
//        for (int i = 0; i < tables.length; i++) {
//            tables[i] = new TableInstance(moduleInfo.tableSections[i]);
//        }
//        for (int i = 0; i < moduleInfo.elementSections.length; i++) {
//            ElementSection elementSection = moduleInfo.elementSections[i];
//            if (elementSection.value.isActive()) {
//                elementSection.value.init(this);
//            }
//        }
//    }
//
//    public ModuleInfo getModule() {
//        return moduleInfo;
//    }
//
//    public Memory getMemory(int index) {
//        return memories[index];
//    }
//
//
//    public static void execStartFunction(ModuleInfo moduleInfo) {
//        System.out.println("========================= ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ =========================");
//        System.out.print(moduleInfo.dump());
//        System.out.println("========================= ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ =========================");
//
//        if (null == moduleInfo.startFunctionIndex) {
//            throw new RuntimeException("no start function");
//        }
//
//        ModuleInstance mi = new ModuleInstance(moduleInfo);
//        mi.initMemory();
//        mi.initGlobals();
//        mi.initFunctions();
//        mi.initTable();
//        Instruction.CALL.operate(mi, moduleInfo.startFunctionIndex); // 执行启动段指定的函数
//        mi.loop();
//
//        System.out.println();
//    }
//
//    private U64 getOffset(DumpMemory args) {
//        U32 offset = args.getOffset();
//        U32 immediate = popU32();
//        return NumberUtil.add(new U64(offset), new U64(immediate));
//    }
//
//    public byte[] readBytes(MemoryIndex index, DumpMemory args, int size) {
//        byte[] bytes = new byte[size];
//        U64 offset = getOffset(args);
//        this.memories[index.intValue()].read(offset, bytes);
//        return bytes;
//    }
//
//    public void writeBytes(MemoryIndex index, DumpMemory args, byte[] data) {
//        U64 offset = getOffset(args);
//        this.memories[index.intValue()].write(offset, data);
//    }

}
