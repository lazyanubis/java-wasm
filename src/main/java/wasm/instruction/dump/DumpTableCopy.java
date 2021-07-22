package wasm.instruction.dump;

import wasm.model.Dump;
import wasm.model.index.TableIndex;

public class DumpTableCopy implements Dump {

    private final TableIndex tableIndex1;

    private final TableIndex tableIndex2;

    public DumpTableCopy(TableIndex tableIndex1, TableIndex tableIndex2) {
        this.tableIndex1 = tableIndex1;
        this.tableIndex2 = tableIndex2;
    }

    @Override
    public String dump() {
        return tableIndex1.dump() + " " + tableIndex2.dump();
    }

}
