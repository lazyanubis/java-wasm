package wasm.instruction2.control;

import wasm.core.exception.Check;
import wasm.core2.instruction.Instruction;
import wasm.core2.model.Dump;
import wasm.core2.model.section.FunctionType;
import wasm.core2.structure.ModuleInstance;
import wasm.instruction2.dump.DumpBlock;

import java.util.Objects;

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
