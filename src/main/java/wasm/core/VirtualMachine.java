package wasm.core;

import wasm.instruction.Expression;
import wasm.instruction.Expressions;
import wasm.instruction.dump.DumpMemory;
import wasm.model.Code;
import wasm.model.Data;
import wasm.model.index.MemoryIndex;
import wasm.model.number.U32;
import wasm.model.number.U64;
import wasm.util.NumberUtil;

public class VirtualMachine {

    public Module module;
    public OperandStack operandStack = new OperandStack();
    private Memory[] memories;
    public ControlStack controlStack = new ControlStack();


    public VirtualMachine(Module module) {
        this.module = module;
    }

    public void executeCode(int index) {
        Code code = module.codeSections[index];
        executeExpressions(code.expressions);
    }

    public void executeExpressions(Expressions expressions) {
        for (Expression expression : expressions) {
            executeExpression(expression);
        }
    }

    private void executeExpression(Expression expression) {
        expression.getInstruction().operate(this, expression.getArgs());
    }

    public Module getModule() {
        return module;
    }

    public Memory getMemory(int index) {
        return memories[index];
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

    public static void execStartFunction(Module module) {
        int index = module.startFunctionIndex.intValue() - module.importSections.length;
        VirtualMachine vm = new VirtualMachine(module);
        vm.initMemory();
        vm.executeCode(index);
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
