package wasm.instruction2.numeric;

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

        byte sign = ((bytes[2] & 0x80) == 0) ? 0 : (byte)0xFF;

        mi.pushS32(new U32(new byte[]{
            sign, sign, bytes[2], bytes[3]
        }).intValue());
    }

}
