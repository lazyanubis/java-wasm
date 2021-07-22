package wasm.model;

import wasm.model.describe.ExportDescribe;

public class Export {

    public String name;
    public ExportDescribe describe;

    public Export(String name, ExportDescribe describe) {
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

    public String dump() {
        StringBuilder sb = new StringBuilder();

        switch (describe.tag.value()) {
            case 0x00: // FUNCTION
                sb.append("func[").append(describe.index.toString()).append("]: "); break;
            case 0x01: // TABLE
                sb.append("table[").append(describe.index.toString()).append("]: "); break;
            case 0x02: // MEMORY
                sb.append("memory[").append(describe.index.toString()).append("]: "); break;
            case 0x03: // GLOBAL
                sb.append("global[").append(describe.index.toString()).append("]: "); break;
            default:
        }
        sb.append("name=").append(name);

        return sb.toString();
    }
}
