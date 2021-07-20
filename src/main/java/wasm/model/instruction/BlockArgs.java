package wasm.model.instruction;

import wasm.model.section.util.Dump;

public class BlockArgs implements Dump {

    public BlockType blockType;

    public Instruction[] instructions;

    @Override
    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append("block < ").append(blockType.name()).append("\n");
        for (Instruction instruction : instructions) {
            sb.append("  ").append(instruction.dump().replace("\n", "\n  ")).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

}
