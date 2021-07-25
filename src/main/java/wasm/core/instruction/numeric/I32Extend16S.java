package wasm.core.instruction.numeric;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.numeric.U32;

public class I32Extend16S implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        byte[] bytes = mi.popU32().getBytes();

        mi.pushS32(U32.valueOfS(new byte[]{
            bytes[2], bytes[3]
        }).intValue());
    }

}
