package wasm.core2.instance;

import wasm.core2.instruction.Instruction;
import wasm.core2.model.section.CodeSection;
import wasm.core2.model.section.FunctionType;
import wasm.core2.numeric.U64;
import wasm.core2.structure.Function;
import wasm.core2.structure.ModuleInstance;
import wasm.instruction2.control.Call;

public class FunctionInstance implements Function {

    public final FunctionType type;

    public final CodeSection codeSection;
    private final ModuleInstance instance;

    public final Function function;

    public FunctionInstance(FunctionType type, CodeSection codeSection, ModuleInstance instance) {
        this.type = type;
        this.codeSection = codeSection;
        this.instance = instance;
        this.function = null;
    }

    public FunctionInstance(FunctionType type, Function function) {
        this.instance = null;
        this.type = type;
        this.codeSection = null;
        this.function = function;
    }

    @Override
    public FunctionType type() {
        return type;
    }

    public U64[] call(U64... args) {
        if (null != function) {
            return function.call(args);
        }
        return safeCall(instance, args);
    }

    @Override
    public boolean isInternal() {
        return null != codeSection;
    }

    @Override
    public CodeSection getCodeSection() {
        return codeSection;
    }

    private U64[] safeCall(ModuleInstance instance, U64[] args) {
        pushArgs(instance, args);
        ((Call) Instruction.CALL.operate).callFunction(instance, this);
        if (null == function) { instance.loop(); }
        return popResults(instance);
    }

    private void pushArgs(ModuleInstance instance, U64[] args) {
        assert args.length == type.parameters.length;

        for (U64 arg : args) {
            instance.pushU64(arg);
        }
    }

    private U64[] popResults(ModuleInstance instance) {
        U64[] results = new U64[type.results.length];
        for (int i = results.length - 1; 0 <= i; i--) {
            results[i] = instance.popU64();
        }
        return results;
    }


}
