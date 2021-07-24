package wasm.instruction2.memory;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.instruction2.dump.DumpMemory;
import wasm.core.model.Dump;
import wasm.core.model.index.MemoryIndex;
import wasm.core.numeric.U32;

public class I32Store8 implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;

        DumpMemory a = (DumpMemory) args;

        U32 v = mi.popU32();
        byte[] bytes = v.getBytes();

        // System.err.println("So, which memory ?");
        mi.writeBytes(new MemoryIndex(new U32(0)), a,
                new byte[] {bytes[3]});
    }

}
