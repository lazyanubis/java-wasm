package wasm.instruction2.numeric;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.numeric.U64;

public class I64Extend16S implements Operate {
    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        byte[] bytes = mi.popU64().getBytes();

        byte sign = ((bytes[6] & 0x80) == 0) ? 0 : (byte)0xFF;

        mi.pushS64(new U64(new byte[]{
            sign, sign, sign, sign,
            sign, sign, bytes[6], bytes[7]
        }).longValue());
    }

}
