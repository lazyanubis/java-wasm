package wasm.core.instruction.variable;

import wasm.core.exception.Check;
import wasm.core.numeric.USize;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.model.index.GlobalIndex;

public class GlobalGet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readGlobalIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, GlobalIndex.class);

        GlobalIndex a = (GlobalIndex) args;

        USize value = mi.getGlobal(a).get();

        mi.pushUSize(value);
    }

}
