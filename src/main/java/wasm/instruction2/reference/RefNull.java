package wasm.instruction2.reference;

import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core2.model.type.ReferenceType;

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
