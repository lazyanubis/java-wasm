package wasm.instruction.variable;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.GlobalIndex;
import wasm.model.number.U64;

public class GlobalSet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readGlobalIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof GlobalIndex;

        GlobalIndex a = (GlobalIndex) args;

        U64 value = vm.operandStack.popU64();

        vm.globals[a.intValue()].setU64(value);
    }

}
