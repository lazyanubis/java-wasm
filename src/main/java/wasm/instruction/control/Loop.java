package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.instruction.Instruction;
import wasm.instruction.dump.DumpBlock;
import wasm.model.Dump;
import wasm.model.FunctionType;

public class Loop extends Block {

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof DumpBlock;

        DumpBlock b = (DumpBlock) args;

        FunctionType functionType = vm.module.getBlockType(b.blockType);

        vm.enterBlock(Instruction.LOOP, functionType, b.expressions);
    }

}
