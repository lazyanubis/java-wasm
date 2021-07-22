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

}
