package wasm.core2.structure;

import wasm.core.numeric.U32;
import wasm.core.numeric.U64;

import java.util.ArrayList;
import java.util.List;

public class OperandStack {

    private final List<U64> slots = new ArrayList<>();

    public void pushU64(U64 value) { slots.add(value); }
    public U64 popU64() { return slots.remove(slots.size() - 1); }

    public void pushS64(long value) { pushU64(U64.valueOf(value)); }
    public void pushU32(U32 value) { pushU64(U64.valueOf(value)); }
    public void pushS32(int value) { pushU64(U64.valueOf(value)); }
    public void pushBool(boolean value) { pushU64(U64.valueOf(value ? 1 : 0)); }
    public long popS64() { return popU64().longValue(); }
    public U32 popU32() { return popU64().u32(); }
    public int popS32() { return popU64().u32().intValue(); }
    public boolean popBool() { return popU64().boolValue(); }

    public int size() {
        return slots.size();
    }

    public U64 getOperand(int index) {
        assert index < slots.size();
        return slots.get(index);
    }

    public void setOperand(int index, U64 value) {
        assert index < slots.size();
        slots.set(index, value);
    }

    public void pushU64s(U64[] values) {
        for (U64 u : values) { pushU64(u); }
    }

    public U64[] popU64s(int size) {
        U64[] values = new U64[size];
        for (int i = size - 1; 0 <= i; i--) {
            values[i] = popU64();
        }
        return values;
    }

}
