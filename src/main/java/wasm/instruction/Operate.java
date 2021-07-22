package wasm.instruction;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.model.Dump;

public interface Operate {

    default Dump read(WasmReader reader) {
        throw new RuntimeException("what a operate code args ?");
    }

    default void operate(VirtualMachine vm, Dump args) {
        throw new RuntimeException("what a operate code ?");
    }

}
