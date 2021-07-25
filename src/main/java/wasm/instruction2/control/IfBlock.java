package wasm.instruction2.control;

import wasm.core.exception.Check;
import wasm.core2.instruction.Expression;
import wasm.core2.instruction.Instruction;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.model.section.FunctionType;
import wasm.core2.model.type.BlockType;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.instruction2.dump.DumpIfBlock;

import java.util.Objects;

import static wasm.core3.util.ConstNumber.EXPRESSION_ELSE;

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
