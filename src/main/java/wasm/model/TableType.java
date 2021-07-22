package wasm.model;

import wasm.model.type.ReferenceType;

public class TableType {

    public ReferenceType type;

    public Limits limits;

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
