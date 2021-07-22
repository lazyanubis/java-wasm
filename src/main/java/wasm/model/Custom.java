package wasm.model;

public class Custom {

    public final String name;

    public final byte[] bytes;

    public Custom(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public String dump(int index) {
        return "custom[" + index + "]: " +
                "name=" + name + " " +
                "length=" + bytes.length;
    }

}
