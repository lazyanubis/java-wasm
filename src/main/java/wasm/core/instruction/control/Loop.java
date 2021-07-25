package wasm.core.instruction.control;

import wasm.core.exception.Check;
import wasm.core.instruction.Instruction;
import wasm.core.model.Dump;
import wasm.core.model.section.FunctionType;
import wasm.core.structure.ModuleInstance;
import wasm.core.instruction.dump.DumpBlock;

public class Loop extends Block {

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, DumpBlock.class);

        DumpBlock b = (DumpBlock) args;

        FunctionType functionType = mi.getModuleInfo().getBlockType(b.blockType);

        mi.enterBlock(Instruction.LOOP, functionType, b.expression);
    }

}
