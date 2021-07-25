package wasm.instruction2.variable;

import wasm.core.exception.Check;
import wasm.core.numeric.U64;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core3.model.index.GlobalIndex;

import java.util.Objects;

public class GlobalSet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readGlobalIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Objects.requireNonNull(args);
        Check.require(args, GlobalIndex.class);

        GlobalIndex a = (GlobalIndex) args;

        U64 value = mi.popU64();

        mi.getGlobal(a).set(value);
    }

}
