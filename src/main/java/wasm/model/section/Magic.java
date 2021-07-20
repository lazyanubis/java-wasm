package wasm.model.section;

public class Magic {

    private String value;

    private String string;

    public Magic(String value) {
        this.value = value;

        assert "01101101011100110110000100000000".equals(value);

        StringBuilder sb = new StringBuilder(4);
        for (int i = 3; 0 <= i; i--) {
            sb.append((char) Byte.valueOf(value.substring(i * 8, (i + 1) * 8), 2).byteValue());
        }

        this.string = sb.toString();
    }

    public String value() {
        return this.string;
    }

    @Override
    public String toString() {
        return "Magic{" +
                "string='" + string + '\'' +
                '}';
    }
}
