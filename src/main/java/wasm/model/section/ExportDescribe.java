package wasm.model.section;

import wasm.model.section.util.ExportType;
import wasm.model.section.util.Uint32;

public class ExportDescribe {

    public ExportType type;

    public Uint32 index;

    public ExportDescribe(ExportType type, Uint32 index) {
        this.type = type;
        this.index = index;
    }

    @Override
    public String toString() {
        return "ExportDescribe{" +
                "type=" + type +
                ", index=" + index +
                '}';
    }
}
