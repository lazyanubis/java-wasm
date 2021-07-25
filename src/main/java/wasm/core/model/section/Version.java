package wasm.core.model.section;

import wasm.core.numeric.U16;
import wasm.core.numeric.U32;

/**
 * Wasm定义是一个U32数字
 * 这边解析成2个Leb128的数字
 * 前一个是主版本 主版本不同互不兼容
 * 后一个是服版本 副版本大的可以兼容副版本小的
 *
 * 01 00 00 00
 *
 * 不行不行，不兼容
 */
public class Version {

    private final U16 v1;
    private final U16 v2;

    private Version(U16 v1, U16 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Version(U32 u32) {
        this( U16.valueOfU(new byte[]{ u32.getBytes()[0], u32.getBytes()[1]}),
              U16.valueOfU(new byte[]{ u32.getBytes()[2], u32.getBytes()[3]}));
    }

    public String value() {
        return v1.toHexString() + v2.toHexString();
    }

    @Override
    public String toString() {
        return "Version: " + ("0".equals(v1.toString()) ? "" : v1.toString()) + v2;
    }

}
