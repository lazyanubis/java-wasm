package wasm.core2.model.section;

import wasm.core2.instruction.Expression;
import wasm.core2.model.type.GlobalType;

public class GlobalSection {

    public GlobalType type; // 变量类型
    public Expression init; // 初始化表达式

    public GlobalSection(GlobalType type, Expression init) {
        this.type = type;
        this.init = init;
    }

    @Override
    public String toString() {
        return "Global{" +
                "type=" + type +
                ", init=" + init +
                '}';
    }

    public String dump(int index) {
        return "global[" + index + "]: " + type.dump() + " " + init.dump();
    }

}
