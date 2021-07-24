package wasm.instruction2.control;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.instruction.Instruction;
import wasm.instruction2.dump.DumpBlock;
import wasm.core2.model.Dump;
import wasm.core2.model.section.FunctionType;

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
