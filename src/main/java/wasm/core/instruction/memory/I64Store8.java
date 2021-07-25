package wasm.core.instruction.memory;

import wasm.core.exception.Check;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.model.index.MemoryIndex;
import wasm.core.instruction.dump.DumpMemory;

public class I64Store8 implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);

        DumpMemory a = (DumpMemory) args;

        U64 v = mi.popU64();
        byte[] bytes = v.getBytes();

        // System.err.println("So, which memory ?");
        mi.writeBytes(MemoryIndex.of(U32.valueOf(0)), a,
                new byte[] {bytes[7]});
    }

}
