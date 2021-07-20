package wasm.model.section;

import wasm.model.section.util.Uint32;

public class Data {

    public Uint32 memory;

    public Object offset;

    public byte[] init;

    public Data(Uint32 memory, Object offset, byte[] init) {
        this.memory = memory;
        this.offset = offset;
        this.init = init;
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        sb.append("data[").append(index).append("]: ").append("memory=").append(memory.value());

        return sb.toString();
    }
}
