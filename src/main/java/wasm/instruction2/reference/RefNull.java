package wasm.instruction2.reference;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.type.ReferenceType;

public class RefNull implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return ReferenceType.of(reader.readByte());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof ReferenceType;

        ReferenceType a = (ReferenceType) args;

        Operate.super.operate(mi, args);
    }

}
