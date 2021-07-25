package wasm.core.model.section;

import wasm.core.exception.Check;

public class Magic {

    private static final byte b0 = 0x00;
    private static final byte b1 = 0x61;
    private static final byte b2 = 0x73;
    private static final byte b3 = 0x6D;

    private final String value;

    public Magic(byte b0, byte b1, byte b2, byte b3) {
        Check.require(Magic.b0, b0);
        Check.require(Magic.b1, b1);
        Check.require(Magic.b2, b2);
        Check.require(Magic.b3, b3);

        this.value = new String(new byte[]{ b0, b1, b2, b3 });
    }

    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Magic: '" + value + "'";
    }

}
