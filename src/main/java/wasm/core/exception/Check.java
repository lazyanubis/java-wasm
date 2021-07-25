package wasm.core.exception;

import java.util.Objects;

public class Check {

    public static void require(Object args, Class<?> c) {
        if (args.getClass() != c) {
            throw new WasmException("args != " + c.getName());
        }
    }

    public static void require(int args, int... values) {
        for (int v : values) {
            if (args == v) {
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(Integer.valueOf(i)).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        throw new WasmException("args != [" + sb + "]");
    }

    public static void require(boolean value) {
        if (!value) {
            throw new WasmException("false");
        }
    }

    public static void requireNonNull(Object v) {
        Objects.requireNonNull(v);
    }

}
