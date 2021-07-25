package wasm.core.model.section;

import wasm.core.model.describe.ExportDescribe;

public class ExportSection {

    public final String name;             // 导出名称
    public final ExportDescribe describe; // 导出描述信息

    public ExportSection(String name, ExportDescribe describe) {
        this.name = name;
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Export{" +
                "name='" + name + '\'' +
                ", describe=" + describe +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        switch (describe.tag.value()) {
            case 0x00: // FUNCTION
                sb.append("func[").append(index).append("]: sig=").append(describe.index.dump()); break;
            case 0x01: // TABLE
                sb.append("table[").append(index).append("]: sig=").append(describe.index.dump()); break;
            case 0x02: // MEMORY
                sb.append("memory[").append(index).append("]: sig=").append(describe.index.dump()); break;
            case 0x03: // GLOBAL
                sb.append("global[").append(index).append("]: sig=").append(describe.index.dump()); break;
            default:
        }
        sb.append(" name=").append(name);

        return sb.toString();
    }

}
