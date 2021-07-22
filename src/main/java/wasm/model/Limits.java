package wasm.model;

import wasm.model.number.U32;
import wasm.model.tag.LimitsTag;

public class Limits {

    private final LimitsTag tag; // 0x00 只有min  0x01 还有max

    private final U32 min;

    private final U32 max;

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
