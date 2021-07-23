package wasm.instruction.memory;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpMemory;
import wasm.model.Dump;
import wasm.model.index.MemoryIndex;
import wasm.model.number.U32;

public class I32Store implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpMemory(reader.readLeb128U32(), reader.readLeb128U32());
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;

        DumpMemory a = (DumpMemory) args;

        U32 v = vm.operandStack.popU32();
        byte[] bytes = v.getBytes();

        System.err.println("So, which memory ?");
        vm.writeBytesToMemory(new MemoryIndex(new U32(0)), a,
                new byte[] {bytes[3], bytes[2], bytes[1], bytes[0]});
    }

}
