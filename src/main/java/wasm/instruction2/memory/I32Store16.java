package wasm.instruction2.memory;

import wasm.core.numeric.U32;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core3.model.index.MemoryIndex;
import wasm.instruction2.dump.DumpMemory;

import java.util.Objects;

public class I32Store16 implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Objects.requireNonNull(args);

        DumpMemory a = (DumpMemory) args;

        U32 v = mi.popU32();
        byte[] bytes = v.getBytes();

        // System.err.println("So, which memory ?");
        mi.writeBytes(MemoryIndex.of(U32.valueOf(0)), a,
                new byte[] {bytes[3], bytes[2]});
    }

}
