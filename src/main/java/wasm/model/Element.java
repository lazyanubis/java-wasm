package wasm.model;

import wasm.core.VirtualMachine;
import wasm.instruction.Expressions;
import wasm.model.index.FunctionIndex;
import wasm.model.index.TableIndex;
import wasm.model.number.U32;
import wasm.model.type.ReferenceType;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static wasm.util.NumberUtil.toHex;

/**
 * 这部分貌似有更新，以后再修改
 */
public class Element {

    public byte tag; // 0x00 ~ 0x07

    public Value value;

    public static abstract class Value implements Dump {
        public abstract boolean isActive();
        public abstract void init(VirtualMachine vm);
    }

    public static class Value0 extends Value {
        // 𝟶𝚡𝟶𝟶  𝑒:𝚎𝚡𝚙𝚛  𝑦∗:𝚟𝚎𝚌(𝚏𝚞𝚗𝚌𝚒𝚍𝚡) => {𝗍𝗒𝗉𝖾 𝖿𝗎𝗇𝖼𝗋𝖾𝖿,𝗂𝗇𝗂𝗍 ((𝗋𝖾𝖿.𝖿𝗎𝗇𝖼 𝑦) 𝖾𝗇𝖽)∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗍𝖺𝖻𝗅𝖾 0,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        // 第一类是函数索引 初始化
        public Expressions expressions;
        public FunctionIndex[] functionIndices;
        public Value0(Expressions expressions, FunctionIndex[] functionIndices) {
            this.expressions = expressions;
            this.functionIndices = functionIndices;
        }

