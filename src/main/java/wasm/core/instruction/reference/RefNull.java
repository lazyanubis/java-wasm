package wasm.core.instruction.reference;

import wasm.core.exception.Check;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.type.ReferenceType;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;

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
