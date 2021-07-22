package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Operate;
import wasm.model.Dump;
import wasm.model.index.FunctionIndex;

public class Call implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readFunctionIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof FunctionIndex;

        int index = ((FunctionIndex) args).intValue();

        // 暂时直接默认所有方法都是导入方法，并且本地直接实现
        System.out.println(vm.getModule().importSections[index].name);
        switch (vm.getModule().importSections[index].name) {
            case "assert_true": assertEq(vm.popBool(), true); break;
            case "assert_false": assertEq(vm.popBool(), false); break;
            case "assert_eq_i32": assertEq(vm.popU32(), vm.popU32()); break;
            case "assert_eq_i64": assertEq(vm.popU64(), vm.popU64()); break;
            default:
                throw new RuntimeException("what a " + vm.getModule().importSections[index].name);
        }

    }

    private void assertEq(Object a, Object b) {
        if (!a.equals(b)) {
            throw new RuntimeException("not equals: " + a + " != " + b);
        }
    }

}
