package wasm.core.model.tag;

import wasm.core.model.Type;

import static wasm.core.util.NumberTransform.toHex;

public class PortTag implements Type {

    private final byte value;

    private final String name;

    private PortTag(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public static final PortTag FUNCTION = new PortTag((byte) 0x00, "typeidx");
    public static final PortTag TABLE    = new PortTag((byte) 0x01, "tabletype");
    public static final PortTag MEMORY   = new PortTag((byte) 0x02, "memtype");
    public static final PortTag GLOBAL   = new PortTag((byte) 0x03, "globaltype");

    public static PortTag of(byte value) {
        switch (value) {
            case 0x00: return FUNCTION;
            case 0x01: return TABLE;
            case 0x02: return MEMORY;
            case 0x03: return GLOBAL;
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
        return "PortTag{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
