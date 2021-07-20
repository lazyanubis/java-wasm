package wasm.model.section;

public class Import {

    public String module; // 导入模块名
    public String name; // 导入成员名
    public ImportDescribe describe; // 描述信息

    public Import(String module, String name, ImportDescribe describe) {
        this.module = module;
        this.name = name;
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Import{" +
                "module='" + module + '\'' +
                ", name='" + name + '\'' +
                ", describe=" + describe +
                '}';
    }

    public String dump(int index) {
        StringBuilder sb = new StringBuilder();

        switch (describe.importType) {
            case FUNCTION:
                sb.append("func[").append(index).append("]: ").append(module).append(".").append(name).append(", sig=").append(describe.functionType.value());
                break;
            default:
        }

        return sb.toString();
    }

}
