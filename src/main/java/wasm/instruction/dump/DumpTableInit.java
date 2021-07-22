package wasm.instruction.dump;

import wasm.model.Dump;
import wasm.model.index.ElementIndex;
import wasm.model.index.TableIndex;

public class DumpTableInit implements Dump {

    private final ElementIndex elementIndex;

    private final TableIndex tableIndex;

    public DumpTableInit(ElementIndex elementIndex, TableIndex tableIndex) {
        this.elementIndex = elementIndex;
        this.tableIndex = tableIndex;
    }

    @Override
    public String dump() {
        return elementIndex.dump() + " " + tableIndex.dump();
    }

}
