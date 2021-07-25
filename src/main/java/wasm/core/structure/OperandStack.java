package wasm.core.structure;

import wasm.core.exception.Check;
import wasm.core.numeric.*;

import java.util.ArrayList;
import java.util.List;

public class OperandStack {

    private final List<USize> slots = new ArrayList<>();

    public void pushUSize(USize value) { slots.add(value); }
    public USize popUSize() { return slots.remove(slots.size() - 1); }

    public void pushU8(U8 value) { pushUSize(value); }
    public void pushU16(U16 value) { pushUSize(value); }
    public void pushU32(U32 value) { pushUSize(value); }
    public void pushU64(U64 value) { pushUSize(value); }
    public void pushBool(boolean value) { pushUSize(U32.valueOf(value ? 1 : 0)); }
    public void pushS32(int value) { pushUSize(U32.valueOf(value)); }
    public void pushS64(long value) { pushUSize(U64.valueOf(value)); }

    public U8 popU8() { return parse(popUSize(), U8.class); }
    public U16 popU16() { return parse(popUSize(), U16.class); }
    public U32 popU32() { return parse(popUSize(), U32.class); }
    public U64 popU64() { return parse(popUSize(), U64.class); }
    public boolean popBool() { return popU32().boolValue(); }
    public int popS32() { return popU32().intValue(); }
    public long popS64() { return popU64().longValue(); }

    private static <T extends USize> T parse(USize u, Class<T> c) {
        Check.require(u, c);
        return (T) u;
    }

    public int size() {
        return slots.size();
    }

    public <T extends USize> T getOperand(int index, Class<T> c) {
        Check.require(index < slots.size());
        USize u = slots.get(index);
        if (c == USize.class) { return (T) u; }
        Check.require(u, c);
        return (T) u;
    }

    public void setOperand(int index, USize value) {
        Check.require(index < slots.size());
        slots.set(index, value);
    }

    public void pushUSizes(USize[] values) {
        for (USize u : values) { pushUSize(u); }
    }

    public USize[] popUSizes(int size) {
        USize[] values = new USize[size];
        for (int i = size - 1; 0 <= i; i--) {
            values[i] = popUSize();
        }
        return values;
    }

    public void clear() {
        slots.clear();
    }

}
