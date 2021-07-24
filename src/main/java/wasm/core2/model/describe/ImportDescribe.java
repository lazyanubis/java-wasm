package wasm.core2.model.describe;

import wasm.core2.model.Dump;
import wasm.core3.model.index.TypeIndex;
import wasm.core3.model.tag.PortTag;
import wasm.core2.model.type.GlobalType;
import wasm.core2.model.section.MemoryType;
import wasm.core2.model.section.TableType;

public class ImportDescribe {

    public final PortTag tag;

    public final Value value;

    public static abstract class Value implements Dump { }

    public static class Function extends Value {
        public TypeIndex typeIndex; // 如果是导入函数 指向类型段的函数索引

        public Function(TypeIndex typeIndex) {
            this.typeIndex = typeIndex;
        }

        @Override
        public String dump() { return typeIndex.dump(); }

    }
    public static class Table extends Value {
        public TableType table; // 如果是导入表 表信息

        public Table(TableType table) {
            this.table = table;
        }

        @Override
        public String dump() { return table.dump(); }

    }
    public static class Memory extends Value {
        public MemoryType memory; // 如果是导入内存 内存信息

        public Memory(MemoryType memory) {
            this.memory = memory;
        }

        @Override
        public String dump() { return memory.dump(); }

    }
    public static class Global extends Value {
        public GlobalType global; // 如果是导入全局变量 变量信息

        public Global(GlobalType global) {
            this.global = global;
        }

        @Override
        public String dump() { return global.dump(); }

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
