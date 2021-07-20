package wasm.model.section;

import wasm.model.section.util.Uint32;

public class TypeIndex {
    public Uint32 value;

    public TypeIndex(Uint32 value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TypeIndex{" +
                "value=" + value +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("func[").append(index).append("]: ").append("sig=").append(value.value());

        return sb.toString();
    }
}
