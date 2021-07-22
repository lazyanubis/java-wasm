package wasm.instruction.dump;

import wasm.model.Dump;
import wasm.model.index.LabelIndex;
import wasm.model.number.U32;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DumpBrTable implements Dump {

    private final LabelIndex[] labelIndices;

    private final LabelIndex labelIndex;

    public DumpBrTable(LabelIndex[] labelIndices, LabelIndex labelIndex) {
        this.labelIndices = labelIndices;
        this.labelIndex = labelIndex;
    }

    @Override
    public String dump() {
        return "[" + Stream.of(labelIndices).map(U32::toString).collect(Collectors.joining(",")) + "] " + labelIndex;
    }
}
