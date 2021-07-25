package wasm.core.instruction.dump;

import wasm.core.instruction.Action;
import wasm.core.instruction.Expression;
import wasm.core.model.Dump;
import wasm.core.model.type.BlockType;

public class DumpBlock implements Dump {

    public final BlockType blockType;
    public final Expression expression;

    public DumpBlock(BlockType blockType, Expression expression) {
        this.blockType = blockType;
        this.expression = expression;
    }

    @Override
    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append("-> ").append(blockType.name()).append("\n");
        for (Action e : expression) {
            sb.append("  ").append(e.dump().replace("\n", "\n  ")).append("\n");
        }
        sb.append("end");

        return sb.toString();
    }

}
