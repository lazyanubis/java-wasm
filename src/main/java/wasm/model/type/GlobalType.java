package wasm.model.type;

public class GlobalType {

    public ValueType valueType;

    public MutableType mutableType;

    public GlobalType(ValueType valueType, MutableType mutableType) {
        this.valueType = valueType;
        this.mutableType = mutableType;
    }

    @Override
    public String toString() {
        return "GlobalType{" +
                "valueType=" + valueType +
                ", mutableType=" + mutableType +
                '}';
    }

    public String dump() {
        return "{type: " + valueType.name() + ", mut: " + mutableType.name() + "}";
    }

}
