package wasm.model.instruction;

import wasm.model.section.util.Dump;

public class IfArgs implements Dump {

    public BlockType blockType;

    public Instruction[] instructions1;

    public Instruction[] instructions2;

    @Override
    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append("if < ").append(blockType.name()).append("\n");
        for (Instruction instruction : instructions1) {
            sb.append("  ").append(instruction.dump().replace("\n", "\n  ")).append("\n");
        }
        if (null != instructions2) {
            sb.append("else").append("\n");
            for (Instruction instruction : instructions2) {
                sb.append("  ").append(instruction.dump().replace("\n", "\n  ")).append("\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

}
