package wasm.core;

import wasm.instruction.Expression;
import wasm.instruction.Expressions;
import wasm.instruction.Instruction;
import wasm.instruction.dump.DumpMemory;
import wasm.model.Data;
import wasm.model.FunctionType;
import wasm.model.index.MemoryIndex;
import wasm.model.number.U32;
import wasm.model.number.U64;
import wasm.util.NumberUtil;

public class VirtualMachine {

    public Module module;
    public OperandStack operandStack = new OperandStack();
    private Memory[] memories;
    public ControlStack controlStack = new ControlStack();
    public GlobalVariable[] globals; // 存放全局变量
    public U32 local0Index; // 如果是函数调用，记录函数可用部分的起始位置

    public VirtualMachine(Module module) {
        this.module = module;
    }

    public void loop() {
        int depth = controlStack.depth();
        while (controlStack.depth() >= depth) {
            ControlFrame frame = controlStack.top();
            if (frame.pc == frame.expressions.length()) {
                exitBlock();
            } else {
                Expression expression = frame.expressions.get(frame.pc);
                frame.pc++;
                executeExpression(expression);
            }
        }
    }

    public void executeExpressions(Expressions expressions) {
        for (Expression expression : expressions) {
            executeExpression(expression);
        }
    }

    private void executeExpression(Expression expression) {
        expression.getInstruction().operate(this, expression.getArgs());
    }

    public void enterBlock(Instruction instruction, FunctionType functionType, Expressions expressions) {
        int bp = operandStack.stackSize() - functionType.parameters.length; // 除去参数剩下的栈的长度
        ControlFrame frame = new ControlFrame(bp, instruction, functionType, expressions);
        controlStack.push(frame);
        if (instruction == Instruction.CALL) {
            local0Index = new U32(bp);
        }
    }

    public void exitBlock() {
        ControlFrame frame = controlStack.pop();
        clearBlock(frame);
    }

    private void clearBlock(ControlFrame frame) {
        // 取出结果
        U64[] results = operandStack.popU64s(frame.functionType.results.length);
        // 弹出参数
        operandStack.popU64s(operandStack.stackSize() - frame.bp);
        // 装入结果
        operandStack.pushU64s(results);
        if (frame.instruction == Instruction.CALL && controlStack.depth() > 0) {
            ControlFrame callFrame = controlStack.topCallFrame(new int[1]);
            local0Index = new U32(callFrame.bp);
        }
    }

    public void resetBlock(ControlFrame frame) {
        U64[] results = operandStack.popU64s(frame.functionType.parameters.length);
        operandStack.popU64s(operandStack.stackSize() - frame.bp);
        operandStack.pushU64s(results);
    }

    private void initMemory() {
        this.memories = new Memory[module.memorySections.length];
        for (int i = 0; i < this.memories.length; i++) {
            this.memories[i] = new Memory(module.memorySections[i]);
        }
        for (Data d : module.dataSections) {
            d.value.initMemory(this);
        }
    }

    private void initGlobals() {
        globals = new GlobalVariable[module.globalSections.length];
        for (int i = 0; i < globals.length; i++) {
            // 执行初始化指令
            executeExpressions(module.globalSections[i].init);
            // 将执行结果存到对应位置
            globals[i] = new GlobalVariable(module.globalSections[i].type, operandStack.popU64());
        }
    }

    public Module getModule() {
        return module;
    }

    public Memory getMemory(int index) {
        return memories[index];
    }


    public static void execStartFunction(Module module) {
        System.out.println("========================= ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ =========================");
        System.out.print(module.dump());
        System.out.println("========================= ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ =========================");

        if (null == module.startFunctionIndex) {
            throw new RuntimeException("no start function");
        }

        VirtualMachine vm = new VirtualMachine(module);
        vm.initMemory();
        vm.initGlobals();
        Instruction.CALL.operate(vm, module.startFunctionIndex); // 执行启动段指定的函数
        vm.loop();
    }

    private U64 getOffset(DumpMemory args) {
        U32 offset = args.getOffset();
        U32 immediate = operandStack.popU32();
        return NumberUtil.add(new U64(offset), new U64(immediate));
    }

    public byte[] readBytesFromMemory(MemoryIndex index, DumpMemory args, int size) {
        byte[] bytes = new byte[size];
        U64 offset = getOffset(args);
        this.memories[index.intValue()].read(offset.u32(), bytes);
        return bytes;
    }

    public void writeBytesToMemory(MemoryIndex index, DumpMemory args, byte[] data) {
        U64 offset = getOffset(args);
        this.memories[index.intValue()].write(offset.u32(), data);
    }

}
