package wasm.instruction2.memory;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.instruction2.dump.DumpMemory;
import wasm.core.model.Dump;
import wasm.core.model.index.MemoryIndex;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;

public class I64Store implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;

        DumpMemory a = (DumpMemory) args;

        U64 v = mi.popU64();
        byte[] bytes = v.getBytes();

        // System.err.println("So, which memory ?");
        mi.writeBytes(new MemoryIndex(new U32(0)), a,
                new byte[] {bytes[7], bytes[6], bytes[5], bytes[4], bytes[3], bytes[2], bytes[1], bytes[0]});
    }

}
