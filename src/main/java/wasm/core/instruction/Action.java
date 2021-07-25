package wasm.core.instruction;

import wasm.core.model.Dump;

public class Action implements Dump {

    private final Instruction instruction;

    private final Dump args;

    public Action(Instruction instruction, Dump args) {
        this.instruction = instruction;
        this.args = args;
    }

    @Override
    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append(instruction.name).append(" ");
        if (null != args) { sb.append(args.dump()); }

//        sb.append(toHex(instruction.opcode)).append(" ").append(null == args ? "" : args.dump()).append(" ").append("\t");
//        if (null != args) {
//            if (args instanceof DumpBlock || args instanceof DumpIfBlock) {
//
//            } else {
//                sb.append(" | ");
//                sb.append(instruction.name).append(" ");
//                sb.append(args.dump());
//            }
//        } else {
//            sb.append(" | ");
//            sb.append(instruction.name).append(" ");
//        }


        return sb.toString();
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public Dump getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return dump();
    }

}
