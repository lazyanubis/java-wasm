package wasm.instruction.memory;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpMemory;
import wasm.model.Dump;
import wasm.model.index.MemoryIndex;
import wasm.model.number.U32;
import wasm.model.number.U64;

public class I64Load16U implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;

        DumpMemory a = (DumpMemory) args;

        System.err.println("So, which memory ?");
        byte[] bytes = vm.readBytesFromMemory(new MemoryIndex(new U32(0)), a, 2);

        vm.pushU64(new U64((bytes[1] & 0x00FF) << 8 | (bytes[0] & 0xFF)));
    }

}
