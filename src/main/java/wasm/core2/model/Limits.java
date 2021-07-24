package wasm.core2.model;

import wasm.core.numeric.U32;
import wasm.core2.model.tag.LimitsTag;

public class Limits {

    private final LimitsTag tag;    // 0x00 只有min  如果是 0x01 还要读取max值
    private final U32 min;          // 最小值
    private final U32 max;          // 最大值

    public Limits(LimitsTag tag, U32 min, U32 max) {
        this.tag = tag;
        this.min = min;
        this.max = max;
    }

    public LimitsTag getTag() {
        return tag;
    }

    public U32 getMin() {
        return min;
    }

    public U32 getMax() {
        return max;
    }

    public boolean check(int wanna) {
        if (null == max) {
            return true;
        }
        return 0 <= wanna && wanna <= max.intValue();
    }

    @Override
    public String toString() {
        return "Limits{" +
                "tag=" + tag +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

    public String dump() {
        return "{min: " + min + ", max: " + (null == max ? "0" : max.toString()) + "}";
    }

}
