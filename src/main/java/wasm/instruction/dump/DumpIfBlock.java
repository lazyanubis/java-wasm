package wasm.instruction.dump;

import wasm.instruction.Expression;
import wasm.instruction.Expressions;
import wasm.model.Dump;
import wasm.model.type.BlockType;

public class DumpIfBlock implements Dump {

    public final BlockType blockType;
    public final Expressions expressions1;
    public final Expressions expressions2;

    public DumpIfBlock(BlockType blockType, Expressions expressions1, Expressions expressions2) {
        this.blockType = blockType;
        this.expressions1 = expressions1;
        this.expressions2 = expressions2;
    }

    @Override
    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append("-> ").append(blockType.name()).append("\n");
        for (Expression e : expressions1) {
            sb.append("  ").append(e.dump().replace("\n", "\n  ")).append("\n");
        }
        if (null != expressions2) {
            sb.append("else").append("\n");
            for (Expression e : expressions2) {
                sb.append("  ").append(e.dump().replace("\n", "\n  ")).append("\n");
            }
        }
        sb.append("end");

        return sb.toString();
    }

}
