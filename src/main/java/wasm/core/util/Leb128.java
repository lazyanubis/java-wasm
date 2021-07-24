package wasm.core.util;

import static wasm.core.util.NumberTransform.parseByteByBinary;
import static wasm.core.util.NumberTransform.toBinary;

/**
 * Leb128编码解析
 */
public class Leb128 {

    /**
     * 读取结果
     */
    public static class Result {
        public final byte[] bytes;
        public final int length;
        private Result(byte[] bytes, int length) {
            assert bytes.length == 8;

            this.bytes = bytes;
            this.length = length;
        }

        static Result of(String v, int length) {
            byte[] bytes = new byte[8];
            for (int j = 0; j < 8; j++) {
                bytes[j] = parseByteByBinary(v.substring(j * 8, j * 8 + 8));
            }
            return new Result(bytes, length);
        }

    }

    /**
     * 读取无符号数字
     */
    public static Result decodeVarUint(byte[] data, int size) {
        assert null != data;
        assert size == 32 || size == 64;

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
            sb.insert(0, toBinary(b).substring(1));

            if ((b & 0x80) == 0) {
                String v = "0000000000000000000000000000000000000000000000000000000000000000" + sb;
                v = v.substring(v.length() - 64);
                return Result.of(v, i + 1);
            }
        }

        throw new RuntimeException("unexpected end of section or function");
    }


    /**
     * 读取有符号数字
     */
    public static Result decodeVarInt(byte[] data, int size) {
        assert null != data;
        assert size == 32 || size == 64;

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
                    // 0b0100_0000 末位的第2位是符号位 如果是0 则 排出需要的位 剩下的都应该是0
                    // 0b0100_0000 末位的第2位是符号位 如果是1 则 排出需要的位 剩下的都是必须都是1
                    throw new RuntimeException("integer too large");
                }
            }

            // 每次只取后面7位数字
            sb.insert(0, toBinary(b).substring(1));

            if ((b&0x80) == 0) {
                String v = sb.toString();
                if ((i * 7 < size) && ((b & 0x40) != 0)) {
                    // 如果顶位是1 则填充满为1
                    v = "1111111111111111111111111111111111111111111111111111111111111111" + v;
                } else {
                    v = "0000000000000000000000000000000000000000000000000000000000000000" + v;
                }
                v = v.substring(v.length() - 64);
                return Result.of(v, i + 1);
            }
        }

        throw new RuntimeException("unexpected end of section or function");
    }

}
