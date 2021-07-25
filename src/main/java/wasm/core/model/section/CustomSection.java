package wasm.core.model.section;

public class CustomSection {

    public final String name;
    public final byte[] bytes;

    public CustomSection(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public String dump(int index) {
        return "custom[" + index + "]: name=" + name + " length=" + bytes.length;
    }

}
