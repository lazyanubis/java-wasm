package wasm.instruction2.control;

import wasm.core.structure.ModuleInstance;
import wasm.core.instruction.Instruction;
import wasm.instruction2.dump.DumpBlock;
import wasm.core.model.Dump;
import wasm.core.model.section.FunctionType;

public class Loop extends Block {

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof DumpBlock;

        DumpBlock b = (DumpBlock) args;

        FunctionType functionType = mi.getModuleInfo().getBlockType(b.blockType);

        mi.enterBlock(Instruction.LOOP, functionType, b.expression);
    }

}
