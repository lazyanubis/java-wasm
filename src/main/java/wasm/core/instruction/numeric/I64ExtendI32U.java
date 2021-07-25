package wasm.core.instruction.numeric;

import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;

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
        U64 u64 = U64.valueOfU(new byte[] {
            bytes[0], bytes[1], bytes[2], bytes[3]
        });
        mi.pushU64(u64);
    }

}
