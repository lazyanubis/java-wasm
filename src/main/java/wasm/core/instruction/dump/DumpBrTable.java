package wasm.core.instruction.dump;

import wasm.core.model.Dump;
import wasm.core.model.index.LabelIndex;
import wasm.core.numeric.U32;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DumpBrTable implements Dump {

    public final LabelIndex[] labelIndices;

    public final LabelIndex omit;

    public DumpBrTable(LabelIndex[] labelIndices, LabelIndex omit) {
        this.labelIndices = labelIndices;
        this.omit = omit;
    }

    @Override
    public String dump() {
        return "[" + Stream.of(labelIndices).map(U32::dump).collect(Collectors.joining(",")) + "] " + omit;
    }
}
