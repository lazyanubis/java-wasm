package wasm.model.section;

import wasm.model.section.util.ValueType;
import wasm.model.section.util.VariableType;

public class GlobalType {

    public ValueType valueType;

    public VariableType variableType;

    public GlobalType(ValueType valueType, VariableType variableType) {
        this.valueType = valueType;
        this.variableType = variableType;
    }

    @Override
    public String toString() {
        return "GlobalType{" +
                "valueType=" + valueType +
                ", variableType=" + variableType +
                '}';
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append("{type: ").append(valueType.name()).append(", mut: ").append(variableType.name()).append("}");

        return sb.toString();
    }
}
