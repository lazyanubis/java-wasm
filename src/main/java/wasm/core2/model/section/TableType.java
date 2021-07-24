package wasm.core2.model.section;

import wasm.core2.model.type.ReferenceType;
import wasm.core2.model.Limits;

public class TableType {

    public final ReferenceType type;    // 引用类型
    public final Limits limits;         // 表限制

    public TableType(ReferenceType type, Limits limits) {
        this.type = type;
        this.limits = limits;
    }

    @Override
    public String toString() {
        return "TableType{" +
                "type=" + type +
                ", limits=" + limits +
                '}';
    }

    public String dump(int index) {
        return "table[" + index + "]: " + limits.dump();
    }

}
