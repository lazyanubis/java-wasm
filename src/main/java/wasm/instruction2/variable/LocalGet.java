package wasm.instruction2.variable;

import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.index.LocalIndex;
import wasm.core.numeric.U64;

public class LocalGet implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readLocalIndex();
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof LocalIndex;

        LocalIndex a = (LocalIndex) args;

        int index = mi.getFrameOffset() + a.intValue();

        U64 value = mi.getOperand(index);

        mi.pushU64(value);
    }

}
