package wasm.instruction.numeric.i32.operate;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.util.NumberUtil;

public class I32DivS implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        int v2 = vm.popS32();
        int v1 = vm.popS32();
        vm.pushS32(NumberUtil.divS(v1, v2));
    }

}
