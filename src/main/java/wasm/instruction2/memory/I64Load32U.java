package wasm.instruction2.memory;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.instruction2.dump.DumpMemory;
import wasm.core.model.Dump;
import wasm.core.model.index.MemoryIndex;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;

public class I64Load32U implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;

        DumpMemory a = (DumpMemory) args;

        // System.err.println("So, which memory ?");
        byte[] bytes = mi.readBytes(new MemoryIndex(new U32(0)), a, 4);

        mi.pushU64(new U64(new byte[] {
            bytes[3],
            bytes[2],
            bytes[1],
            bytes[0]
        }));
    }

}
