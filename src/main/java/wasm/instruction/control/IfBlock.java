package wasm.instruction.control;

import wasm.core.WasmReader;
import wasm.instruction.Expressions;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpIfBlock;
import wasm.model.Dump;
import wasm.model.type.BlockType;

import static wasm.util.Const.EXPRESS_ELSE;

public class IfBlock implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        BlockType blockType = reader.readBlockType();
        Expressions expressions = reader.readExpressions();
        Expressions expressions2 = null;
        if (reader.remaining() > 0) {
            if (reader.readByte(false) == EXPRESS_ELSE) {
                reader.readByte();
                expressions2 = reader.readExpressions();
            }
        }
        return new DumpIfBlock(blockType, expressions, expressions2);
    }

}
