package wasm.instruction.numeric.i32;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpI32;
import wasm.model.Dump;

public class I32Const implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpI32(reader.readLeb128S32());
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;
        assert args instanceof DumpI32;

        DumpI32 a = (DumpI32) args;

        vm.operandStack.pushS32(a.value);
    }

}
