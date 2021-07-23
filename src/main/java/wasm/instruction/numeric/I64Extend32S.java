package wasm.instruction.numeric;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.number.U64;

public class I64Extend32S implements Operate {
    @Override
    public Dump read(WasmReader reader) {
        return null;
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        byte[] bytes = vm.operandStack.popU64().getBytes();

        byte sign = ((bytes[4] & 0x80) == 0) ? 0 : (byte)0xFF;

        vm.operandStack.pushS64(new U64(new byte[]{
            sign, sign, sign, sign,
            bytes[4], bytes[5], bytes[6], bytes[7]
        }).longValue());
    }

}
