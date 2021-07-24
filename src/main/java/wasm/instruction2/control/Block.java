package wasm.instruction2.control;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Expression;
import wasm.core2.instruction.Instruction;
import wasm.core2.instruction.Operate;
import wasm.instruction2.dump.DumpBlock;
import wasm.core2.model.Dump;
import wasm.core2.model.section.FunctionType;
import wasm.core2.model.type.BlockType;

public class Block implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        BlockType blockType = reader.readBlockType();
        Expression expression = reader.readExpression();
        return new DumpBlock(blockType, expression);
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof DumpBlock;

        DumpBlock b = (DumpBlock) args;

        FunctionType functionType = mi.getModuleInfo().getBlockType(b.blockType);

        mi.enterBlock(Instruction.BLOCK, functionType, b.expression);
    }

}
