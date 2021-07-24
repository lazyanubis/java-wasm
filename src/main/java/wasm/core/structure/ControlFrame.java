package wasm.core.structure;

import wasm.core.instruction.Expression;
import wasm.core.instruction.Instruction;
import wasm.core.model.section.FunctionType;

public class ControlFrame {

    public int bp; // 栈上不属于本控制帧的长度 后面就是传入的参数 以及本地变量栈
    public Instruction instruction; // 当前控制帧的指令
    public FunctionType functionType; // 指令是CALL的话 应当函数签名
    public Expression expression; // 当前块的指令序列
    public int pc; // 当前块应该执行哪条指令

    public ControlFrame(int bp, Instruction instruction, FunctionType functionType, Expression expression) {
        this.bp = bp;
        this.instruction = instruction;
        this.functionType = functionType;
        this.expression = expression;
        this.pc = 0;
    }

}
