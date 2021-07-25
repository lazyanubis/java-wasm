package wasm.core2.model.section;

import wasm.core2.model.Dump;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.instruction.Expression;
import wasm.core3.model.index.FunctionIndex;
import wasm.core3.model.index.TableIndex;
import wasm.core.numeric.U32;
import wasm.core2.model.type.ReferenceType;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static wasm.core.util.NumberTransform.toHex;

/**
 * 这部分貌似有更新，以后再修改
 */
public class ElementSection {

    public final byte tag;    // 0x00 ~ 0x07 元素段好多种
    public final Value value; // 元素段内容

    public static abstract class Value implements Dump {
        public abstract boolean isActive();
        public abstract void init(ModuleInstance mi);
    }

    public static class Value0 extends Value {
        // 𝟶𝚡𝟶𝟶  𝑒:𝚎𝚡𝚙𝚛  𝑦∗:𝚟𝚎𝚌(𝚏𝚞𝚗𝚌𝚒𝚍𝚡) => {𝗍𝗒𝗉𝖾 𝖿𝗎𝗇𝖼𝗋𝖾𝖿,𝗂𝗇𝗂𝗍 ((𝗋𝖾𝖿.𝖿𝗎𝗇𝖼 𝑦) 𝖾𝗇𝖽)∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗍𝖺𝖻𝗅𝖾 0,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        // 第一类是函数索引 初始化
        public Expression expression;
        public FunctionIndex[] functionIndices;
        public Value0(Expression expression, FunctionIndex[] functionIndices) {
            this.expression = expression;
            this.functionIndices = functionIndices;
        }

