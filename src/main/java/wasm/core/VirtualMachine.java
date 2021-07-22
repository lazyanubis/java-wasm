package wasm.core;

public class VirtualMachine extends OperandStack {

    public Module module;

//    public void executeCode(int index) {
//        Code code = module.codeSections[index];
//        for (Instruction instruction : code.instructions) {
//            executeInstruction(instruction);
//        }
//    }
//
//    private void executeInstruction(Instruction instruction) {
//        if (null == INSTRUCTION_TABLE[instruction.opcode]) {
//            throw new RuntimeException("wait");
//        }
//        INSTRUCTION_TABLE[instruction.opcode].accept(this, instruction.args);
//    }
//
//    private static final BiConsumer<VirtualMachine, Dump>[] INSTRUCTION_TABLE = new BiConsumer[256];
//
//    static {
//        // 参数指令
//        INSTRUCTION_TABLE[Drop] = (vm, d) -> vm.popUint64();
//        INSTRUCTION_TABLE[Select] = (vm, a) -> {
//            boolean e = vm.popBoolean();
//            Uint64 d = vm.popUint64();
//            Uint64 c = vm.popUint64();
//            vm.pushUint64(e ? c : d);
//        };
//
//        // 数值指令
//        // 常量指令
//        INSTRUCTION_TABLE[I32Const] = (vm, d) -> {
//            if (!(d instanceof DumpInt32)) { throw new RuntimeException("wrong args"); }
//            DumpInt32 a = (DumpInt32) d;
//            vm.pushInt32(a.value);
//        };
//        INSTRUCTION_TABLE[I64Const] = (vm, d) -> {
//            if (!(d instanceof DumpInt64)) { throw new RuntimeException("wrong args"); }
//            DumpInt64 a = (DumpInt64) d;
//            vm.pushInt64(a.value);
//        };
//        INSTRUCTION_TABLE[F32Const] = (vm, d) -> {
//            if (!(d instanceof DumpFloat32)) { throw new RuntimeException("wrong args"); }
//            DumpFloat32 a = (DumpFloat32) d;
//            vm.pushFloat32(a.value);
//        };
//        INSTRUCTION_TABLE[F64Const] = (vm, d) -> {
//            if (!(d instanceof DumpFloat64)) { throw new RuntimeException("wrong args"); }
//            DumpFloat64 a = (DumpFloat64) d;
//            vm.pushFloat64(a.value);
//        };
//        // 测试指令
//        INSTRUCTION_TABLE[F64Const] = (vm, d) -> {
//            Uint64 v = vm.popUint64();
//            vm.pushBoolean(!v.getValue().contains("1"));
//        };
//        INSTRUCTION_TABLE[F32Const] = (vm, d) -> {
//            Uint64 v = vm.popUint64();
//            vm.pushBoolean(!v.getValue().contains("1"));
//        };
//        // 比较指令
//        // 32位整型
//        INSTRUCTION_TABLE[I32Eq] = (vm, d) -> {
//            Uint32 v2 = vm.popUint32();
//            Uint32 v1 = vm.popUint32();
//            vm.pushBoolean(Long.parseUnsignedLong(v1.getValue(), 2) == Long.parseUnsignedLong(v2.getValue(), 2));
//        };
//        INSTRUCTION_TABLE[I32Ne] = (vm, d) -> {
//            Uint32 v2 = vm.popUint32();
//            Uint32 v1 = vm.popUint32();
//            vm.pushBoolean(Long.parseUnsignedLong(v1.getValue(), 2) != Long.parseUnsignedLong(v2.getValue(), 2));
//        };
//        INSTRUCTION_TABLE[I32LtS] = (vm, d) -> {
//            int v2 = vm.popInt32();
//            int v1 = vm.popInt32();
//            vm.pushBoolean(v1 < v2);
//        };
//        INSTRUCTION_TABLE[I32LtU] = (vm, d) -> {
//            Uint32 v2 = vm.popUint32();
//            Uint32 v1 = vm.popUint32();
//            vm.pushBoolean(Long.parseUnsignedLong(v1.getValue(), 2) < Long.parseUnsignedLong(v2.getValue(), 2));
//        };
//        INSTRUCTION_TABLE[I32GtS] = (vm, d) -> {
//            int v2 = vm.popInt32();
//            int v1 = vm.popInt32();
//            vm.pushBoolean(v1 > v2);
//        };
//        INSTRUCTION_TABLE[I32GtU] = (vm, d) -> {
//            Uint32 v2 = vm.popUint32();
//            Uint32 v1 = vm.popUint32();
//            vm.pushBoolean(Long.parseUnsignedLong(v1.getValue(), 2) > Long.parseUnsignedLong(v2.getValue(), 2));
//        };
//        INSTRUCTION_TABLE[I32LeS] = (vm, d) -> {
//            int v2 = vm.popInt32();
//            int v1 = vm.popInt32();
//            vm.pushBoolean(v1 <= v2);
//        };
//        INSTRUCTION_TABLE[I32LeU] = (vm, d) -> {
//            Uint32 v2 = vm.popUint32();
//            Uint32 v1 = vm.popUint32();
//            vm.pushBoolean(Long.parseUnsignedLong(v1.getValue(), 2) <= Long.parseUnsignedLong(v2.getValue(), 2));
//        };
//        INSTRUCTION_TABLE[I32GeS] = (vm, d) -> {
//            int v2 = vm.popInt32();
//            int v1 = vm.popInt32();
//            vm.pushBoolean(v1 >= v2);
//        };
//        INSTRUCTION_TABLE[I32GeU] = (vm, d) -> {
//            Uint32 v2 = vm.popUint32();
//            Uint32 v1 = vm.popUint32();
//            vm.pushBoolean(Long.parseUnsignedLong(v1.getValue(), 2) >= Long.parseUnsignedLong(v2.getValue(), 2));
//        };
//        // 64位整型
//        INSTRUCTION_TABLE[I64Eq] = (vm, d) -> {
//            BigInteger v2 = new BigInteger(vm.popUint64().getValue(), 2);
//            BigInteger v1 = new BigInteger(vm.popUint64().getValue(), 2);
//            vm.pushBoolean(v1.compareTo(v2) == 0);
//        };
//        INSTRUCTION_TABLE[I64Ne] = (vm, d) -> {
//            BigInteger v2 = new BigInteger(vm.popUint64().getValue(), 2);
//            BigInteger v1 = new BigInteger(vm.popUint64().getValue(), 2);
//            vm.pushBoolean(v1.compareTo(v2) != 0);
//        };
//        INSTRUCTION_TABLE[I64LtS] = (vm, d) -> {
//            long v2 = vm.popInt64();
//            long v1 = vm.popInt64();
//            vm.pushBoolean(v1 < v2);
//        };
//        INSTRUCTION_TABLE[I64LtU] = (vm, d) -> {
//            BigInteger v2 = new BigInteger(vm.popUint64().getValue(), 2);
//            BigInteger v1 = new BigInteger(vm.popUint64().getValue(), 2);
//            vm.pushBoolean(v1.compareTo(v2) < 0);
//        };
//        INSTRUCTION_TABLE[I64GtS] = (vm, d) -> {
//            long v2 = vm.popInt64();
//            long v1 = vm.popInt64();
//            vm.pushBoolean(v1 > v2);
//        };
//        INSTRUCTION_TABLE[I64GtU] = (vm, d) -> {
//            BigInteger v2 = new BigInteger(vm.popUint64().getValue(), 2);
//            BigInteger v1 = new BigInteger(vm.popUint64().getValue(), 2);
//            vm.pushBoolean(v1.compareTo(v2) > 0);
//        };
//        INSTRUCTION_TABLE[I64LeS] = (vm, d) -> {
//            long v2 = vm.popInt64();
//            long v1 = vm.popInt64();
//            vm.pushBoolean(v1 <= v2);
//        };
//        INSTRUCTION_TABLE[I64LeU] = (vm, d) -> {
//            BigInteger v2 = new BigInteger(vm.popUint64().getValue(), 2);
//            BigInteger v1 = new BigInteger(vm.popUint64().getValue(), 2);
//            vm.pushBoolean(v1.compareTo(v2) <= 0);
//        };
//        INSTRUCTION_TABLE[I64GeS] = (vm, d) -> {
//            long v2 = vm.popInt64();
//            long v1 = vm.popInt64();
//            vm.pushBoolean(v1 >= v2);
//        };
//        INSTRUCTION_TABLE[I32GeU] = (vm, d) -> {
//            BigInteger v2 = new BigInteger(vm.popUint64().getValue(), 2);
//            BigInteger v1 = new BigInteger(vm.popUint64().getValue(), 2);
//            vm.pushBoolean(v1.compareTo(v2) >= 0);
//        };
//
//        // 32位浮点
//        INSTRUCTION_TABLE[F32Eq] = (vm, d) -> {
//            float v2 = vm.popFloat32();
//            float v1 = vm.popFloat32();
//            vm.pushBoolean(v1 == v2);
//        };
//        INSTRUCTION_TABLE[F32Ne] = (vm, d) -> {
//            float v2 = vm.popFloat32();
//            float v1 = vm.popFloat32();
//            vm.pushBoolean(v1 != v2);
//        };
//        INSTRUCTION_TABLE[F32Lt] = (vm, d) -> {
//            float v2 = vm.popFloat32();
//            float v1 = vm.popFloat32();
//            vm.pushBoolean(v1 < v2);
//        };
//        INSTRUCTION_TABLE[F32Gt] = (vm, d) -> {
//            float v2 = vm.popFloat32();
//            float v1 = vm.popFloat32();
//            vm.pushBoolean(v1 > v2);
//        };
//        INSTRUCTION_TABLE[F32Le] = (vm, d) -> {
//            float v2 = vm.popFloat32();
//            float v1 = vm.popFloat32();
//            vm.pushBoolean(v1 <= v2);
//        };
//        INSTRUCTION_TABLE[F32Ge] = (vm, d) -> {
//            float v2 = vm.popFloat32();
//            float v1 = vm.popFloat32();
//            vm.pushBoolean(v1 >= v2);
//        };
//        // 64位浮点
//        INSTRUCTION_TABLE[F64Eq] = (vm, d) -> {
//            double v2 = vm.popFloat64();
//            double v1 = vm.popFloat64();
//            vm.pushBoolean(v1 == v2);
//        };
//        INSTRUCTION_TABLE[F64Ne] = (vm, d) -> {
//            double v2 = vm.popFloat64();
//            double v1 = vm.popFloat64();
//            vm.pushBoolean(v1 != v2);
//        };
//        INSTRUCTION_TABLE[F64Lt] = (vm, d) -> {
//            double v2 = vm.popFloat64();
//            double v1 = vm.popFloat64();
//            vm.pushBoolean(v1 < v2);
//        };
//        INSTRUCTION_TABLE[F64Gt] = (vm, d) -> {
//            double v2 = vm.popFloat64();
//            double v1 = vm.popFloat64();
//            vm.pushBoolean(v1 > v2);
//        };
//        INSTRUCTION_TABLE[F64Le] = (vm, d) -> {
//            double v2 = vm.popFloat64();
//            double v1 = vm.popFloat64();
//            vm.pushBoolean(v1 <= v2);
//        };
//        INSTRUCTION_TABLE[F64Ge] = (vm, d) -> {
//            double v2 = vm.popFloat64();
//            double v1 = vm.popFloat64();
//            vm.pushBoolean(v1 >= v2);
//        };
//
//        // 一元算术指令
//        INSTRUCTION_TABLE[I32Clz] = (vm, d) -> vm.pushUint32(countUint32LeftZero(vm.popUint32().getValue()));
//        INSTRUCTION_TABLE[I32Ctz] = (vm, d) -> vm.pushUint32(countUint32RightZero(vm.popUint32().getValue()));
//        INSTRUCTION_TABLE[I32PopCnt] = (vm, d) -> vm.pushUint32(countUint32One(vm.popUint32().getValue()));
//        INSTRUCTION_TABLE[I64Clz] = (vm, d) -> vm.pushUint64(countUint64LeftZero(vm.popUint64().getValue()));
//        INSTRUCTION_TABLE[I64Ctz] = (vm, d) -> vm.pushUint64(countUint64RightZero(vm.popUint64().getValue()));
//        INSTRUCTION_TABLE[I64PopCnt] = (vm, d) -> vm.pushUint64(countUint64One(vm.popUint64().getValue()));
//        INSTRUCTION_TABLE[F32Abs] = (vm, d) -> vm.pushFloat32(Math.abs(vm.popFloat32()));
//        INSTRUCTION_TABLE[F32Neg] = (vm, d) -> vm.pushFloat32(-vm.popFloat32());
//        INSTRUCTION_TABLE[F32Ceil] = (vm, d) -> vm.pushFloat32((float) Math.ceil(vm.popFloat32()));
//        INSTRUCTION_TABLE[F32Floor] = (vm, d) -> vm.pushFloat32((float) Math.floor(vm.popFloat32()));
//        INSTRUCTION_TABLE[F32Trunc] = (vm, d) -> vm.pushFloat32((float) Math.rint(vm.popFloat32()));
//        INSTRUCTION_TABLE[F32Nearest] = (vm, d) -> vm.pushFloat32(Math.round(vm.popFloat32()));
//        INSTRUCTION_TABLE[F32Sqrt] = (vm, d) -> vm.pushFloat32((float) Math.sqrt(vm.popFloat32()));
//        INSTRUCTION_TABLE[F64Abs] = (vm, d) -> vm.pushFloat64(Math.abs(vm.popFloat64()));
//        INSTRUCTION_TABLE[F64Neg] = (vm, d) -> vm.pushFloat64(-vm.popFloat64());
//        INSTRUCTION_TABLE[F64Ceil] = (vm, d) -> vm.pushFloat64(Math.ceil(vm.popFloat64()));
//        INSTRUCTION_TABLE[F64Floor] = (vm, d) -> vm.pushFloat64(Math.floor(vm.popFloat64()));
//        INSTRUCTION_TABLE[F64Trunc] = (vm, d) -> vm.pushFloat64(Math.rint(vm.popFloat64()));
//        INSTRUCTION_TABLE[F64Nearest] = (vm, d) -> vm.pushFloat64(Math.round(vm.popFloat64()));
//        INSTRUCTION_TABLE[F64Sqrt] = (vm, d) -> vm.pushFloat64(Math.sqrt(vm.popFloat64()));
//
//        // 二元算术指令
//        INSTRUCTION_TABLE[I64Rotl] = (vm, d) -> vm.pushUint64(rotateLeftUint64(vm.popUint64(), vm.popInt32()));
//        INSTRUCTION_TABLE[I64Rotr] = (vm, d) -> vm.pushUint64(rotateRightUint64(vm.popUint64(), vm.popInt32()));
////        INSTRUCTION_TABLE[I64Sub] = (vm, d) -> {
////            BigInteger v2 = new BigInteger(vm.popUint64().getValue(), 2);
////            BigInteger v1 = new BigInteger(vm.popUint64().getValue(), 2);
////            String v = v1.subtract(v2).toString(2);
////            if (v.length())
////        };
//
//
//    }
//
//    private static Uint32 countUint32LeftZero(String value) {
//        StringBuilder valueBuilder = new StringBuilder(value);
//        while (valueBuilder.length() < 32) { valueBuilder.insert(0, "0"); }
//        value = valueBuilder.toString();
//
//        int c = 0;
//        for (int i = 0; i < value.length(); i++) {
//            if (value.charAt(i) == '0') {
//                c++;
//            } else {
//                break;
//            }
//        }
//
//        String v = Integer.toBinaryString(c);
//        StringBuilder sb = new StringBuilder(v);
//        while (sb.length() < 32) { sb.insert(0, "0"); }
//        value = sb.toString();
//
//        return new Uint32(value);
//    }
//
//    private static Uint32 countUint32RightZero(String value) {
//        StringBuilder valueBuilder = new StringBuilder(value);
//        while (valueBuilder.length() < 32) { valueBuilder.insert(0, "0"); }
//        value = valueBuilder.toString();
//
//        int c = 0;
//        for (int i = value.length() - 1; 0 <= i; i--) {
//            if (value.charAt(i) == '0') {
//                c++;
//            } else {
//                break;
//            }
//        }
//
//        String v = Integer.toBinaryString(c);
//        StringBuilder sb = new StringBuilder(v);
//        while (sb.length() < 32) { sb.insert(0, "0"); }
//        value = sb.toString();
//
//        return new Uint32(value);
//    }
//
//    private static Uint32 countUint32One(String value) {
//        int c = 0;
//        for (int i = 0; i < value.length(); i++) {
//            if (value.charAt(i) == '1') {
//                c++;
//            }
//        }
//
//        String v = Integer.toBinaryString(c);
//        StringBuilder sb = new StringBuilder(v);
//        while (sb.length() < 32) { sb.insert(0, "0"); }
//        value = sb.toString();
//
//        return new Uint32(value);
//    }
//
//    private static Uint64 countUint64LeftZero(String value) {
//        StringBuilder valueBuilder = new StringBuilder(value);
//        while (valueBuilder.length() < 64) { valueBuilder.insert(0, "0"); }
//        value = valueBuilder.toString();
//
//        int c = 0;
//        for (int i = 0; i < value.length(); i++) {
//            if (value.charAt(i) == '0') {
//                c++;
//            } else {
//                break;
//            }
//        }
//
//        String v = Integer.toBinaryString(c);
//        StringBuilder sb = new StringBuilder(v);
//        while (sb.length() < 64) { sb.insert(0, "0"); }
//        value = sb.toString();
//
//        return new Uint64(value);
//    }
//
//    private static Uint64 countUint64RightZero(String value) {
//        StringBuilder valueBuilder = new StringBuilder(value);
//        while (valueBuilder.length() < 64) { valueBuilder.insert(0, "0"); }
//        value = valueBuilder.toString();
//
//        int c = 0;
//        for (int i = value.length() - 1; 0 <= i; i--) {
//            if (value.charAt(i) == '0') {
//                c++;
//            } else {
//                break;
//            }
//        }
//
//        String v = Integer.toBinaryString(c);
//        StringBuilder sb = new StringBuilder(v);
//        while (sb.length() < 64) { sb.insert(0, "0"); }
//        value = sb.toString();
//
//        return new Uint64(value);
//    }
//
//    private static Uint64 countUint64One(String value) {
//        int c = 0;
//        for (int i = 0; i < value.length(); i++) {
//            if (value.charAt(i) == '1') {
//                c++;
//            }
//        }
//
//        String v = Integer.toBinaryString(c);
//        StringBuilder sb = new StringBuilder(v);
//        while (sb.length() < 64) { sb.insert(0, "0"); }
//        value = sb.toString();
//
//        return new Uint64(value);
//    }
//
//    private static Uint64 rotateLeftUint64(Uint64 value, int size) {
//        return new Uint64(value.getValue().substring(size) + value.getValue().substring(0, size));
//    }
//    private static Uint64 rotateRightUint64(Uint64 value, int size) {
//        return new Uint64(value.getValue().substring(value.getValue().length() - size) + value.getValue().substring(0, value.getValue().length() - size));
//    }
//    private static Uint32 rotateLeftUint32(Uint32 value, int size) {
//        return new Uint32(value.getValue().substring(size) + value.getValue().substring(0, size));
//    }
//    private static Uint32 rotateRightUint32(Uint32 value, int size) {
//        return new Uint32(value.getValue().substring(value.getValue().length() - size) + value.getValue().substring(0, value.getValue().length() - size));
//    }

}
