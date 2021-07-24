package wasm.core.instruction;

import wasm.core.model.Dump;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Expression implements Dump, Iterable<Action> {

    private final Action[] actions;

    public Expression(Action[] actions) {
        this.actions = actions;
    }

    public int length() {
        return actions.length;
    }

    public Action get(int index) {
        return actions[index];
    }

    @Override
    public String dump() {
        return "[" + Stream.of(actions).map(Action::dump).collect(Collectors.joining(",")) + "]";
    }

    @Override
    public Iterator<Action> iterator() {
        return Arrays.stream(actions).iterator();
    }

}
