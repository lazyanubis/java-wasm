package wasm.instruction.variable;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.LocalIndex;
import wasm.model.number.U64;

public class LocalGet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLocalIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;
        assert args instanceof LocalIndex;

        LocalIndex a = (LocalIndex) args;

        int index = vm.local0Index.intValue() + a.intValue();

        U64 value = vm.operandStack.getOperand(index);

        vm.operandStack.pushU64(value);
    }

}
