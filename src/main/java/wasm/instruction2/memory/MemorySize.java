package wasm.instruction2.memory;

import wasm.core3.model.index.MemoryIndex;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;

public class MemorySize implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        // System.err.println("The memory.size instruction returns the current size of a memory. So, which memory ?");
        mi.pushU32(mi.size(MemoryIndex.of(0)));
    }

}
