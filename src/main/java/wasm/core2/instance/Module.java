package wasm.core2.instance;

import wasm.core3.model.index.*;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;
import wasm.core3.structure.Function;
import wasm.core2.instruction.Action;
import wasm.core2.instruction.Expression;
import wasm.core2.instruction.Instruction;
import wasm.core2.model.describe.ExportDescribe;
import wasm.core2.model.section.*;
import wasm.core2.structure.*;
import wasm.core.util.NumberUtil;
import wasm.instruction2.dump.DumpMemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Module implements ModuleInstance {

    private final ModuleInfo moduleInfo;                            // 模块信息

    private final ArrayList<Memory> memories = new ArrayList<>();   // 内存实例
    private final ArrayList<Global> globals = new ArrayList<>();    // 存放全局变量
    private final ArrayList<Function> functions = new ArrayList<>();// 整个模块的函数集合
    private final ArrayList<Table> tables = new ArrayList<>();      // 表

    private final OperandStack operandStack = new OperandStack();   // 操作数栈
    private final ControlStack controlStack = new ControlStack();   // 控制块栈
    protected int frameOffset;                                      // 如果是函数调用，记录函数可用部分的起始位置

    private Map<String, Object> EXPORTS = null;                     // 导出内容缓存

    private Module(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    @Override
    public Object getMember(String name) {
        if (null != EXPORTS) { return EXPORTS.get(name); }

        EXPORTS = new HashMap<>();
        for (int i = 0; i < moduleInfo.exportSections.length; i++) {
            ExportSection exportSection = moduleInfo.exportSections[i];

            if (EXPORTS.containsKey(exportSection.name)) {
                throw new RuntimeException("already exist item: " + exportSection.name);
            }

            ExportDescribe d = exportSection.describe;

            switch (d.tag.value()) {
                case 0x00: // FUNCTION
                    EXPORTS.put(exportSection.name, functions.get(d.index.intValue())); break;
                case 0x01: // TABLE
                    EXPORTS.put(exportSection.name, tables.get(d.index.intValue())); break;
                case 0x02: // MEMORY
                    EXPORTS.put(exportSection.name, memories.get(d.index.intValue())); break;
                case 0x03: // GLOBAL
                    EXPORTS.put(exportSection.name, globals.get(d.index.intValue())); break;
                default:
                    throw new RuntimeException("what a tag: " + d.tag);
            }

        }

        return getMember(name);
    }

    @Override
    public U64[] invoke(String name, U64... args) {
        Object member = getMember(name);
        if (member instanceof Function) {
            Function f = (Function) member;

            if (args.length != f.type().parameters.length) {
                throw new RuntimeException("args is mismatch.");
            }

            // 启动一个新的函数，清除栈
            this.clearOperandStack();
            this.clearControlStack();

            return f.call(args);
        }
        throw new RuntimeException("can not find function: " + name);
    }

    @Override
    public void clearOperandStack() {
        operandStack.clear();
    }

    @Override
    public void pushU64(U64 value) {
        operandStack.pushU64(value);
    }

    @Override
    public void pushS64(long value) {
        operandStack.pushS64(value);
    }

    @Override
    public void pushU32U(U32 value) {
        operandStack.pushU32U(value);
    }

    @Override
    public void pushU32S(U32 value) {
        operandStack.pushU32S(value);
    }

    @Override
    public void pushS32(int value) {
        operandStack.pushS32(value);
    }

    @Override
    public void pushBool(boolean value) {
        operandStack.pushBool(value);
    }

    @Override
    public void pushU64s(U64[] values) {
        operandStack.pushU64s(values);
    }

    @Override
    public U64 popU64() {
        return operandStack.popU64();
    }

    @Override
    public long popS64() {
        return operandStack.popS64();
    }

    @Override
    public U32 popU32() {
        return operandStack.popU32();
    }

    @Override
    public int popS32() {
        return operandStack.popS32();
    }

    @Override
    public boolean popBool() {
        return operandStack.popBool();
    }

    @Override
    public U64[] popU64s(int size) {
        return operandStack.popU64s(size);
    }

    @Override
    public U64 getOperand(int index) {
        return operandStack.getOperand(index);
    }

    @Override
    public void setOperand(int index, U64 value) {
        operandStack.setOperand(index, value);
    }

    @Override
    public int getFrameOffset() {
        return frameOffset;
    }

    @Override
    public void clearControlStack() {
        controlStack.clear();
    }

    @Override
    public ControlFrame popFrame() {
        return controlStack.pop();
    }

    @Override
    public ControlFrame topFrame() {
        return controlStack.top();
    }

    @Override
    public ControlFrame topCallFrame(int[] index) {
        return controlStack.topCallFrame(index);
    }

    @Override
    public Memory getMemory(MemoryIndex index) {
        return memories.get(index.intValue());
    }

    @Override
    public void setMemory(MemoryIndex index, Memory value) {
        while (memories.size() <= index.intValue()) { memories.add(null); }
        memories.set(index.intValue(), value);
    }

    @Override
    public void write(MemoryIndex index, U64 offset, byte[] data) {
        memories.get(index.intValue()).write(offset, data);
    }

    @Override
    public void read(MemoryIndex index, U64 offset, byte[] buffer) {
        memories.get(index.intValue()).read(offset, buffer);
    }


    private U64 getOffset(DumpMemory args) {
        U32 offset = args.getOffset();
        U32 immediate = popU32();
        return NumberUtil.add(U64.valueOfU(offset), U64.valueOfU(immediate));
    }

    @Override
    public void writeBytes(MemoryIndex index, DumpMemory args, byte[] data) {
        U64 offset = getOffset(args);
        this.memories.get(index.intValue()).write(offset, data);
    }

    @Override
    public byte[] readBytes(MemoryIndex index, DumpMemory args, int size) {
        byte[] bytes = new byte[size];
        U64 offset = getOffset(args);
        this.memories.get(index.intValue()).read(offset, bytes);
        return bytes;
    }

    @Override
    public U32 memorySize(MemoryIndex index) {
        return memories.get(index.intValue()).size();
    }

    @Override
    public U32 memoryGrow(MemoryIndex index, U32 grow) {
        return memories.get(index.intValue()).grow(grow);
    }


    @Override
    public Global getGlobal(GlobalIndex index) {
        return globals.get(index.intValue());
    }

    @Override
    public void setGlobal(GlobalIndex index, Global value) {
        while (globals.size() <= index.intValue()) { globals.add(null); }
        globals.set(index.intValue(), value);
    }

    @Override
    public Function getFunction(FunctionIndex index) {
        return functions.get(index.intValue());
    }

    @Override
    public void setFunction(FunctionIndex index, Function function) {
        while (functions.size() <= index.intValue()) { functions.add(null); }
        functions.set(index.intValue(), function);
    }

    @Override
    public Table getTable(TableIndex index) {
        return tables.get(index.intValue());
    }

    @Override
    public void setTable(TableIndex index, Table table) {
        while (tables.size() <= index.intValue()) { tables.add(null); }
        tables.set(index.intValue(), table);
    }

    @Override
    public void loop() {
        int depth = controlStack.depth();
        while (controlStack.depth() >= depth) {
            ControlFrame frame = controlStack.top();
            if (frame.pc == frame.expression.length()) {
                exitBlock();
            } else {
                Action action = frame.expression.get(frame.pc);
                frame.pc++;
                executeAction(action);
            }
        }
    }

    @Override
    public void executeExpression(Expression expression) {
        for (Action action : expression) {
            executeAction(action);
        }
    }

    @Override
    public void executeAction(Action action) {
        System.out.println(action.getInstruction().name + " " + (null == action.getArgs() ? "" : action.getArgs().dump()));
        action.getInstruction().operate(this, action.getArgs());
    }

    @Override
    public void enterBlock(Instruction instruction, FunctionType type, Expression expression) {
        int bp = operandStack.size() - type.parameters.length; // 除去参数剩下的栈的长度
        ControlFrame frame = new ControlFrame(bp, instruction, type, expression);
        controlStack.push(frame);
        if (instruction == Instruction.CALL) {
            frameOffset = bp;
        }
    }

    @Override
    public void exitBlock() {
        ControlFrame frame = controlStack.pop();
        clearBlock(frame);
    }

    private void clearBlock(ControlFrame frame) {
        // 取出结果
        U64[] results = popU64s(frame.functionType.results.length);
        // 弹出参数
        popU64s(operandStack.size() - frame.bp);
        // 装入结果
        pushU64s(results);
        if (frame.instruction == Instruction.CALL && controlStack.depth() > 0) {
            ControlFrame callFrame = controlStack.topCallFrame(new int[1]);
            frameOffset = callFrame.bp;
        }
    }

    @Override
    public void resetBlock(ControlFrame frame) {
        U64[] results = popU64s(frame.functionType.parameters.length);
        popU64s(operandStack.size() - frame.bp);
        pushU64s(results);
    }


    @Override
    public ModuleInfo getModuleInfo() {
        return moduleInfo;
    }


    @Override
    public void linkImports() {
        for (int i = 0; i < moduleInfo.importSections.length; i++) {
            ImportSection importSection = moduleInfo.importSections[i];

            ModuleInstance instance = ModuleInstance.MODULES.get(importSection.module);

            if (null == instance) {
                throw new RuntimeException("module instance: " + importSection.module + " is not exist");
            }

            Object member = instance.getMember(importSection.name);

            if (null == member) {
                throw new RuntimeException("can not find export member: " + importSection.name + " in module instance:" + importSection.module);
            }

            if (member instanceof Function) {
                this.setFunction(FunctionIndex.of(i), (Function) member);
            } else if (member instanceof Table) {
                this.setTable(TableIndex.of(i), (Table) member);
            } else if (member instanceof Memory) {
                this.setMemory(MemoryIndex.of(i), (Memory) member);
            } else if (member instanceof Global) {
                this.setGlobal(GlobalIndex.of(i), (Global) member);
            } else {
                throw new RuntimeException("what a member: " + member);
            }
        }
    }

    @Override
    public void initFunctions() {
        for (int i = 0; i < moduleInfo.functionSections.length; i++) {
            TypeIndex index = moduleInfo.functionSections[i];
            FunctionType type = moduleInfo.typeSections[index.intValue()];
            CodeSection codeSection = moduleInfo.codeSections[i];
            this.functions.add(new FunctionInstance(type, codeSection, this));
        }
    }

    @Override
    public void initTables() {
        for (int i = 0; i < moduleInfo.tableSections.length; i++) {
            this.tables.add(new TableInstance(moduleInfo.tableSections[i]));
        }
        for (int i = 0; i < moduleInfo.elementSections.length; i++) {
            ElementSection elementSection = moduleInfo.elementSections[i];
            if (elementSection.value.isActive()) {
                elementSection.value.init(this);
            }
        }
    }

    @Override
    public void initMemories() {
        for (int i = 0; i < moduleInfo.memorySections.length; i++) {
            this.memories.add(new MemoryInstance(moduleInfo.memorySections[i]));
        }
        for (DataSection d : moduleInfo.dataSections) {
            d.value.initMemory(this);
        }
    }

    @Override
    public void initGlobals() {
        for (int i = 0; i < moduleInfo.globalSections.length; i++) {
            // 执行初始化指令
            executeExpression(moduleInfo.globalSections[i].init);
            // 将执行结果存到对应位置
            this.globals.add(new GlobalInstance(moduleInfo.globalSections[i].type, popU64()));
        }
    }

    @Override
    public void execStartFunction() {
        if (null != moduleInfo.startFunctionIndex) {
            getFunction(moduleInfo.startFunctionIndex).call();
        }
    }


    public static ModuleInstance newModule(ModuleInfo info) {
        Module module = new Module(info);

        module.linkImports();

        module.initFunctions();
        module.initTables();
        module.initMemories();
        module.initGlobals();

        System.out.println("========================= ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ =========================");
        System.out.print(info.dump());
        System.out.println("========================= ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ =========================");

        module.execStartFunction();

        System.out.println();

        return module;
    }

}
