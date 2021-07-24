package wasm.instruction2.dump;

import wasm.core2.model.Dump;
import wasm.core2.model.index.ElementIndex;
import wasm.core2.model.index.TableIndex;

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