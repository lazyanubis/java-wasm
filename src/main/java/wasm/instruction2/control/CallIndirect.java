package wasm.instruction2.control;

import wasm.core2.instruction.Instruction;
import wasm.core2.instruction.Operate;
import wasm.core2.model.Dump;
import wasm.core3.model.index.TableIndex;
import wasm.core3.model.index.TypeIndex;
import wasm.core2.model.section.FunctionType;
import wasm.core.numeric.U32;
import wasm.core3.structure.Function;
import wasm.core2.structure.ModuleInstance;
import wasm.core2.structure.WasmReader;
import wasm.instruction2.dump.DumpCallIndirect;

public class CallIndirect implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return new DumpCallIndirect(reader.readTypeIndex(), reader.readTableIndex());
    }

    @Override
    public void operate(ModuleInstance mi, Dump args) {
        assert null != args;
        assert args instanceof DumpCallIndirect;

        DumpCallIndirect d = (DumpCallIndirect) args;

        int i = mi.popU32().intValue();
        // which table ?
        if (i >= mi.getTable(TableIndex.of(0)).size().intValue()) {
            throw new RuntimeException("to large");
        }

        Function function = mi.getTable(TableIndex.of(0)).getElement(U32.valueOf(i));

        TypeIndex typeIndex = d.typeIndex;
        FunctionType functionType = mi.getModuleInfo().typeSections[typeIndex.intValue()];
        if (!function.type().same(functionType)) {
            throw new RuntimeException("indirect call type mismatch");
        }

        ((Call) Instruction.CALL.operate).callFunction(mi, function);
    }

}
