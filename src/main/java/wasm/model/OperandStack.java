package wasm.model;

import wasm.model.section.util.Uint32;
import wasm.model.section.util.Uint64;

import java.util.Stack;

public class OperandStack {

    public Stack<Uint64> slots = new Stack<>();


    public void pushUint64(Uint64 value) {
        slots.push(value);
    }

    public Uint64 popUint64() {
        return slots.pop();
    }


    public void pushInt64(long value) { pushUint64(new Uint64(Long.toBinaryString(value))); }
    public void pushUint32(Uint32 value) { pushUint64(new Uint64(value.getValue())); }
    public void pushInt32(int value) { pushUint64(new Uint64(Integer.toBinaryString(value))); }
    public void pushFloat64(double value) { pushUint64(new Uint64(Long.toBinaryString(Double.doubleToLongBits(value)))); }
    public void pushFloat32(float value) { pushUint64(new Uint64(Integer.toBinaryString(Float.floatToIntBits(value)))); }
    public void pushBoolean(boolean value) { pushUint64(new Uint64(value ? "1" : "0")); }
    public long popInt64() { return Long.parseUnsignedLong(popUint64().getValue(), 2); }
    public Uint32 popUint32() { return new Uint32(popUint64().getValue()); }
    public int popInt32() { return Integer.parseUnsignedInt(popUint64().getValue(), 2); }
    public double popFloat64() { return Double.longBitsToDouble(Long.parseUnsignedLong(popUint64().getValue(), 2)); }
    public float popFloat32() { return Float.floatToIntBits(Integer.parseUnsignedInt(popUint64().getValue(), 2)); }
    public boolean popBoolean() { return !"0".equals(popUint64().getValue()); }


}
