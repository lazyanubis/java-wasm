package wasm.model.section;

import wasm.model.section.util.ValueType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FunctionType {

    public ValueType tag;

    public ValueType[] parameterTypes;

    public ValueType[] resultTypes;

    public FunctionType(ValueType tag, ValueType[] parameterTypes, ValueType[] resultTypes) {
        this.tag = tag;

        assert tag == ValueType.Function;

        this.parameterTypes = parameterTypes;
        this.resultTypes = resultTypes;
    }

    @Override
    public String toString() {
        return "FunctionType{" +
                "tag=" + tag +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", resultTypes=" + Arrays.toString(resultTypes) +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("type[").append(index).append("]: (").append(Arrays.stream(parameterTypes).map(Enum::name).collect(Collectors.joining(","))).append(")->(").append(Arrays.stream(resultTypes).map(Enum::name).collect(Collectors.joining(","))).append(")");

        return sb.toString();
    }

}
