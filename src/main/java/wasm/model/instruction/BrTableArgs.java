package wasm.model.instruction;

import wasm.model.section.util.Dump;
import wasm.model.section.util.Uint32;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static wasm.model.instruction.Instruction.toHex;

public class BrTableArgs implements Dump {

    public Uint32[] labels;

    public Uint32 omit;

    @Override
    public String dump() {
        return toHex((byte) labels.length) + " " + Stream.of(labels).map(l -> toHex((byte) l.value())).collect(Collectors.joining(" ")) + " " + toHex((byte)omit.value());
    }

    @Override
    public String toString() {
        return Stream.of(labels).map(l -> String.valueOf(l.value())).collect(Collectors.joining(" ")) + " " + omit.value();
    }
}
