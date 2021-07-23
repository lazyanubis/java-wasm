package wasm.core;

import wasm.model.MemoryType;
import wasm.model.number.U32;

import static wasm.util.Const.MEMORY_MAX_PAGE_COUNT;
import static wasm.util.Const.MEMORY_PAGE_SIZE;

public class Memory {

    private final MemoryType memoryType;

    private byte[] data;

    public Memory(MemoryType memoryType) {
        this.memoryType = memoryType;
        this.data = new byte[MEMORY_PAGE_SIZE * memoryType.getMin().intValue()]; // 先按照最小的初始化
    }

    /**
     * 当前内存的页数
     */
    public U32 size() {
        return new U32(this.data.length / MEMORY_PAGE_SIZE);
    }

    public U32 grow(U32 grow) {
        U32 old = size();

        int wanna = old.intValue() + grow.intValue();

        if (null != memoryType.getMax() && wanna > memoryType.getMax().intValue()) {
            return new U32(-1);
        }

        if (wanna > MEMORY_MAX_PAGE_COUNT) {
            return new U32(-1);
        }

        byte[] data = new byte[MEMORY_PAGE_SIZE * wanna];

        System.arraycopy(this.data, 0, data, 0, this.data.length);

        this.data = data;

        return old;
    }

    public void read(U32 offset, byte[] buffer) {
        int max = this.data.length;

        int start = offset.intValue();

        if (start < 0 || max <= start) {
            throw new RuntimeException("start position is invalid: " + start + " for max " + max);
        }

        int end = start + buffer.length;

        if (end < 0 || max <= end) {
            throw new RuntimeException("end position is invalid: " + end + " for max " + max);
        }

        System.arraycopy(this.data, start, buffer, 0, buffer.length);
    }

    public void write(U32 offset, byte[] data) {
        int max = this.data.length;

        int start = offset.intValue();

        if (start < 0 || max <= start) {
            throw new RuntimeException("start position is invalid: " + start + " for max " + max);
        }

        int end = start + data.length;

        if (end < 0 || max <= end) {
            throw new RuntimeException("end position is invalid: " + end + " for max " + max);
        }

        System.arraycopy(data, 0, this.data, start, data.length);
    }


}
