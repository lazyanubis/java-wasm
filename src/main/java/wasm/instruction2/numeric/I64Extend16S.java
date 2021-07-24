package wasm.instruction2.numeric;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core3.numeric.U64;

public class I64Extend16S implements Operate {
    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        byte[] bytes = mi.popU64().getBytes();

        byte sign = ((bytes[6] & 0x80) == 0) ? 0 : (byte)0xFF;

        mi.pushS64(U64.valueOf(new byte[]{
            sign, sign, sign, sign,
            sign, sign, bytes[6], bytes[7]
        }).longValue());
    }

}
