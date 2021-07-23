package wasm.instruction.memory;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;

public class MemorySize implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        // System.err.println("The memory.size instruction returns the current size of a memory. So, which memory ?");
        vm.operandStack.pushU32(vm.getMemory(0).size());
    }

}
