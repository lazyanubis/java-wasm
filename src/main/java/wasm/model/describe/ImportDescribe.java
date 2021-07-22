package wasm.model2.section;

import wasm.model.TableType;
import wasm.model2.section.util.ImportType;
import wasm.model2.section.util.Uint32;

public class ImportDescribe {

    public ImportType importType;

    public Uint32 functionType; // 如果是导入函数 指向类型段的函数索引
    public TableType table; // 如果是导入表 表信息
    public MemoryType memory; // 如果是导入内存 内存信息
    public Global global; // 如果是导入全局变量 变量信息

    public ImportDescribe(ImportType importType, Uint32 functionType, TableType table, MemoryType memory, Global global) {
        this.importType = importType;
        this.functionType = functionType;
        this.table = table;
        this.memory = memory;
        this.global = global;
    }

    @Override
    public String toString() {
        return "ImportDescribe{" +
                "importType=" + importType +
                ", functionType=" + functionType +
                ", table=" + table +
                ", memory=" + memory +
                ", global=" + global +
                '}';
    }
}