        @Override
        public String dump() {
            return "0x00 " + expressions.dump() + " [" + Stream.of(functionIndices).map(U32::toString).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void init(VirtualMachine vm) {
            // 计算偏移
            vm.executeExpressions(expressions);
            int offset = vm.operandStack.popU32().intValue();

            // 初始化
            for (int i = 0; i < functionIndices.length; i++) {
                // 默认是0 从初始化的函数表中取出对应的函数
                vm.tables[0].setElement(offset + i, vm.functions[functionIndices[i].intValue()]);
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
            return "0x01 " + toHex(elementKind) + " [" + Stream.of(functionIndices).map(U32::toString).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void init(VirtualMachine vm) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value2 extends Value {
        // 𝟶𝚡𝟶𝟸  𝑥:𝚝𝚊𝚋𝚕𝚎𝚒𝚍𝚡  𝑒:𝚎𝚡𝚙𝚛  et:𝚎𝚕𝚎𝚖𝚔𝚒𝚗𝚍  𝑦∗:𝚟𝚎𝚌(𝚏𝚞𝚗𝚌𝚒𝚍𝚡) => {𝗍𝗒𝗉𝖾 et,𝗂𝗇𝗂𝗍 ((𝗋𝖾𝖿.𝖿𝗎𝗇𝖼 𝑦) 𝖾𝗇𝖽)∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗍𝖺𝖻𝗅𝖾 𝑥,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public TableIndex tableIndex;
        public Expressions expressions;
        public byte elementKind;
        public FunctionIndex[] functionIndices;

        public Value2(TableIndex tableIndex, Expressions expressions, byte elementKind, FunctionIndex[] functionIndices) {
            this.tableIndex = tableIndex;
            this.expressions = expressions;
            this.elementKind = elementKind;
            this.functionIndices = functionIndices;
        }

        @Override
        public String dump() {
            return "0x02 " + tableIndex + " " + expressions.dump() + " " + toHex(elementKind) + " [" + Stream.of(functionIndices).map(U32::toString).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void init(VirtualMachine vm) {
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
            return "0x03 " + toHex(elementKind) + " [" + Stream.of(functionIndices).map(U32::toString).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void init(VirtualMachine vm) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value4 extends Value {
        // 𝟶𝚡𝟶𝟺  𝑒:𝚎𝚡𝚙𝚛  el∗:𝚟𝚎𝚌(𝚎𝚡𝚙𝚛) => {𝗍𝗒𝗉𝖾 𝖿𝗎𝗇𝖼𝗋𝖾𝖿,𝗂𝗇𝗂𝗍 el∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗍𝖺𝖻𝗅𝖾 0,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public Expressions expressions;
        public Expressions[] expressionsArray;

        public Value4(Expressions expression, Expressions[] expressionsArray) {
            this.expressions = expression;
            this.expressionsArray = expressionsArray;
        }
        @Override
        public String dump() {
            return "0x04 " + expressions.dump() + " [" + Stream.of(expressionsArray).map(Expressions::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void init(VirtualMachine vm) {
            // 计算偏移
            vm.executeExpressions(expressions);
            int offset = vm.operandStack.popU32().intValue();

            // 初始化
            for (int i = 0; i < expressionsArray.length; i++) {
                vm.executeExpressions(expressionsArray[i]);
                int index = vm.operandStack.popU32().intValue();
                // 默认是0 从初始化的函数表中取出对应的函数
                vm.tables[0].setElement(offset + i, vm.functions[index]);
            }
        }
    }
    public static class Value5 extends Value {
        // 𝟶𝚡𝟶𝟻  et:𝚛𝚎𝚏𝚝𝚢𝚙𝚎  el∗:𝚟𝚎𝚌(𝚎𝚡𝚙𝚛) => {𝗍𝗒𝗉𝖾 𝑒𝑡,𝗂𝗇𝗂𝗍 el∗,𝗆𝗈𝖽𝖾 𝗉𝖺𝗌𝗌𝗂𝗏𝖾}
        public ReferenceType referenceType;
        public Expressions[] expressionsArray;

        public Value5(ReferenceType referenceType, Expressions[] expressionsArray) {
            this.referenceType = referenceType;
            this.expressionsArray = expressionsArray;
        }
        @Override
        public String dump() {
            return "0x05 " + referenceType.dump() + " [" + Stream.of(expressionsArray).map(Expressions::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void init(VirtualMachine vm) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value6 extends Value {
        // 𝟶𝚡𝟶𝟼  𝑥:𝚝𝚊𝚋𝚕𝚎𝚒𝚍𝚡  𝑒:𝚎𝚡𝚙𝚛  et:𝚛𝚎𝚏𝚝𝚢𝚙𝚎  el∗:𝚟𝚎𝚌(𝚎𝚡𝚙𝚛) => {𝗍𝗒𝗉𝖾 𝑒𝑡,𝗂𝗇𝗂𝗍 el∗,𝗆𝗈𝖽𝖾 𝖺𝖼𝗍𝗂𝗏𝖾 {𝗍𝖺𝖻𝗅𝖾 𝑥,𝗈𝖿𝖿𝗌𝖾𝗍 𝑒}}
        public TableIndex tableIndex;
        public Expressions expressions;
        public ReferenceType referenceType;
        public Expressions[] expressionsArray;

        public Value6(TableIndex tableIndex, Expressions expression, ReferenceType referenceType, Expressions[] expressionsArray) {
            this.tableIndex = tableIndex;
            this.expressions = expression;
            this.referenceType = referenceType;
            this.expressionsArray = expressionsArray;
        }
        @Override
        public String dump() {
            return "0x06 " + tableIndex + " " + expressions.dump() + " " + referenceType.dump() + " [" + Stream.of(expressionsArray).map(Expressions::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public void init(VirtualMachine vm) {
            throw new RuntimeException("how to init?");
        }
    }
    public static class Value7 extends Value {
        // 𝟶𝚡𝟶𝟽  et:𝚛𝚎𝚏𝚝𝚢𝚙𝚎  el∗:𝚟𝚎𝚌(𝚎𝚡𝚙𝚛) => {𝗍𝗒𝗉𝖾 𝑒𝑡,𝗂𝗇𝗂𝗍 el∗,𝗆𝗈𝖽𝖾 𝖽𝖾𝖼𝗅𝖺𝗋𝖺𝗍𝗂𝗏𝖾}
        public ReferenceType referenceType;
        public Expressions[] expressionsArray;

        public Value7(ReferenceType referenceType, Expressions[] expressionsArray) {
            this.referenceType = referenceType;
            this.expressionsArray = expressionsArray;
        }
        @Override
        public String dump() {
            return "0x07 " + referenceType.dump() + " [" + Stream.of(expressionsArray).map(Expressions::dump).collect(Collectors.joining(",")) + "]";
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public void init(VirtualMachine vm) {
            throw new RuntimeException("how to init?");
        }
    }


    public Element(byte tag, Value value) {
        this.tag = tag;
        this.value = value;
    }

    public String dump(int index) {
        return "element[" + index + "]: " + value.dump();
    }
}
