package wasm.model.type;

import wasm.model.Dump;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Types implements Dump {

    private final Type[] types;

    public Types(Type[] types) {
        this.types = types;
    }

    @Override
    public String dump() {
        return Stream.of(types).map(Type::dump).collect(Collectors.joining(","));
    }
}
