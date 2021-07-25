package wasm.instruction2.reference;

import wasm.core.exception.Check;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.model.type.ReferenceType;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;

import java.util.Objects;

public class RefNull implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return ReferenceType.of(reader.readByte());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        Check.requireNonNull(args);
        Check.require(args, ReferenceType.class);

        ReferenceType a = (ReferenceType) args;

        Operate.super.operate(mi, args);
    }

}
