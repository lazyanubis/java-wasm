package wasm.core.model.describe;

import wasm.core.numeric.U32;
import wasm.core.model.tag.PortTag;

public class ExportDescribe {

    public final PortTag tag;   // 导出标识4种类型
    public final U32 index;     // funcidx tableidx memidx globalidx

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
