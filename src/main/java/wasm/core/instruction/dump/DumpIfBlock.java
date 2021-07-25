package wasm.core.instruction.dump;

import wasm.core.instruction.Action;
import wasm.core.instruction.Expression;
import wasm.core.model.Dump;
import wasm.core.model.type.BlockType;

public class DumpIfBlock implements Dump {

    public final BlockType blockType;
    public final Expression expression1;
    public final Expression expression2;

    public DumpIfBlock(BlockType blockType, Expression expression1, Expression expression2) {
        this.blockType = blockType;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append("-> ").append(blockType.name()).append("\n");
        for (Action e : expression1) {
            sb.append("  ").append(e.dump().replace("\n", "\n  ")).append("\n");
        }
        if (null != expression2) {
            sb.append("else").append("\n");
            for (Action e : expression2) {
                sb.append("  ").append(e.dump().replace("\n", "\n  ")).append("\n");
            }
        }
        sb.append("end");

        return sb.toString();
    }

}
