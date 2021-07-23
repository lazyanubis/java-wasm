package wasm.instruction.memory;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U32;

public class MemoryGrow implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        U32 grow = vm.popU32();
        System.err.println("So, which memory ?");
        U32 old = vm.getMemory(0).grow(grow);
        vm.pushU32(old);
    }
    
}
