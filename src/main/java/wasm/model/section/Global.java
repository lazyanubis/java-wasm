package wasm.model.section;

public class Global {

    public GlobalType type;

    public Object init;

    public Global(GlobalType type, Object init) {
        this.type = type;
        this.init = init;
    }

    @Override
    public String toString() {
        return "Global{" +
                "type=" + type +
                ", init=" + init +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("global[").append(index).append("]: ").append(type.dump());

        return sb.toString();
    }
}
