package wasm.core;

import wasm.instruction.Expression;
import wasm.instruction.Expressions;
import wasm.instruction.dump.DumpMemory;
import wasm.model.Code;
import wasm.model.Data;
import wasm.model.number.U32;
import wasm.model.number.U64;
import wasm.util.NumberUtil;

public class VirtualMachine extends OperandStack {

    public Module module;

    private Memory[] memories;

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
        U32 immediate = this.popU32();
        return NumberUtil.add(new U64(offset), new U64(immediate));
    }

    public U32 readU8FromMemory(int index, DumpMemory args) {
        byte[] bytes = new byte[1];
        U64 offset = getOffset(args);
        this.memories[index].read(offset.u32(), bytes);
        return new U32(bytes[0]);
    }

    public U32 readU16FromMemory(int index, DumpMemory args) {
        byte[] bytes = new byte[2];
        U64 offset = getOffset(args);
        this.memories[index].read(offset.u32(), bytes);
        return new U32(bytes[0] << 8 + bytes[1]);
    }

    public U32 readU32FromMemory(int index, DumpMemory args) {
        byte[] bytes = new byte[4];
        U64 offset = getOffset(args);
        this.memories[index].read(offset.u32(), bytes);
        return new U32(bytes[3], bytes[2], bytes[1], bytes[0]);
    }

    public U64 readU64FromMemory(int index, DumpMemory args) {
        byte[] bytes = new byte[8];
        U64 offset = getOffset(args);
        this.memories[index].read(offset.u32(), bytes);
        return new U64(new byte[]{bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0]});
    }

}
