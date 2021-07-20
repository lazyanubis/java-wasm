package wasm.model.section;

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

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        switch (describe.type) {
            case MEMORY: sb.append("memory[").append(describe.index.value()).append("]: "); break;
            case GLOBAL: sb.append("global[").append(describe.index.value()).append("]: "); break;
            case FUNCTION: sb.append("func[").append(describe.index.value()).append("]: "); break;
            case TABLE: sb.append("table[").append(describe.index.value()).append("]: "); break;
            default:
        }
        sb.append("name=").append(name);

        return sb.toString();
    }
}
