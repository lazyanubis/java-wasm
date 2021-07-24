package wasm.core.model.describe;

import wasm.core.model.index.TypeIndex;
import wasm.core.model.tag.PortTag;
import wasm.core.model.type.GlobalType;
import wasm.model2.MemoryType;
import wasm.model2.TableType;

public class ImportDescribe {

    public final PortTag tag;

    public final Value value;

    public static abstract class Value { }

    public static class Function extends Value {
        public TypeIndex typeIndex; // 如果是导入函数 指向类型段的函数索引

        public Function(TypeIndex typeIndex) {
            this.typeIndex = typeIndex;
        }

        @Override
        public String toString() { return typeIndex.toString(); }

    }
    public static class Table extends Value {
        public TableType table; // 如果是导入表 表信息

        public Table(TableType table) {
            this.table = table;
        }

        @Override
        public String toString() { return table.toString(); }

    }
    public static class Memory extends Value {
        public MemoryType memory; // 如果是导入内存 内存信息

        public Memory(MemoryType memory) {
            this.memory = memory;
        }

        @Override
        public String toString() { return memory.toString(); }

    }
    public static class Global extends Value {
        public GlobalType global; // 如果是导入全局变量 变量信息

        public Global(GlobalType global) {
            this.global = global;
        }

        @Override
        public String toString() { return global.toString(); }

    }


    public ImportDescribe(PortTag tag, Value value) {
        this.tag = tag;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ImportDescribe{" +
                "tag=" + tag +
                ", value=" + value +
                '}';
    }

}
