package wasm.core.instance;

import wasm.core.exception.Check;
import wasm.core.numeric.USize;
import wasm.core.instruction.Instruction;
import wasm.core.model.section.CodeSection;
import wasm.core.model.section.FunctionType;
import wasm.core.numeric.U64;
import wasm.core.structure.Function;
import wasm.core.structure.ModuleInstance;
import wasm.core.instruction.control.Call;

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

    public USize[] call(USize... args) {
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

    protected USize[] safeCall(ModuleInstance instance, USize[] args) {
        pushArgs(instance, args);
        ((Call) Instruction.CALL.operate).callFunction(instance, this);
        if (null == function) { instance.loop(); }
        return popResults(instance);
    }

    private void pushArgs(ModuleInstance instance, USize[] args) {
        Check.require(args.length == type.parameters.length);

        for (USize arg : args) {
            instance.pushUSize(arg);
        }
    }

    protected U64[] popResults(ModuleInstance instance) {
        U64[] results = new U64[type.results.length];
        for (int i = results.length - 1; 0 <= i; i--) {
            results[i] = instance.popU64();
        }
        return results;
    }


}
