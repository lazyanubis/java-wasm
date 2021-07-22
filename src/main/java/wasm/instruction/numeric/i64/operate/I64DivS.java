package wasm.instruction.numeric.i64.operate;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.util.NumberUtil;

public class I64DivS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        long v2 = vm.popS64();
        long v1 = vm.popS64();
        vm.pushS64(NumberUtil.divS(v1, v2));
    }

}
