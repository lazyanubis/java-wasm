package wasm.model.section;

import wasm.model.instruction.Instruction;
import wasm.model.section.util.Local;
import wasm.model.section.util.Uint32;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Code {

    public Uint32 size;

    public Local[] locals;

    public Instruction[] instructions;

    public Code(Uint32 size, Local[] locals, Instruction[] instructions) {
        this.size = size;
        this.locals = locals;
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Code{" +
                "size=" + size +
                ", locals=" + Arrays.toString(locals) +
                ", instructions=" + Arrays.toString(instructions) +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("func[").append(index).append("]: ")
                .append("locals=[").append(Stream.of(locals).map(l -> l.type.name() + " x " + l.n.value()).collect(Collectors.joining(", "))).append("]");

        if (null != instructions && instructions.length > 0) {
            sb.append("\n");
            for (Instruction instruction : instructions) {
                sb.append("    ").append(instruction.dump().replace("\n", "\n    ")).append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
