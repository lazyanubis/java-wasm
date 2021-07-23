package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.VirtualMachineFunction;
import wasm.core.WasmReader;
import wasm.instruction.Instruction;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.FunctionType;
import wasm.model.index.FunctionIndex;
import wasm.model.number.U64;
import wasm.model.type.ValueType;

public class Call implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readFunctionIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;
        assert args instanceof FunctionIndex;

        int index = ((FunctionIndex) args).intValue();

        VirtualMachineFunction function = vm.functions[index];

        callFunction(vm, function);
    }

    public void callFunction(VirtualMachine vm, VirtualMachineFunction function) {
        if (null != function.local) {
            // 有本地函数内容，对于模块来说是外部的
            callExternalFunction(vm, function);
        } else {
            callInternalFunction(vm, function);
        }
    }

    private void callInternalFunction(VirtualMachine vm, VirtualMachineFunction function) {
        assert null != function.code;

        vm.enterBlock(Instruction.CALL, function.type, function.code.expressions);

        // 分配本地变量
        long length = function.code.localCount();
        for (int i = 0; i < length; i++) { vm.operandStack.pushU64(new U64(0)); }
    }

    // ========================== tools ==========================

    private Object wrapU64(ValueType type, U64 value) {
        switch (type.value()) {
            case 0x7F: // i32
                return (int) value.longValue();
            case 0x7E: // i64
                return value.longValue();
            default:
                throw new RuntimeException("what a type ?");
        }
    }

    private U64 unwrapU64(ValueType type, Object value) {
        switch (type.value()) {
            case 0x7F: // i32
                return new U64((int) value);
            case 0x7E: // i64
                return new U64((long) value);
            default:
                throw new RuntimeException("what a type ?");
        }
    }

    private Object[] popArgs(VirtualMachine vm, FunctionType type) {
        Object[] args = new Object[type.parameters.length];
        for (int i = args.length - 1; 0 <= i; i--) {
            args[i] = wrapU64(type.parameters[i], vm.operandStack.popU64());
        }
        return args;
    }

    private void pushResults(VirtualMachine vm, FunctionType type, Object[] results) {
        for (int i = 0; i < results.length; i++) {
            vm.operandStack.pushU64(unwrapU64(type.results[i], results[i]));
        }
    }

    private void callExternalFunction(VirtualMachine vm, VirtualMachineFunction function) {
        assert null != function.local;

        Object[] args = popArgs(vm, function.type);
        Object[] results = function.local.execute(args);
        pushResults(vm, function.type, results);
    }

}
