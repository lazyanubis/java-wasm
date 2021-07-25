package wasm.core.instruction.control;

import wasm.core.exception.Check;
import wasm.core.instruction.Expression;
import wasm.core.instruction.Instruction;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.section.FunctionType;
import wasm.core.model.type.BlockType;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.dump.DumpIfBlock;

import static wasm.core.util.ConstNumber.EXPRESSION_ELSE;

public class IfBlock implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        BlockType blockType = reader.readBlockType();
        Expression expression = reader.readExpression();
        Expression expression2 = null;
        if (reader.remaining() > 0) {
            if (reader.readByte(false) == EXPRESSION_ELSE) {
                reader.readByte();
                expression2 = reader.readExpression();
            }
        }
        return new DumpIfBlock(blockType, expression, expression2);
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, DumpIfBlock.class);

        DumpIfBlock b = (DumpIfBlock) args;

        FunctionType functionType = mi.getModuleInfo().getBlockType(b.blockType);

        if (mi.popBool()) {
            mi.enterBlock(Instruction.IF_BLOCK, functionType, b.expression1);
        } else if (null != b.expression2) {
            mi.enterBlock(Instruction.IF_BLOCK, functionType, b.expression2);
        }
    }
}
