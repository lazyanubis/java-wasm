package wasm.model;

import wasm.model.section.*;
import wasm.model.section.util.ImportType;
import wasm.model.section.util.Uint32;

import java.util.stream.Stream;


public class Module {

    public Magic magic;      // uint32
    public Version version;    // uint32

    public Custom[] customSections; // 自定义段
    public FunctionType[] typeSections = new FunctionType[0];     // 类型段 函数签名
    public Import[] importSections = new Import[0];         // 导入 导入函数部分指向类型段的函数签名
    public TypeIndex[] functionSections = new TypeIndex[0];    // 函数段 指向类型段函数索引
    public TableType[] tableSections = new TableType[0];       // 表
    public MemoryType[] memorySections = new MemoryType[0];     // 内存
    public Global[] globalSections = new Global[0];     // 全局
    public Export[] exportSections = new Export[0];     // 导出
    public Uint32 startFunctionIndex;     // 起始函数索引 uint32 应当是函数段的索引
    public Element[] elementSections = new Element[0];     // 元素
    public Code[] codeSections = new Code[0];     // 代码
    public Data[] dataSections = new Data[0];     // 数据

    public String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("Magic: ").append(magic.value()).append("\n");
        sb.append("Version: ").append(version.value()).append("\n");

        sb.append("Type[").append(typeSections.length).append("]:").append("\n");
        for (int i = 0; i < typeSections.length; i++) {
            sb.append("  ").append(typeSections[i].dump(i)).append("\n");
        }

        sb.append("Import[").append(importSections.length).append("]:").append("\n");
        for (int i = 0; i < importSections.length; i++) {
            sb.append("  ").append(importSections[i].dump(i)).append("\n");
        }

        sb.append("Function[").append(functionSections.length).append("]:").append("\n");
        for (int i = 0; i < functionSections.length; i++) {
            sb.append("  ").append(functionSections[i].dump(i)).append("\n");
        }

        sb.append("Table[").append(tableSections.length).append("]:").append("\n");
        for (int i = 0; i < tableSections.length; i++) {
            sb.append("  ").append(tableSections[i].dump(i)).append("\n");
        }

        sb.append("Memory[").append(memorySections.length).append("]:").append("\n");
        for (int i = 0; i < memorySections.length; i++) {
            sb.append("  ").append(memorySections[i].dump(i)).append("\n");
        }

        sb.append("Global[").append(globalSections.length).append("]:").append("\n");
        for (int i = 0; i < globalSections.length; i++) {
            sb.append("  ").append(globalSections[i].dump(i)).append("\n");
        }

        sb.append("Export[").append(exportSections.length).append("]:").append("\n");
        for (int i = 0; i < exportSections.length; i++) {
            sb.append("  ").append(exportSections[i].dump(i)).append("\n");
        }

        sb.append("Start: ").append("\n");

        sb.append("Element[").append(elementSections.length).append("]:").append("\n");
        for (int i = 0; i < elementSections.length; i++) {
            sb.append("  ").append(elementSections[i].dump(i).replace("\n", "\n  ")).append("\n");
        }

        int importFunctionCount = (int) Stream.of(importSections).filter(i -> i.describe.importType == ImportType.FUNCTION).count();
        sb.append("Code[").append(codeSections.length).append("]:").append("\n");
        for (int i = 0; i < codeSections.length; i++) {
            sb.append("  ").append(codeSections[i].dump(importFunctionCount + i)).append("\n");
        }

        sb.append("Data[").append(dataSections.length).append("]:").append("\n");
        for (int i = 0; i < dataSections.length; i++) {
            sb.append("  ").append(dataSections[i].dump(i)).append("\n");
        }

        sb.append("Custom[").append(customSections.length).append("]:").append("\n");
        for (int i = 0; i < customSections.length; i++) {
            sb.append("  ").append(customSections[i].dump(i)).append("\n");
        }

        return sb.toString();
    }


}
