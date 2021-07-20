package wasm.model.section;

import wasm.model.section.util.Limit;
import wasm.model.section.util.ValueType;

public class TableType {

    public ValueType elementType; // 目前只能是函数类型

    public Limit limit;

    public TableType(ValueType elementType, Limit limit) {
        this.elementType = elementType;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "TableType{" +
                "elementType=" + elementType +
                ", limit=" + limit +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("table[").append(index).append("]: ").append(limit.dump());

        return sb.toString();
    }
}
