package wasm.model.section.util;

/**
 * 无符号32位 用java原生有符号表示，取用时要注意
 */
public class Uint32 {

    private String value;

    public Uint32(String value) {
        if (value.length() > 32) {
            this.value = value.substring(value.length() - 32);
        } else {
            this.value = value;
        }
    }

    public String getValue() {
        return value;
    }

    public int value() {
        return (int) Long.parseLong(value, 2);
    }

    @Override
    public String toString() {
        return Long.toString(Long.parseLong(value, 2));
    }
}
