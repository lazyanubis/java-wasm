package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Instruction;
import wasm.instruction.Operate;
import wasm.model.Code;
import wasm.model.Dump;
import wasm.model.FunctionType;
import wasm.model.index.FunctionIndex;
import wasm.model.index.TypeIndex;
import wasm.model.number.U64;

public class Call implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        return reader.readFunctionIndex();
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert args instanceof FunctionIndex;

        int index = ((FunctionIndex) args).intValue();

        int importedFunctionCount = vm.module.importSections.length; // 导入了多少个函数

        if (index < importedFunctionCount) {
            callExternalFunction(vm, index);
        } else {
            callInternalFunction(vm, index - importedFunctionCount); // 序号要减去导入的函数
        }
    }

    private void callInternalFunction(VirtualMachine vm, int index) {
        // 执行模块内部定义的函数
        TypeIndex typeIndex = vm.module.functionSections[index]; // 函数签名序号
        FunctionType functionType = vm.module.typeSections[typeIndex.intValue()]; // 函数签名
        Code code = vm.module.codeSections[index]; // 函数代码

        vm.enterBlock(Instruction.CALL, functionType, code.expressions);

        // 分配本地变量
        long length = code.localCount();
        for (int i = 0; i < length; i++) {
            vm.operandStack.pushU64(new U64(0));
        }

    }

    private void callExternalFunction(VirtualMachine vm, int index) {
        // 执行外部函数
        // 1. 本地函数
        // 2. 其他模块的函数
        System.out.println(vm.getModule().importSections[index].name);
        switch (vm.getModule().importSections[index].name) {
            case "assert_true": assertEq(vm.operandStack.popBool(), true); break;
            case "assert_false": assertEq(vm.operandStack.popBool(), false); break;
            case "assert_eq_i32": assertEq(vm.operandStack.popU32(), vm.operandStack.popU32()); break;
            case "assert_eq_i64": assertEq(vm.operandStack.popU64(), vm.operandStack.popU64()); break;
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
