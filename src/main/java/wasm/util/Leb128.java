package wasm.util;

import wasm.model.number.U64;

public class Leb128 {

    public static class U64Result {
        public U64 result;
        public int length;
    }

    public static class S64Result {
        public long result;
        public int length;
    }

    public static U64Result decodeVarUint(byte[] data, int size) {
        U64Result u64Result = new U64Result();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];

            if (i == size / 7) {
                // 如果应当是最后一个字节
                if ((b & 0x80) != 0) {
                    // 已经最后一个字节 第一位标识符不应该是1 如果是1表示后面还有，不应该
                    throw new RuntimeException("integer representation too long");
                }
                if (b >> (size - i * 7) > 0) {
                    // 已经最后一个字节 移除需要的数字位，剩下的应该全是0
                    throw new RuntimeException("integer too large");
                }
            }

            // 每次只取后面7位数字
            sb.insert(0, toBinaryString(b).substring(1));

            if ((b & 0x80) == 0) {
                String v = "0000000000000000000000000000000000000000000000000000000000000000" + sb;
                u64Result.result = new U64(v.substring(v.length() - 64));
                u64Result.length = i + 1;
                return u64Result;
            }
        }

        throw new RuntimeException("unexpected end of section or function");
    }


    public static S64Result decodeVarInt(byte[] data, int size) {
        S64Result s64Result = new S64Result();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];

            if (i == size / 7) {
                // 如果应当是最后一个字节
                if ((b&0x80) != 0) {
                    // 已经最后一个字节 第一位标识符不应该是1 如果是1表示后面还有，不应该
                    throw new RuntimeException("integer representation too long");
                }
                if (((b & 0x40) == 0 && b >> (size - i * 7 - 1) != 0) ||
                        ((b & 0x40) != 0 && ((b | 0xFFFFFF80) >> (size - i * 7 - 1)) != -1)) {
                    // 0b0100_0000 末位的第2位是符号位 如果是0则 排出需要的位 剩下的都是0
                    // 0b0100_0000 末位的第2位是符号位 如果是1则 排出需要的位 剩下的都是必须都是1
                    throw new RuntimeException("integer too large");
                }
            }

            // 每次只取后面7位数字
            sb.insert(0, toBinaryString(b).substring(1));

            if ((b&0x80) == 0) {
                String v = sb.toString();
                if ((i*7 < size) && ((b & 0x40) != 0)) {
                    // 如果顶位是1 则填充满为1
                    v = "1111111111111111111111111111111111111111111111111111111111111111" + v;
                    v = v.substring(v.length() - 64);
                }
                s64Result.result = Long.parseUnsignedLong(sb.toString(), 2);
                s64Result.length = i + 1;
                return s64Result;
            }

        }

        throw new RuntimeException("unexpected end of section or function");
    }

    private static String toBinaryString(byte b) {
        String s = Integer.toBinaryString(b);
        if (s.length() >= 8) { return s.substring(s.length() - 8); }
        switch (s.length()) {
            case 1: return "0000000" + s;
            case 2: return "000000" + s;
            case 3: return "00000" + s;
            case 4: return "0000" + s;
            case 5: return "000" + s;
            case 6: return "00" + s;
            case 7: return "0" + s;
            case 8: return s;
        }
        throw new RuntimeException("wrong byte: " + b);
    }


    public static S64Result decodeVarInt33(byte[] data) {
        S64Result i64Result = new S64Result();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];

            if (i == 33 / 7) {
                // 如果应当是最后一个字节
                if ((b & 0x80) != 0) {
                    // 已经最后一个字节 第一位标识符不应该是1 如果是1表示后面还有，不应该
                    throw new RuntimeException("integer representation too long");
                }
                if (((b & 0x40) == 0 && b >> (33 - i * 7 - 1) != 0) ||
                        ((b & 0x40) != 0 && ((b | 0xFFFFFF80) >> (33 - i * 7 - 1)) != -1)) {
                    // 0b0100_0000 末位的第2位是符号位 如果是0则 排出需要的位 剩下的都是0
                    // 0b0100_0000 末位的第2位是符号位 如果是1则 排出需要的位 剩下的都是必须都是1
                    throw new RuntimeException("integer too large");
                }
            }

            // 每次只取后面7位数字
            sb.insert(0, toBinaryString(b).substring(1));

            if ((b & 0x80) == 0) {
                String v = sb.toString();
                if ((i*7 < 33) && ((b & 0x40) != 0)) {
                    // 如果顶位是1 则填充满为1
                    v = "1111111111111111111111111111111111111111111111111111111111111111" + v;
                    v = v.substring(v.length() - 64);
                }
                i64Result.result = Long.parseUnsignedLong(sb.toString(), 2);
                i64Result.length = i + 1;
                return i64Result;
            }

        }

        throw new RuntimeException("unexpected end of section or function");
    }

}
