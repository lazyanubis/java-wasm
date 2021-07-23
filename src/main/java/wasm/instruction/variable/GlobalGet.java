package wasm.instruction.variable;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.GlobalIndex;
import wasm.model.number.U64;

public class GlobalGet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readGlobalIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;
        assert args instanceof GlobalIndex;

        GlobalIndex a = (GlobalIndex) args;

        U64 value = vm.globals[a.intValue()].getU64();

        vm.operandStack.pushU64(value);
    }

}
