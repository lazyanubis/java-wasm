package wasm.model.section.util;

public enum LimitTag {

    TAG0(0x00),
    TAG1(0x01);

    public final byte value;

    LimitTag(int value) {
        this.value = (byte) value;
    }


    public static LimitTag valueOf(byte value) {
        for (LimitTag type : LimitTag.values()) {
            if (value == type.value) {
                return type;
            }
        }
        throw new RuntimeException("wrong value for LimitTag");
    }

}
