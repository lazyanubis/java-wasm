package wasm.model.section.util;

public class Leb128 {

    public static class Uint64Result {
        public Uint64 result;
        public int length;
    }

    public static class Int64Result {
        public long result;
        public int length;
    }

    public static Uint64Result decodeVarUint(byte[] data, int size) {
        Uint64Result uint64Result = new Uint64Result();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];

            if (i == size / 7) {
                if ((b & 0x80) != 0) {
                    throw new RuntimeException("integer representation too long");
                }
                if (b >> (size - i * 7) > 0) {
                    throw new RuntimeException("integer too large");
                }
            }

            sb.insert(0, toBinaryString(b).substring(1));

            if ((b & 0x80) == 0) {
                String v = "0000000000000000000000000000000000000000000000000000000000000000" + sb;
                uint64Result.result = new Uint64(v.substring(v.length() - 64));
                uint64Result.length = i + 1;
                return uint64Result;
            }
        }

        throw new RuntimeException("unexpected end of section or function");
    }


    public static Int64Result decodeVarInt(byte[] data, int size) {
        Int64Result int64Result = new Int64Result();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];

            if (i == size / 7) {
                if ((b&0x80) != 0) {
                    throw new RuntimeException("integer representation too long");
                }
                if ((b & 0x40) == 0 && b >> (size - i * 7 - 1) != 0 ||
                        (b & 0x40) != 0 && ((b | 0x80)) >> (size - i * 7 - 1) != -1) {
                    throw new RuntimeException("integer too large");
                }
            }

            sb.insert(0, toBinaryString(b).substring(1));

            if ((b&0x80) == 0) {
                if ((i*7 < size) && ((b & 0x40) != 0)) {
                    while (sb.length() < 64) {
                        sb.insert(0, "1");
                    }
                }
                int64Result.result = Long.parseUnsignedLong(sb.toString(), 2);
                int64Result.length = i + 1;
                return int64Result;
            }

        }

        throw new RuntimeException("unexpected end of section or function");
    }

    private static String toBinaryString(byte b) {
        String s = Integer.toBinaryString(b);
        if (s.length() > 8) {
            return s.substring(24);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8 - s.length(); i++) {
            sb.append("0");
        }
        return sb + s;
    }

}
