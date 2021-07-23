package wasm.core;

import wasm.model.TableType;

public class Table {

    public TableType type;

    private VirtualMachineFunction[] elements;

    public Table(TableType type) {
        this.type = type;
        this.elements = new VirtualMachineFunction[type.limits.getMin().intValue()];
    }

    public VirtualMachineFunction getElement(int index) {
        if (elements.length <= index) {
            throw new RuntimeException("too large");
        }
        return elements[index];
    }

    public void setElement(int index, VirtualMachineFunction function) {
        if (elements.length <= index) {
            throw new RuntimeException("too large");
        }
        elements[index] = function;
    }

    public int size() {
        return elements.length;
    }


}
