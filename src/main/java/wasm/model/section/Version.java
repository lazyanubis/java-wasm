package wasm.model.section;

public class Version {

    private String value;

    public Version(String value) {
        this.value = value;
    }

    public String value() {
        return "0x" + Long.toString(Long.parseLong(value, 2), 16);
    }

    @Override
    public String toString() {
        return "Version{" +
                "value='" + value + '\'' +
                '}';
    }
}
