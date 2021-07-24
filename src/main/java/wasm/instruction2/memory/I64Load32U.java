package wasm.instruction2.memory;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.instruction2.dump.DumpMemory;
import wasm.core2.model.Dump;
import wasm.core2.model.index.MemoryIndex;
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
        byte[] bytes = mi.readBytes(new MemoryIndex(U32.valueOf(0)), a, 4);

        mi.pushU64(U64.valueOf(new byte[] {
            bytes[3],
            bytes[2],
            bytes[1],
            bytes[0]
        }));
    }

}