package wasm.core;

import wasm.model.number.U32;
import wasm.model.number.U64;

import java.util.Stack;

public class OperandStack {

    public Stack<U64> slots = new Stack<>();


    public void pushU64(U64 value) { slots.push(value); }

    public U64 popU64() { return slots.pop(); }

    public void pushS64(long value) { pushU64(new U64(value)); }
    public void pushU32(U32 value) { pushU64(new U64(value)); }
    public void pushS32(int value) { pushU64(new U64(value)); }
    public void pushBool(boolean value) { pushU64(new U64(value ? 1 : 0)); }
    public long popS64() { return popU64().longValue(); }
    public U32 popU32() { return popU64().u32(); }
    public int popS32() { return popU64().u32().intValue(); }
    public boolean popBool() { return popU64().parseBool(); }


}
