package wasm.instruction.numeric.i64.operate;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U64;
import wasm.util.NumberUtil;

public class I64Add implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        U64 v2 = vm.popU64();
        U64 v1 = vm.popU64();
        vm.pushU64(NumberUtil.add(v1, v2));
    }

}
