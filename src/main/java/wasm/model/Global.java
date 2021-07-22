package wasm.model;

import wasm.instruction.Expressions;
import wasm.model.type.GlobalType;

public class Global {

    public GlobalType type;

    public Expressions init;

    public Global(GlobalType type, Expressions init) {
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
