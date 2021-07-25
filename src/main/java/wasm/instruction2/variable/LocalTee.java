package wasm.instruction2.variable;

import wasm.core.exception.Check;
import wasm.core.numeric.U64;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core3.model.index.LocalIndex;

import java.util.Objects;

public class LocalTee implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLocalIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, LocalIndex.class);

        LocalIndex a = (LocalIndex) args;

        int index = mi.getFrameOffset() + a.intValue();

        U64 value = mi.popU64();

        mi.pushU64(value); // 再压回去

        mi.setOperand(index, value);
    }

}