        @Override
        public String dump() {
            return "0x00 " + expression.dump() + " [" + Stream.of(functionIndices).map(U32::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void init(ModuleInstance mi) {
            // 计算偏移
            mi.executeExpression(expression);
            int offset = mi.popU32().intValue();

            // 初始化
            for (int i = 0; i < functionIndices.length; i++) {
                // 默认是0 从初始化的函数表中取出对应的函数
                mi.getTable(TableIndex.of(0)).setElement(U32.valueOf(offset + i), mi.getFunction(functionIndices[i]));
            }
        }
    }
    public static class Value1 extends Value {
        // 𝟶𝚡𝟶𝟷  et:𝚎𝚕𝚎𝚖𝚔𝚒𝚗𝚍  𝑦∗:𝚟𝚎𝚌(𝚏𝚞𝚗𝚌𝚒𝚍𝚡) => {𝗍𝗒𝗉𝖾 et,𝗂𝗇𝗂𝗍 ((𝗋𝖾𝖿.𝖿𝗎𝗇𝖼 𝑦) 𝖾𝗇𝖽)∗,𝗆𝗈𝖽𝖾 𝗉𝖺𝗌𝗌𝗂𝗏𝖾}
        public byte elementKind;
        public FunctionIndex[] functionIndices;

        public Value1(byte elementKind, FunctionIndex[] functionIndices) {
            this.elementKind = elementKind;
            this.functionIndices = functionIndices;
        }

        @Override
        public String dump() {
            return "0x01 " + toHex(elementKind) + " [" + Stream.of(functionIndices).map(U32::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void init(ModuleInstance mi) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value2 extends Value {
        // 𝟶𝚡𝟶𝟸  𝑥:𝚝𝚊𝚋𝚕𝚎𝚒𝚍𝚡  𝑒:𝚎𝚡𝚙𝚛  et:𝚎𝚕𝚎𝚖𝚔𝚒𝚗𝚍  𝑦∗:𝚟𝚎𝚌(𝚏𝚞𝚗𝚌𝚒𝚍𝚡) => {𝗍𝗒𝗉𝖾 et,𝗂𝗇𝗂𝗍 ((𝗋𝖾𝖿.𝖿𝗎𝗇𝖼 𝑦) 𝖾𝗇𝖽)∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗍𝖺𝖻𝗅𝖾 𝑥,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public TableIndex tableIndex;
        public Expression expression;
        public byte elementKind;
        public FunctionIndex[] functionIndices;

        public Value2(TableIndex tableIndex, Expression expression, byte elementKind, FunctionIndex[] functionIndices) {
            this.tableIndex = tableIndex;
            this.expression = expression;
            this.elementKind = elementKind;
            this.functionIndices = functionIndices;
        }

        @Override
        public String dump() {
            return "0x02 " + tableIndex + " " + expression.dump() + " " + toHex(elementKind) + " [" + Stream.of(functionIndices).map(U32::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void init(ModuleInstance mi) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value3 extends Value {
        // 𝟶𝚡𝟶𝟹  et:𝚎𝚕𝚎𝚖𝚔𝚒𝚗𝚍  𝑦∗:𝚟𝚎𝚌(𝚏𝚞𝚗𝚌𝚒𝚍𝚡) => {𝗍𝗒𝗉𝖾 et,𝗂𝗇𝗂𝗍 ((𝗋𝖾𝖿.𝖿𝗎𝗇𝖼 𝑦) 𝖾𝗇𝖽)∗,𝗆𝗈𝖽𝖾 𝖽𝖾𝖼𝗅𝖺𝗋𝖺𝗍𝗂𝗏𝖾}
        public byte elementKind;
        public FunctionIndex[] functionIndices;

        public Value3(byte elementKind, FunctionIndex[] functionIndices) {
            this.elementKind = elementKind;
            this.functionIndices = functionIndices;
        }
        @Override
        public String dump() {
            return "0x03 " + toHex(elementKind) + " [" + Stream.of(functionIndices).map(U32::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void init(ModuleInstance mi) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value4 extends Value {
        // 𝟶𝚡𝟶𝟺  𝑒:𝚎𝚡𝚙𝚛  el∗:𝚟𝚎𝚌(𝚎𝚡𝚙𝚛) => {𝗍𝗒𝗉𝖾 𝖿𝗎𝗇𝖼𝗋𝖾𝖿,𝗂𝗇𝗂𝗍 el∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗍𝖺𝖻𝗅𝖾 0,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public Expression expression;
        public Expression[] expressionsArray;

        public Value4(Expression expression, Expression[] expressionsArray) {
            this.expression = expression;
            this.expressionsArray = expressionsArray;
        }
        @Override
        public String dump() {
            return "0x04 " + expression.dump() + " [" + Stream.of(expressionsArray).map(Expression::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void init(ModuleInstance mi) {
            // 计算偏移
            mi.executeExpression(expression);
            int offset = mi.popU32().intValue();

            // 初始化
            for (int i = 0; i < expressionsArray.length; i++) {
                mi.executeExpression(expressionsArray[i]);
                U32 index = mi.popU32();
                // 默认是0 从初始化的函数表中取出对应的函数
                mi.getTable(TableIndex.of(0)).setElement(U32.valueOf(offset + i), mi.getFunction(FunctionIndex.of(index)));
            }
        }
    }
    public static class Value5 extends Value {
        // 𝟶𝚡𝟶𝟻  et:𝚛𝚎𝚏𝚝𝚢𝚙𝚎  el∗:𝚟𝚎𝚌(𝚎𝚡𝚙𝚛) => {𝗍𝗒𝗉𝖾 𝑒𝑡,𝗂𝗇𝗂𝗍 el∗,𝗆𝗈𝖽𝖾 𝗉𝖺𝗌𝗌𝗂𝗏𝖾}
        public ReferenceType referenceType;
        public Expression[] expressionsArray;

        public Value5(ReferenceType referenceType, Expression[] expressionsArray) {
            this.referenceType = referenceType;
            this.expressionsArray = expressionsArray;
        }
        @Override
        public String dump() {
            return "0x05 " + referenceType.dump() + " [" + Stream.of(expressionsArray).map(Expression::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void init(ModuleInstance mi) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value6 extends Value {
        // 𝟶𝚡𝟶𝟼  𝑥:𝚝𝚊𝚋𝚕𝚎𝚒𝚍𝚡  𝑒:𝚎𝚡𝚙𝚛  et:𝚛𝚎𝚏𝚝𝚢𝚙𝚎  el∗:𝚟𝚎𝚌(𝚎𝚡𝚙𝚛) => {𝗍𝗒𝗉𝖾 𝑒𝑡,𝗂𝗇𝗂𝗍 el∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗍𝖺𝖻𝗅𝖾 𝑥,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public TableIndex tableIndex;
        public Expression expression;
        public ReferenceType referenceType;
        public Expression[] expressionsArray;

        public Value6(TableIndex tableIndex, Expression expression, ReferenceType referenceType, Expression[] expressionsArray) {
            this.tableIndex = tableIndex;
            this.expression = expression;
            this.referenceType = referenceType;
            this.expressionsArray = expressionsArray;
        }
        @Override
        public String dump() {
            return "0x06 " + tableIndex + " " + expression.dump() + " " + referenceType.dump() + " [" + Stream.of(expressionsArray).map(Expression::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void init(ModuleInstance mi) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value7 extends Value {
        // 𝟶𝚡𝟶𝟽  et:𝚛𝚎𝚏𝚝𝚢𝚙𝚎  el∗:𝚟𝚎𝚌(𝚎𝚡𝚙𝚛) => {𝗍𝗒𝗉𝖾 𝑒𝑡,𝗂𝗇𝗂𝗍 el∗,𝗆𝗈𝖽𝖾 𝖽𝖾𝖼𝗅𝖺𝗋𝖺𝗍𝗂𝗏𝖾}
        public ReferenceType referenceType;
        public Expression[] expressionsArray;

        public Value7(ReferenceType referenceType, Expression[] expressionsArray) {
            this.referenceType = referenceType;
            this.expressionsArray = expressionsArray;
        }
        @Override
        public String dump() {
            return "0x07 " + referenceType.dump() + " [" + Stream.of(expressionsArray).map(Expression::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void init(ModuleInstance mi) {
            throw new RuntimeException("how to init?");
        }
    }


    public ElementSection(byte tag, Value value) {
        this.tag = tag;
        this.value = value;
    }

    public String dump(int index) {
        return "element[" + index + "]: " + value.dump();
    }

}
