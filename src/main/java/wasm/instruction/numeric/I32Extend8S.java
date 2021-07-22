package wasm.instruction.numeric;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U32;

public class I32Extend8S implements Operate {
    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        byte[] bytes = vm.popU32().getBytes();

        byte sign = ((bytes[3] & 0x80) == 0) ? 0 : (byte)0xFF;

        vm.pushS32(new U32(new byte[]{
            sign, sign, sign, bytes[3]
        }).intValue());
    }

}
