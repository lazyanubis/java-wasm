package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.VirtualMachineFunction;
import wasm.core.WasmReader;
import wasm.instruction.Instruction;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpCallIndirect;
import wasm.model.Dump;
import wasm.model.FunctionType;
import wasm.model.index.TypeIndex;

public class CallIndirect implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpCallIndirect(reader.readTypeIndex(), reader.readTableIndex());
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;
        assert args instanceof DumpCallIndirect;

        DumpCallIndirect d = (DumpCallIndirect) args;

        int i = vm.operandStack.popU32().intValue();
        // which table ?
        if (i >= vm.tables[0].size()) {
            throw new RuntimeException("to large");
        }

        VirtualMachineFunction function = vm.tables[0].getElement(i);

        TypeIndex typeIndex = d.typeIndex;
        FunctionType functionType = vm.module.typeSections[typeIndex.intValue()];
        if (!function.type.same(functionType)) {
            throw new RuntimeException("indirect call type mismatch");
        }

        ((Call) Instruction.CALL.operate).callFunction(vm, function);
    }

}
