package wasm.instruction2.variable;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.index.GlobalIndex;
import wasm.core.numeric.U64;

public class GlobalSet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readGlobalIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof GlobalIndex;

        GlobalIndex a = (GlobalIndex) args;

        U64 value = mi.popU64();

        mi.setGlobal(a, value);
    }

}
