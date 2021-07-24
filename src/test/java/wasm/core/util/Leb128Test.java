package wasm.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Leb128Test {

    @Test
    public void testRead() {
        Leb128.Result r = Leb128.decodeVarUint(new byte[] { 0x00 }, 32);
        Assertions.assertEquals(r.length, 1);
        Assertions.assertArrayEquals(r.bytes, new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });

        r = Leb128.decodeVarUint(new byte[] { 0x01 }, 32);
        Assertions.assertEquals(r.length, 1);
        Assertions.assertArrayEquals(r.bytes, new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 });



    }

}
