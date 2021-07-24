package wasm.instruction2.numeric.i32.operate;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.numeric.U32;

public class I32Ctz implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        U32 v = mi.popU32();
        mi.pushU32(new U32(v.ctz()));
    }

}
