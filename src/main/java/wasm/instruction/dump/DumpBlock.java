package wasm.instruction.dump;

import wasm.instruction.Expression;
import wasm.instruction.Expressions;
import wasm.model.Dump;
import wasm.model.type.BlockType;

public class DumpBlock implements Dump {

    private final BlockType blockType;
    private final Expressions expressions;

    public DumpBlock(BlockType blockType, Expressions expressions) {
        this.blockType = blockType;
        this.expressions = expressions;
    }

    @Override
    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append(" -> ").append(blockType.name()).append("\n");
        for (Expression e : expressions) {
            sb.append("  ").append(e.dump().replace("\n", "\n  ")).append("\n");
        }
        sb.append("end");

        return sb.toString();
    }

}
