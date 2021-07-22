package wasm.util;

public class NumberUtil {

    public static String toHex(byte value) {
        String v = Integer.toHexString(value).toUpperCase();
        if (v.length() > 2) { return v.substring(v.length() - 2); }
        if (v.length() == 1) {
            return "0" + v;
        }
        return v;
    }

    public static String toHexArray(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toHex(b));
        }
        return sb.toString();
    }

    public static String toBinary(byte value) {
        String v = toHex(value);
        return parse(v.charAt(0)) + parse(v.charAt(1));
    }

    public static String toBinaryArray(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toBinary(b));
        }
        return sb.toString();
    }

    private static String parse(char c) {
        switch (c) {
            case '0': return "0000";
            case '1': return "0001";
            case '2': return "0010";
            case '3': return "0011";
            case '4': return "0100";
            case '5': return "0101";
            case '6': return "0110";
            case '7': return "0111";
            case '8': return "1000";
            case '9': return "1001";
            case 'A': return "1010";
            case 'B': return "1011";
            case 'C': return "1100";
            case 'D': return "1101";
            case 'E': return "1110";
            case 'F': return "1111";
        }
        throw new RuntimeException("can not parse " + c);
    }

    public static String toHex(int value) {
        String v = Integer.toHexString(value).toUpperCase();
        StringBuilder sb = new StringBuilder(v);
        while (sb.length() < 8) { sb.insert(0, "0"); }
        return sb.toString();
    }

    public static String toHex(long value) {
        String v = Long.toHexString(value).toUpperCase();
        StringBuilder sb = new StringBuilder(v);
        while (sb.length() < 16) { sb.insert(0, "0"); }
        return sb.toString();
    }

    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toHex(b));
        }
        return sb.toString();
    }


    public static int clz(byte[] bytes) {
        String v = toBinaryArray(bytes);
        int count = 0;
        for (int i = 0; i < v.length(); i++) {
            if (v.charAt(i) == '0') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static int ctz(byte[] bytes) {
        String v = toBinaryArray(bytes);
        int count = 0;
        for (int i = v.length() - 1; 0 <= i; i--) {
            if (v.charAt(i) == '0') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static int popcnt(byte[] bytes) {
        String v = toBinaryArray(bytes);
        int count = 0;
        for (int i = v.length() - 1; 0 <= i; i--) {
            if (v.charAt(i) == '0') {

            } else {
                count++;
            }
        }
        return count;
    }

}
