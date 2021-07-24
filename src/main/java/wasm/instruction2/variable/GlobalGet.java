package wasm.instruction2.variable;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.model.index.GlobalIndex;
import wasm.core.numeric.U64;

public class GlobalGet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readGlobalIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof GlobalIndex;

        GlobalIndex a = (GlobalIndex) args;

        U64 value = mi.getGlobal(a);

        mi.pushU64(value);
    }

}