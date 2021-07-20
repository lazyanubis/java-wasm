package wasm.model.section.util;

public class Local {

    public Uint32 n;

    public ValueType type;

    public Local(Uint32 n, ValueType type) {
        this.n = n;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Local{" +
                "n=" + n +
                ", type=" + type +
                '}';
    }
}
