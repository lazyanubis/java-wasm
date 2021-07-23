package wasm.core;

import wasm.model.Code;
import wasm.model.FunctionType;
import wasm.nav.function.NativeFunction;

public class VirtualMachineFunction {

    public final FunctionType type;

    public final Code code;

    public final NativeFunction local;

    public VirtualMachineFunction(FunctionType type, Code code) {
        this.type = type;
        this.code = code;
        this.local = null;
    }

    public VirtualMachineFunction(FunctionType type, NativeFunction local) {
        this.type = type;
        this.code = null;
        this.local = local;
    }



}
