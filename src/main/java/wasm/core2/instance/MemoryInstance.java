package wasm.core2.instance;

import wasm.core.numeric.U32;
import wasm.core.numeric.U64;
import wasm.core2.model.section.MemoryType;
import wasm.core2.structure.Memory;

import java.util.Objects;

import static wasm.core3.util.ConstNumber.MEMORY_MAX_PAGE_COUNT;
import static wasm.core3.util.ConstNumber.MEMORY_PAGE_SIZE;

/**
 * 由于标准中每个内存页64KB，每次增加太大了，特别是每增加一页就多64KB
 * 出于持久化内存的考虑，应当记录当前内存页数量，并且将有数据的部分进行持久化
 * 有必要标记最大的不是0的内存的位置，实例化的时候只要从0实例化到这个位置就可以了
 */
public class MemoryInstance implements Memory {

    private final MemoryType type;  // 内存类型
    private byte[] data;            // 数据内存

    private int maxNonePosition = 0; // 最大非0byte的位置，写入内存时要进行判断调整

    public MemoryInstance(MemoryType type) {
        this.type = type;
        // 先按照最小的初始化
        this.data = new byte[MEMORY_PAGE_SIZE * type.getMin().intValue()];
    }

    @Override
    public MemoryType type() {
        return type;
    }

    @Override
    public U32 size() {
        return U32.valueOf(this.data.length / MEMORY_PAGE_SIZE);
    }

    @Override
    public U32 grow(U32 grow) {
        U32 old = size();

        int wanna = old.intValue() + grow.intValue();

        if (!type.check(wanna)) {
            return U32.valueOf(-1);
        }

        if (wanna > MEMORY_MAX_PAGE_COUNT) {
            return U32.valueOf(-1);
        }

        byte[] data = new byte[MEMORY_PAGE_SIZE * wanna];

        System.arraycopy(this.data, 0, data, 0, this.data.length);

        this.data = data;

        return old;
    }

    @Override
    public void read(U64 offset, byte[] buffer) {
        Objects.requireNonNull(buffer);
        if (buffer.length == 0) { return; }

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

    @Override
    public void write(U64 offset, byte[] data) {
        Objects.requireNonNull(data);
        if (data.length == 0) { return; }

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

        updateMaxNonePosition(end - 1);
    }

    private void updateMaxNonePosition(int end) {
        if (end < maxNonePosition) { return; }
        // 需要更新的字节超出标记的位置，从end开始往前找最近的非0位置
        for (int p = end; 0 <= p; p--) {
            if (data[p] != 0) {
                maxNonePosition = p;
                return;
            }
        }
        maxNonePosition = 0;
    }

}
