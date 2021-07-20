package wasm.model.section;

public class Custom {

    public String name;

    public byte[] bytes;

    public Custom(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("custom[").append(index).append("]: ").append("name=").append(name);

        return sb.toString();
    }
}
