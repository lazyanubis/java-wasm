package wasm.core;

import wasm.instruction.Expression;
import wasm.model.Code;

public class VirtualMachine extends OperandStack {

    public Module module;

    public void executeCode(int index) {
        Code code = module.codeSections[index];
        for (Expression expression : code.expressions) {
            executeExpression(expression);
        }
    }

    private void executeExpression(Expression expression) {
        expression.getInstruction().operate(this, expression.getArgs());
    }

}
