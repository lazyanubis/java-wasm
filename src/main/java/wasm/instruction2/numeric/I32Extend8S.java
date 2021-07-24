package wasm.instruction2.numeric;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core3.numeric.U32;

public class I32Extend8S implements Operate {
    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        byte[] bytes = mi.popU32().getBytes();

        byte sign = ((bytes[3] & 0x80) == 0) ? 0 : (byte)0xFF;

        mi.pushS32(U32.valueOf(new byte[]{
            sign, sign, sign, bytes[3]
        }).intValue());
    }

}
