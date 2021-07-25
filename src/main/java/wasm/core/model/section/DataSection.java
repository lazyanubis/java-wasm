package wasm.core.model.section;

import wasm.core.model.Dump;
import wasm.core.util.NumberTransform;
import wasm.core2.structure.ModuleInstance;
import wasm.core.instruction.Expression;
import wasm.core.model.index.MemoryIndex;
import wasm.core.numeric.U32;

import static wasm.core.util.NumberTransform.toHexArray;

public class DataSection {

    public byte tag;    // 数据标签
    public Value value; // 数据内容

    public static abstract class Value implements Dump {
        public abstract void initMemory(ModuleInstance mi);
    }

    public static class Value0 extends Value {
        // 𝟶𝚡𝟶𝟶  𝑒:𝚎𝚡𝚙𝚛  𝑏∗:𝚟𝚎𝚌(𝚋𝚢𝚝𝚎) => {𝗂𝗇𝗂𝗍 𝑏∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗆𝖾𝗆𝗈𝗋𝗒 0,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public Expression expression;
        public byte[] bytes;

        public Value0(Expression expression, byte[] bytes) {
            this.expression = expression;
            this.bytes = bytes;
        }

        @Override
        public String dump() {
            return "0x00 " + expression.dump() + " [" + NumberTransform.toHexArray(bytes) + "]";
        }

        @Override
        public void initMemory(ModuleInstance mi) {
            mi.executeExpression(expression);
            U32 offset = mi.popU32();

            mi.write(MemoryIndex.of(0), offset.u64(), bytes);
        }

    }
    public static class Value1 extends Value {
        // 𝟶𝚡𝟶𝟷  𝑏∗:𝚟𝚎𝚌(𝚋𝚢𝚝𝚎) => {𝗂𝗇𝗂𝗍 𝑏∗,𝗆𝗈𝖽𝖾 𝗉𝖺𝗌𝗌𝗂𝗏𝖾}
        public byte[] bytes;

        public Value1(byte[] bytes) {
            this.bytes = bytes;
        }

        @Override
        public String dump() {
            return "0x01 [" +  toHexArray(bytes) + "]";
        }

        // 非主动初始化内存
        @Override
        public void initMemory(ModuleInstance mi) { }
    }
    public static class Value2 extends Value {
        // 𝟶𝚡𝟶𝟸  𝑥:𝚖𝚎𝚖𝚒𝚍𝚡  𝑒:𝚎𝚡𝚙𝚛  𝑏∗:𝚟𝚎𝚌(𝚋𝚢𝚝𝚎) => {𝗂𝗇𝗂𝗍 𝑏∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗆𝖾𝗆𝗈𝗋𝗒 𝑥,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public MemoryIndex memoryIndex;
        public Expression expression;
        public byte[] bytes;

        public Value2(MemoryIndex memoryIndex, Expression expression, byte[] bytes) {
            this.memoryIndex = memoryIndex;
            this.expression = expression;
            this.bytes = bytes;
        }

        @Override
        public String dump() {
            return "0x02 " + memoryIndex.toString() + " " + expression.dump() + " [" +  toHexArray(bytes) + "]";
        }

        @Override
        public void initMemory(ModuleInstance mi) {
            int index = memoryIndex.intValue();

            mi.executeExpression(expression);
            U32 offset = mi.popU32();

            mi.write(MemoryIndex.of(index), offset.u64(), bytes);
        }
    }


    public DataSection(byte tag, Value value) {
        this.tag = tag;
        this.value = value;
    }

    public String dump(int index) {
        return "data[" + index + "]: " + value.dump();
    }

}
