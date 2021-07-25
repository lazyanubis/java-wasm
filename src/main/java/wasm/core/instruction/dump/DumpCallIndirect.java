package wasm.core.instruction.dump;

import wasm.core.model.Dump;
import wasm.core.model.index.TableIndex;
import wasm.core.model.index.TypeIndex;

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
