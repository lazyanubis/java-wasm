package wasm.instruction.dump;

import wasm.model.Dump;
import wasm.model.index.TableIndex;
import wasm.model.index.TypeIndex;

public class DumpCallIndirect implements Dump {

    public final TypeIndex typeIndex;

    public final TableIndex tableIndex;

    public DumpCallIndirect(TypeIndex typeIndex, TableIndex tableIndex) {
        this.typeIndex = typeIndex;
        this.tableIndex = tableIndex;
    }

    @Override
    public String dump() {
        return "typeidx: " +  typeIndex.dump() + "  tableidx: " + tableIndex.dump();
    }

}
