package wasm.instruction2.control;

import wasm.core.instruction.Instruction;
import wasm.core.instruction.Operate;
import wasm.core.model.Dump;
import wasm.core.model.index.TableIndex;
import wasm.core.model.index.TypeIndex;
import wasm.core.model.section.FunctionType;
import wasm.core.numeric.U32;
import wasm.core.structure.Function;
import wasm.core.structure.ModuleInstance;
import wasm.core.structure.WasmReader;
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

        Function function = mi.getTable(TableIndex.of(0)).getElement(new U32(i));

        TypeIndex typeIndex = d.typeIndex;
        FunctionType functionType = mi.getModuleInfo().typeSections[typeIndex.intValue()];
        if (!function.type().same(functionType)) {
            throw new RuntimeException("indirect call type mismatch");
        }

        ((Call) Instruction.CALL.operate).callFunction(mi, function);
    }

}
