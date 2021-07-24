package wasm.core.instruction;

import wasm.core2.VirtualMachine;
import wasm.core2.WasmReader;
import wasm.core.model.Dump;

public interface Operate {

    default Dump read(WasmReader reader) {
        throw new RuntimeException("what a operate code args ?");
    }

    default void operate(VirtualMachine vm, Dump args) {
        throw new RuntimeException("what a operate code ?");
    }

}
