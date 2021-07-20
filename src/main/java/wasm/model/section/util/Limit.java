package wasm.model.section.util;

public class Limit {

    public LimitTag tag;

    public Uint32 min;

    public Uint32 max;

    public Limit(LimitTag tag, Uint32 min, Uint32 max) {
        this.tag = tag;
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "Limit{" +
                "tag=" + tag +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

    public String dump() {
        return "{min: " + min.value() + ", max: " + (null == max ? 0 : max.value()) + "}";
    }

}
