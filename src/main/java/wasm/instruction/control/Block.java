package wasm.instruction.control;

import wasm.core.VirtualMachine;
import wasm.core.WasmReader;
import wasm.instruction.Expressions;
import wasm.instruction.Instruction;
import wasm.instruction.Operate;
import wasm.instruction.dump.DumpBlock;
import wasm.model.Dump;
import wasm.model.FunctionType;
import wasm.model.type.BlockType;

public class Block implements Operate {

    @Override
    public Dump read(WasmReader reader) {
        BlockType blockType = reader.readBlockType();
        Expressions expressions = reader.readExpressions();
        return new DumpBlock(blockType, expressions);
    }

    @Override
    public void operate(VirtualMachine vm, Dump args) {
        assert null != args;
        assert args instanceof DumpBlock;

        DumpBlock b = (DumpBlock) args;

        FunctionType functionType = vm.module.getBlockType(b.blockType);

        vm.enterBlock(Instruction.BLOCK, functionType, b.expressions);
    }

}
