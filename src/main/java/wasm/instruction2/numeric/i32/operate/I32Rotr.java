package wasm.instruction2.numeric.i32.operate;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core3.numeric.U32;
import wasm.core2.util.NumberUtil;

public class I32Rotr implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        U32 v2 = mi.popU32();
        U32 v1 = mi.popU32();
        mi.pushU32(NumberUtil.rotr(v1, v2));
    }

}
