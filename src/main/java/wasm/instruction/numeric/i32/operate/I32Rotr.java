package wasm.instruction.numeric.i32.operate;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U32;
import wasm.util.NumberUtil;

public class I32Rotr implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        U32 v2 = vm.popU32();
        U32 v1 = vm.popU32();
        vm.pushU32(NumberUtil.rotr(v1, v2));
    }

}
