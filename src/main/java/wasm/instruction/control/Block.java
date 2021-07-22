package wasm.instruction.control;

import wasm.core.WasmReader;
import wasm.instruction.Expressions;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpBlock;
import wasm.model.Dump;
import wasm.model.type.BlockType;

public class Block implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        BlockType blockType = reader.readBlockType();
        Expressions expressions = reader.readExpressions();
        return new DumpBlock(blockType, expressions);
    }

}
