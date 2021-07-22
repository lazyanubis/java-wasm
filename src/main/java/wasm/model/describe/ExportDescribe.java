package wasm.model.describe;

import wasm.model.number.U32;
import wasm.model.tag.PortTag;

public class ExportDescribe {

    public PortTag tag;

    public U32 index; // funcidx tableidx memidx globalidx

    public ExportDescribe(PortTag tag, U32 index) {
        this.tag = tag;
        this.index = index;
    }

    @Override
    public String toString() {
        return "ExportDescribe{" +
                "tag=" + tag +
                ", index=" + index +
                '}';
    }
}
