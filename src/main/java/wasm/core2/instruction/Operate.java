package wasm.core2.instruction;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.model.Dump;

public interface Operate {

    default Dump read(WasmReader reader) {
        throw new RuntimeException("what a operate code args ?");
    }

    default void operate(ModuleInstance mi, Dump args) {
        throw new RuntimeException("what a operate code ?");
    }

}
