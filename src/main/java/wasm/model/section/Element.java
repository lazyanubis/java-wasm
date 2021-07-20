package wasm.model.section;

import wasm.model.instruction.Instruction;
import wasm.model.section.util.Uint32;

public class Element {

    public Uint32 table;

    public Instruction[] offset;

    public Uint32[] init;

    public Element(Uint32 table, Instruction[] offset, Uint32[] init) {
        this.table = table;
        this.offset = offset;
        this.init = init;
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("element[").append(index).append("]:\n");
        sb.append("  table: ").append(table.value()).append("\n");
        for (int i = 0; i < offset.length; i++) {
            sb.append("  offset[").append(i).append("]: \n");
            sb.append("    ").append(offset[i].dump()).append("\n");
        }
        for (int i = 0; i < init.length; i++) {
            sb.append("  init[").append(i).append("]: ").append(init[i]).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
