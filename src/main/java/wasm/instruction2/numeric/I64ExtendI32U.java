package wasm.instruction2.numeric;

import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core3.numeric.U32;
import wasm.core3.numeric.U64;

public class I64ExtendI32U implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        U32 value = mi.popU32();
        byte[] bytes = value.getBytes();
        // 无符号拓展，内置方法都是有符号的
        U64 u64 = U64.valueOf(new byte[] { 0,0,0,0, bytes[0], bytes[1], bytes[2], bytes[3] });
        mi.pushU64(u64);
    }

}
