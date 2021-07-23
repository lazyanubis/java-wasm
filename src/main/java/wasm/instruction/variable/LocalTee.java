package wasm.instruction.variable;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.LocalIndex;
import wasm.model.number.U64;

public class LocalTee implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLocalIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof LocalIndex;

        LocalIndex a = (LocalIndex) args;

        int index = vm.local0Index.intValue() + a.intValue();

        U64 value = vm.operandStack.popU64();

        vm.operandStack.pushU64(value); // 再压回去

        vm.operandStack.setOperand(index, value);
    }

}
