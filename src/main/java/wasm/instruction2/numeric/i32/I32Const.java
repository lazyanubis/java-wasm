package wasm.instruction2.numeric.i32;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.instruction2.dump.DumpI32;
import wasm.core2.model.Dump;

public class I32Const implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpI32(reader.readLeb128S32());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof DumpI32;

        DumpI32 a = (DumpI32) args;

        mi.pushS32(a.value);
    }

}
