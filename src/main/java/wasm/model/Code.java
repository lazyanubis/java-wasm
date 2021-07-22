package wasm.model;

import wasm.instruction.Expressions;
import wasm.model.number.U32;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Code {

    public U32 size;

    public Local[] locals;

    public Expressions expressions;

    public Code(U32 size, Local[] locals, Expressions expressions) {
        this.size = size;
        this.locals = locals;
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        return "Code{" +
                "size=" + size +
                ", locals=" + Arrays.toString(locals) +
                ", express=" + expressions +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("func[").append(index).append("]: ")
                .append("locals=[").append(Stream.of(locals).map(l -> l.type.name() + " x " + l.n).collect(Collectors.joining(", "))).append("]");

        if (null != expressions && expressions.length() > 0) {
            sb.append("\n");
            for (int i = 0; i < expressions.length(); i++) {
                sb.append("    ").append(expressions.get(i).dump().replace("\n", "\n    ")).append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
