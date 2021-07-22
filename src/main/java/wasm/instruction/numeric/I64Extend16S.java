package wasm.instruction.numeric;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U64;

public class I64Extend16S implements Operate {
    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        vm.pushS64(new U64(new byte[]{
                0, 0, 0, 0,
                0, 0, vm.popU64().getBytes()[6], vm.popU64().getBytes()[7]
        }).longValue());
    }

}
