package wasm.core.model.type;

import wasm.core.model.Dump;
import wasm.core.model.Type;

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
