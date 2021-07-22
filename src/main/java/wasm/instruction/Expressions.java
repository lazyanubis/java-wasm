package wasm.instruction;

import wasm.model.Dump;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Expressions implements Dump, Iterable<Expression> {

    private final Expression[] expressions;

    public Expressions(Expression[] expressions) {
        this.expressions = expressions;
    }

    public int length() {
        return expressions.length;
    }

    public Expression get(int index) {
        return expressions[index];
    }

    @Override
    public String dump() {
        return "[" + Stream.of(expressions).map(Expression::dump).collect(Collectors.joining(",")) + "]";
    }

    @Override
    public Iterator<Expression> iterator() {
        return Arrays.stream(expressions).iterator();
    }

}
