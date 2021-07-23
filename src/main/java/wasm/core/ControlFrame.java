package wasm.core;

import wasm.instruction.Expressions;
import wasm.instruction.Instruction;
import wasm.model.FunctionType;

public class ControlFrame {

    public Instruction instruction;
    public FunctionType functionType;
    public Expressions instructions;
    public int basePoint;
    public int pc;

    public ControlFrame(Instruction instruction, FunctionType functionType, Expressions instructions, int basePoint, int pc) {
        this.instruction = instruction;
        this.functionType = functionType;
        this.instructions = instructions;
        this.basePoint = basePoint;
        this.pc = pc;
    }
}
