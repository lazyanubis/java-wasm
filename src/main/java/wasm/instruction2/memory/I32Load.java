package wasm.instruction2.memory;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.instruction2.dump.DumpMemory;
import wasm.core.model.Dump;
import wasm.core.model.index.MemoryIndex;
import wasm.core.numeric.U32;

public class I32Load implements Operate {

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

        mi.pushU32(new U32(new byte[]{bytes[3], bytes[2], bytes[1], bytes[0]}));
    }

}
