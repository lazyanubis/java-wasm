package wasm.core.model.type;

public class GlobalType {

    public ValueType value;     // 值类型
    public MutableType mutable; // 是否可变

    public GlobalType(ValueType value, MutableType mutable) {
        this.value = value;
        this.mutable = mutable;
    }

    @Override
    public String toString() {
        return "GlobalType{" +
                "value=" + value +
                ", mutable=" + mutable +
                '}';
    }

    public String dump() {
        return "{type: " + value.name() + ", mut: " + mutable.name() + "}";
    }

}
