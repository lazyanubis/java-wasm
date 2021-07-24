package wasm.core.model.type;

import wasm.core.model.Type;

import static wasm.core.util.NumberTransform.toHex;

public class MutableType implements Type {

    private final byte value;

    private final String name;

    private MutableType(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public static final MutableType CONST   = new MutableType((byte) 0x00, "const");
    public static final MutableType MUTABLE = new MutableType((byte) 0x01, "var");

    public static MutableType of(byte value) {
        switch (value) {
            case 0x00: return CONST;
            case 0x01: return MUTABLE;
        }
        throw new RuntimeException("wrong value: 0x" + toHex(value));
    }

    @Override
    public byte value() {
        return value;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String dump() {
        return "0x" + toHex(value) + " " + name;
    }

    @Override
    public String toString() {
        return "MutableType{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

}
