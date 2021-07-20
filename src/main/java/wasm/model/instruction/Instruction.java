package wasm.model.instruction;

import wasm.model.section.util.Dump;

import java.util.HashMap;
import java.util.Map;

public class Instruction {

    public byte opcode;
    public Dump args;

    public String getOpName() {
        return OP_NAMES.get(opcode);
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();

        sb.append(toHex(opcode)).append(" ").append(null == args ? "" : args.dump()).append(" ").append("\t");
        if (null != args) {
            if (args instanceof BlockArgs || args instanceof IfArgs || args instanceof LoopArgs) {

            } else {
                sb.append(" | ");
                sb.append(OP_NAMES.get(opcode)).append(" ");
                sb.append(args);
            }
        } else {
            sb.append(" | ");
            sb.append(OP_NAMES.get(opcode)).append(" ");
        }


        return sb.toString();
    }

    public static String toHex(byte code) {
        String s = Integer.toHexString(code);
        if (s.length() < 2) {
            return "0" + s;
        }
        return s.substring(s.length() - 2);
    }

    public static final byte Unreachable       = 0x00; // unreachable
    public static final byte Nop               = 0x01; // nop
    public static final byte Block             = 0x02; // block rt in* end
    public static final byte Loop              = 0x03; // loop rt in* end
    public static final byte If                = 0x04; // if rt in* else in* end
    public static final byte Else_             = 0x05; // else

    public static final byte End_              = 0x0B; // end
    public static final byte Br                = 0x0C; // br l
    public static final byte BrIf              = 0x0D; // br_if l
    public static final byte BrTable           = 0x0E; // br_table l* lN
    public static final byte Return            = 0x0F; // return

    public static final byte Call              = 0x10; // call x
    public static final byte CallIndirect      = 0x11; // call_indirect x

    public static final byte Drop              = 0x1A; // drop
    public static final byte Select            = 0x1B; // select

    public static final byte LocalGet          = 0x20; // local.get x
    public static final byte LocalSet          = 0x21; // local.set x
    public static final byte LocalTee          = 0x22; // local.tee x
    public static final byte GlobalGet         = 0x23; // global.get x
    public static final byte GlobalSet         = 0x24; // global.set x

    public static final byte I32Load           = 0x28; // i32.load m
    public static final byte I64Load           = 0x29; // i64.load m
    public static final byte F32Load           = 0x2A; // f32.load m
    public static final byte F64Load           = 0x2B; // f64.load m
    public static final byte I32Load8S         = 0x2C; // i32.load8_s m
    public static final byte I32Load8U         = 0x2D; // i32.load8_u m
    public static final byte I32Load16S        = 0x2E; // i32.load16_s m
    public static final byte I32Load16U        = 0x2F; // i32.load16_u m

    public static final byte I64Load8S         = 0x30; // i64.load8_s m
    public static final byte I64Load8U         = 0x31; // i64.load8_u m
    public static final byte I64Load16S        = 0x32; // i64.load16_s m
    public static final byte I64Load16U        = 0x33; // i64.load16_u m
    public static final byte I64Load32S        = 0x34; // i64.load32_s m
    public static final byte I64Load32U        = 0x35; // i64.load32_u m

    public static final byte I32Store          = 0x36; // i32.store m
    public static final byte I64Store          = 0x37; // i64.store m
    public static final byte F32Store          = 0x38; // f32.store m
    public static final byte F64Store          = 0x39; // f64.store m
    public static final byte I32Store8         = 0x3A; // i32.store8 m
    public static final byte I32Store16        = 0x3B; // i32.store16 m
    public static final byte I64Store8         = 0x3C; // i64.store8 m
    public static final byte I64Store16        = 0x3D; // i64.store16 m
    public static final byte I64Store32        = 0x3E; // i64.store32 m

    public static final byte MemorySize        = 0x3F; // memory.size
    public static final byte MemoryGrow        = 0x40; // memory.grow

    public static final byte I32Const          = 0x41; // i32.const n
    public static final byte I64Const          = 0x42; // i64.const n
    public static final byte F32Const          = 0x43; // f32.const z
    public static final byte F64Const          = 0x44; // f64.const z

    public static final byte I32Eqz            = 0x45; // i32.eqz
    public static final byte I32Eq             = 0x46; // i32.eq
    public static final byte I32Ne             = 0x47; // i32.ne
    public static final byte I32LtS            = 0x48; // i32.lt_s
    public static final byte I32LtU            = 0x49; // i32.lt_u
    public static final byte I32GtS            = 0x4A; // i32.gt_s
    public static final byte I32GtU            = 0x4B; // i32.gt_u
    public static final byte I32LeS            = 0x4C; // i32.le_s
    public static final byte I32LeU            = 0x4D; // i32.le_u
    public static final byte I32GeS            = 0x4E; // i32.ge_s
    public static final byte I32GeU            = 0x4F; // i32.ge_u

    public static final byte I64Eqz            = 0x50; // i64.eqz
    public static final byte I64Eq             = 0x51; // i64.eq
    public static final byte I64Ne             = 0x52; // i64.ne
    public static final byte I64LtS            = 0x53; // i64.lt_s
    public static final byte I64LtU            = 0x54; // i64.lt_u
    public static final byte I64GtS            = 0x55; // i64.gt_s
    public static final byte I64GtU            = 0x56; // i64.gt_u
    public static final byte I64LeS            = 0x57; // i64.le_s
    public static final byte I64LeU            = 0x58; // i64.le_u
    public static final byte I64GeS            = 0x59; // i64.ge_s
    public static final byte I64GeU            = 0x5A; // i64.ge_u
    public static final byte F32Eq             = 0x5B; // f32.eq
    public static final byte F32Ne             = 0x5C; // f32.ne
    public static final byte F32Lt             = 0x5D; // f32.lt
    public static final byte F32Gt             = 0x5E; // f32.gt
    public static final byte F32Le             = 0x5F; // f32.le
    public static final byte F32Ge             = 0x60; // f32.ge
    public static final byte F64Eq             = 0x61; // f64.eq
    public static final byte F64Ne             = 0x62; // f64.ne
    public static final byte F64Lt             = 0x63; // f64.lt
    public static final byte F64Gt             = 0x64; // f64.gt
    public static final byte F64Le             = 0x65; // f64.le
    public static final byte F64Ge             = 0x66; // f64.ge
    public static final byte I32Clz            = 0x67; // i32.clz
    public static final byte I32Ctz            = 0x68; // i32.ctz
    public static final byte I32PopCnt         = 0x69; // i32.popcnt
    public static final byte I32Add            = 0x6A; // i32.add
    public static final byte I32Sub            = 0x6B; // i32.sub
    public static final byte I32Mul            = 0x6C; // i32.mul
    public static final byte I32DivS           = 0x6D; // i32.div_s
    public static final byte I32DivU           = 0x6E; // i32.div_u
    public static final byte I32RemS           = 0x6F; // i32.rem_s
    public static final byte I32RemU           = 0x70; // i32.rem_u
    public static final byte I32And            = 0x71; // i32.and
    public static final byte I32Or             = 0x72; // i32.or
    public static final byte I32Xor            = 0x73; // i32.xor
    public static final byte I32Shl            = 0x74; // i32.shl
    public static final byte I32ShrS           = 0x75; // i32.shr_s
    public static final byte I32ShrU           = 0x76; // i32.shr_u
    public static final byte I32Rotl           = 0x77; // i32.rotl
    public static final byte I32Rotr           = 0x78; // i32.rotr
    public static final byte I64Clz            = 0x79; // i64.clz
    public static final byte I64Ctz            = 0x7A; // i64.ctz
    public static final byte I64PopCnt         = 0x7B; // i64.popcnt
    public static final byte I64Add            = 0x7C; // i64.add
    public static final byte I64Sub            = 0x7D; // i64.sub
    public static final byte I64Mul            = 0x7E; // i64.mul
    public static final byte I64DivS           = 0x7F; // i64.div_s
    public static final byte I64DivU           = (byte)0x80; // i64.div_u
    public static final byte I64RemS           = (byte)0x81; // i64.rem_s
    public static final byte I64RemU           = (byte)0x82; // i64.rem_u
    public static final byte I64And            = (byte)0x83; // i64.and
    public static final byte I64Or             = (byte)0x84; // i64.or
    public static final byte I64Xor            = (byte)0x85; // i64.xor
    public static final byte I64Shl            = (byte)0x86; // i64.shl
    public static final byte I64ShrS           = (byte)0x87; // i64.shr_s
    public static final byte I64ShrU           = (byte)0x88; // i64.shr_u
    public static final byte I64Rotl           = (byte)0x89; // i64.rotl
    public static final byte I64Rotr           = (byte)0x8A; // i64.rotr
    public static final byte F32Abs            = (byte)0x8B; // f32.abs
    public static final byte F32Neg            = (byte)0x8C; // f32.neg
    public static final byte F32Ceil           = (byte)0x8D; // f32.ceil
    public static final byte F32Floor          = (byte)0x8E; // f32.floor
    public static final byte F32Trunc          = (byte)0x8F; // f32.trunc
    public static final byte F32Nearest        = (byte)0x90; // f32.nearest
    public static final byte F32Sqrt           = (byte)0x91; // f32.sqrt
    public static final byte F32Add            = (byte)0x92; // f32.add
    public static final byte F32Sub            = (byte)0x93; // f32.sub
    public static final byte F32Mul            = (byte)0x94; // f32.mul
    public static final byte F32Div            = (byte)0x95; // f32.div
    public static final byte F32Min            = (byte)0x96; // f32.min
    public static final byte F32Max            = (byte)0x97; // f32.max
    public static final byte F32CopySign       = (byte)0x98; // f32.copysign
    public static final byte F64Abs            = (byte)0x99; // f64.abs
    public static final byte F64Neg            = (byte)0x9A; // f64.neg
    public static final byte F64Ceil           = (byte)0x9B; // f64.ceil
    public static final byte F64Floor          = (byte)0x9C; // f64.floor
    public static final byte F64Trunc          = (byte)0x9D; // f64.trunc
    public static final byte F64Nearest        = (byte)0x9E; // f64.nearest
    public static final byte F64Sqrt           = (byte)0x9F; // f64.sqrt
    public static final byte F64Add            = (byte)0xA0; // f64.add
    public static final byte F64Sub            = (byte)0xA1; // f64.sub
    public static final byte F64Mul            = (byte)0xA2; // f64.mul
    public static final byte F64Div            = (byte)0xA3; // f64.div
    public static final byte F64Min            = (byte)0xA4; // f64.min
    public static final byte F64Max            = (byte)0xA5; // f64.max
    public static final byte F64CopySign       = (byte)0xA6; // f64.copysign
    public static final byte I32WrapI64        = (byte)0xA7; // i32.wrap_i64
    public static final byte I32TruncF32S      = (byte)0xA8; // i32.trunc_f32_s
    public static final byte I32TruncF32U      = (byte)0xA9; // i32.trunc_f32_u
    public static final byte I32TruncF64S      = (byte)0xAA; // i32.trunc_f64_s
    public static final byte I32TruncF64U      = (byte)0xAB; // i32.trunc_f64_u
    public static final byte I64ExtendI32S     = (byte)0xAC; // i64.extend_i32_s
    public static final byte I64ExtendI32U     = (byte)0xAD; // i64.extend_i32_u
    public static final byte I64TruncF32S      = (byte)0xAE; // i64.trunc_f32_s
    public static final byte I64TruncF32U      = (byte)0xAF; // i64.trunc_f32_u
    public static final byte I64TruncF64S      = (byte)0xB0; // i64.trunc_f64_s
    public static final byte I64TruncF64U      = (byte)0xB1; // i64.trunc_f64_u
    public static final byte F32ConvertI32S    = (byte)0xB2; // f32.convert_i32_s
    public static final byte F32ConvertI32U    = (byte)0xB3; // f32.convert_i32_u
    public static final byte F32ConvertI64S    = (byte)0xB4; // f32.convert_i64_s
    public static final byte F32ConvertI64U    = (byte)0xB5; // f32.convert_i64_u
    public static final byte F32DemoteF64      = (byte)0xB6; // f32.demote_f64
    public static final byte F64ConvertI32S    = (byte)0xB7; // f64.convert_i32_s
    public static final byte F64ConvertI32U    = (byte)0xB8; // f64.convert_i32_u
    public static final byte F64ConvertI64S    = (byte)0xB9; // f64.convert_i64_s
    public static final byte F64ConvertI64U    = (byte)0xBA; // f64.convert_i64_u
    public static final byte F64PromoteF32     = (byte)0xBB; // f64.promote_f32
    public static final byte I32ReinterpretF32 = (byte)0xBC; // i32.reinterpret_f32
    public static final byte I64ReinterpretF64 = (byte)0xBD; // i64.reinterpret_f64
    public static final byte F32ReinterpretI32 = (byte)0xBE; // f32.reinterpret_i32
    public static final byte F64ReinterpretI64 = (byte)0xBF; // f64.reinterpret_i64
    public static final byte I32Extend8S       = (byte)0xC0; // i32.extend8_s
    public static final byte I32Extend16S      = (byte)0xC1; // i32.extend16_s
    public static final byte I64Extend8S       = (byte)0xC2; // i64.extend8_s
    public static final byte I64Extend16S      = (byte)0xC3; // i64.extend16_s
    public static final byte I64Extend32S      = (byte)0xC4; // i64.extend32_s
    public static final byte TruncSat          = (byte)0xFC; // <i32|64>.trunc_sat_<f32|64>_<s|u>

    
    public static Map<Byte, String> OP_NAMES = new HashMap<>();
    
    static {
        OP_NAMES.put(Unreachable, "unreachable");
        OP_NAMES.put(Nop, "nop");
        OP_NAMES.put(Block, "block");
        OP_NAMES.put(Loop, "loop");
        OP_NAMES.put(If, "if");
        OP_NAMES.put(Else_, "else");
        OP_NAMES.put(End_, "end");
        OP_NAMES.put(Br, "br");
        OP_NAMES.put(BrIf, "br_if");
        OP_NAMES.put(BrTable, "br_table");
        OP_NAMES.put(Return, "return");
        OP_NAMES.put(Call, "call");
        OP_NAMES.put(CallIndirect, "call_indirect");
        OP_NAMES.put(Drop, "drop");
        OP_NAMES.put(Select, "select");
        OP_NAMES.put(LocalGet, "local.get");
        OP_NAMES.put(LocalSet, "local.set");
        OP_NAMES.put(LocalTee, "local.tee");
        OP_NAMES.put(GlobalGet, "global.get");
        OP_NAMES.put(GlobalSet, "global.set");
        OP_NAMES.put(I32Load, "i32.load");
        OP_NAMES.put(I64Load, "i64.load");
        OP_NAMES.put(F32Load, "f32.load");
        OP_NAMES.put(F64Load, "f64.load");
        OP_NAMES.put(I32Load8S, "i32.load8_s");
        OP_NAMES.put(I32Load8U, "i32.load8_u");
        OP_NAMES.put(I32Load16S, "i32.load16_s");
        OP_NAMES.put(I32Load16U, "i32.load16_u");
        OP_NAMES.put(I64Load8S, "i64.load8_s");
        OP_NAMES.put(I64Load8U, "i64.load8_u");
        OP_NAMES.put(I64Load16S, "i64.load16_s");
        OP_NAMES.put(I64Load16U, "i64.load16_u");
        OP_NAMES.put(I64Load32S, "i64.load32_s");
        OP_NAMES.put(I64Load32U, "i64.load32_u");
        OP_NAMES.put(I32Store, "i32.store");
        OP_NAMES.put(I64Store, "i64.store");
        OP_NAMES.put(F32Store, "f32.store");
        OP_NAMES.put(F64Store, "f64.store");
        OP_NAMES.put(I32Store8, "i32.store8");
        OP_NAMES.put(I32Store16, "i32.store16");
        OP_NAMES.put(I64Store8, "i64.store8");
        OP_NAMES.put(I64Store16, "i64.store16");
        OP_NAMES.put(I64Store32, "i64.store32");
        OP_NAMES.put(MemorySize, "memory.size");
        OP_NAMES.put(MemoryGrow, "memory.grow");
        OP_NAMES.put(I32Const, "i32.const");
        OP_NAMES.put(I64Const, "i64.const");
        OP_NAMES.put(F32Const, "f32.const");
        OP_NAMES.put(F64Const, "f64.const");
        OP_NAMES.put(I32Eqz, "i32.eqz");
        OP_NAMES.put(I32Eq, "i32.eq");
        OP_NAMES.put(I32Ne, "i32.ne");
        OP_NAMES.put(I32LtS, "i32.lt_s");
        OP_NAMES.put(I32LtU, "i32.lt_u");
        OP_NAMES.put(I32GtS, "i32.gt_s");
        OP_NAMES.put(I32GtU, "i32.gt_u");
        OP_NAMES.put(I32LeS, "i32.le_s");
        OP_NAMES.put(I32LeU, "i32.le_u");
        OP_NAMES.put(I32GeS, "i32.ge_s");
        OP_NAMES.put(I32GeU, "i32.ge_u");
        OP_NAMES.put(I64Eqz, "i64.eqz");
        OP_NAMES.put(I64Eq, "i64.eq");
        OP_NAMES.put(I64Ne, "i64.ne");
        OP_NAMES.put(I64LtS, "i64.lt_s");
        OP_NAMES.put(I64LtU, "i64.lt_u");
        OP_NAMES.put(I64GtS, "i64.gt_s");
        OP_NAMES.put(I64GtU, "i64.gt_u");
        OP_NAMES.put(I64LeS, "i64.le_s");
        OP_NAMES.put(I64LeU, "i64.le_u");
        OP_NAMES.put(I64GeS, "i64.ge_s");
        OP_NAMES.put(I64GeU, "i64.ge_u");
        OP_NAMES.put(F32Eq, "f32.eq");
        OP_NAMES.put(F32Ne, "f32.ne");
        OP_NAMES.put(F32Lt, "f32.lt");
        OP_NAMES.put(F32Gt, "f32.gt");
        OP_NAMES.put(F32Le, "f32.le");
        OP_NAMES.put(F32Ge, "f32.ge");
        OP_NAMES.put(F64Eq, "f64.eq");
        OP_NAMES.put(F64Ne, "f64.ne");
        OP_NAMES.put(F64Lt, "f64.lt");
        OP_NAMES.put(F64Gt, "f64.gt");
        OP_NAMES.put(F64Le, "f64.le");
        OP_NAMES.put(F64Ge, "f64.ge");
        OP_NAMES.put(I32Clz, "i32.clz");
        OP_NAMES.put(I32Ctz, "i32.ctz");
        OP_NAMES.put(I32PopCnt, "i32.popcnt");
        OP_NAMES.put(I32Add, "i32.add");
        OP_NAMES.put(I32Sub, "i32.sub");
        OP_NAMES.put(I32Mul, "i32.mul");
        OP_NAMES.put(I32DivS, "i32.div_s");
        OP_NAMES.put(I32DivU, "i32.div_u");
        OP_NAMES.put(I32RemS, "i32.rem_s");
        OP_NAMES.put(I32RemU, "i32.rem_u");
        OP_NAMES.put(I32And, "i32.and");
        OP_NAMES.put(I32Or, "i32.or");
        OP_NAMES.put(I32Xor, "i32.xor");
        OP_NAMES.put(I32Shl, "i32.shl");
        OP_NAMES.put(I32ShrS, "i32.shr_s");
        OP_NAMES.put(I32ShrU, "i32.shr_u");
        OP_NAMES.put(I32Rotl, "i32.rotl");
        OP_NAMES.put(I32Rotr, "i32.rotr");
        OP_NAMES.put(I64Clz, "i64.clz");
        OP_NAMES.put(I64Ctz, "i64.ctz");
        OP_NAMES.put(I64PopCnt, "i64.popcnt");
        OP_NAMES.put(I64Add, "i64.add");
        OP_NAMES.put(I64Sub, "i64.sub");
        OP_NAMES.put(I64Mul, "i64.mul");
        OP_NAMES.put(I64DivS, "i64.div_s");
        OP_NAMES.put(I64DivU, "i64.div_u");
        OP_NAMES.put(I64RemS, "i64.rem_s");
        OP_NAMES.put(I64RemU, "i64.rem_u");
        OP_NAMES.put(I64And, "i64.and");
        OP_NAMES.put(I64Or, "i64.or");
        OP_NAMES.put(I64Xor, "i64.xor");
        OP_NAMES.put(I64Shl, "i64.shl");
        OP_NAMES.put(I64ShrS, "i64.shr_s");
        OP_NAMES.put(I64ShrU, "i64.shr_u");
        OP_NAMES.put(I64Rotl, "i64.rotl");
        OP_NAMES.put(I64Rotr, "i64.rotr");
        OP_NAMES.put(F32Abs, "f32.abs");
        OP_NAMES.put(F32Neg, "f32.neg");
        OP_NAMES.put(F32Ceil, "f32.ceil");
        OP_NAMES.put(F32Floor, "f32.floor");
        OP_NAMES.put(F32Trunc, "f32.trunc");
        OP_NAMES.put(F32Nearest, "f32.nearest");
        OP_NAMES.put(F32Sqrt, "f32.sqrt");
        OP_NAMES.put(F32Add, "f32.add");
        OP_NAMES.put(F32Sub, "f32.sub");
        OP_NAMES.put(F32Mul, "f32.mul");
        OP_NAMES.put(F32Div, "f32.div");
        OP_NAMES.put(F32Min, "f32.min");
        OP_NAMES.put(F32Max, "f32.max");
        OP_NAMES.put(F32CopySign, "f32.copysign");
        OP_NAMES.put(F64Abs, "f64.abs");
        OP_NAMES.put(F64Neg, "f64.neg");
        OP_NAMES.put(F64Ceil, "f64.ceil");
        OP_NAMES.put(F64Floor, "f64.floor");
        OP_NAMES.put(F64Trunc, "f64.trunc");
        OP_NAMES.put(F64Nearest, "f64.nearest");
        OP_NAMES.put(F64Sqrt, "f64.sqrt");
        OP_NAMES.put(F64Add, "f64.add");
        OP_NAMES.put(F64Sub, "f64.sub");
        OP_NAMES.put(F64Mul, "f64.mul");
        OP_NAMES.put(F64Div, "f64.div");
        OP_NAMES.put(F64Min, "f64.min");
        OP_NAMES.put(F64Max, "f64.max");
        OP_NAMES.put(F64CopySign, "f64.copysign");
        OP_NAMES.put(I32WrapI64, "i32.wrap_i64");
        OP_NAMES.put(I32TruncF32S, "i32.trunc_f32_s");
        OP_NAMES.put(I32TruncF32U, "i32.trunc_f32_u");
        OP_NAMES.put(I32TruncF64S, "i32.trunc_f64_s");
        OP_NAMES.put(I32TruncF64U, "i32.trunc_f64_u");
        OP_NAMES.put(I64ExtendI32S, "i64.extend_i32_s");
        OP_NAMES.put(I64ExtendI32U, "i64.extend_i32_u");
        OP_NAMES.put(I64TruncF32S, "i64.trunc_f32_s");
        OP_NAMES.put(I64TruncF32U, "i64.trunc_f32_u");
        OP_NAMES.put(I64TruncF64S, "i64.trunc_f64_s");
        OP_NAMES.put(I64TruncF64U, "i64.trunc_f64_u");
        OP_NAMES.put(F32ConvertI32S, "f32.convert_i32_s");
        OP_NAMES.put(F32ConvertI32U, "f32.convert_i32_u");
        OP_NAMES.put(F32ConvertI64S, "f32.convert_i64_s");
        OP_NAMES.put(F32ConvertI64U, "f32.convert_i64_u");
        OP_NAMES.put(F32DemoteF64, "f32.demote_f64");
        OP_NAMES.put(F64ConvertI32S, "f64.convert_i32_s");
        OP_NAMES.put(F64ConvertI32U, "f64.convert_i32_u");
        OP_NAMES.put(F64ConvertI64S, "f64.convert_i64_s");
        OP_NAMES.put(F64ConvertI64U, "f64.convert_i64_u");
        OP_NAMES.put(F64PromoteF32, "f64.promote_f32");
        OP_NAMES.put(I32ReinterpretF32, "i32.reinterpret_f32");
        OP_NAMES.put(I64ReinterpretF64, "i64.reinterpret_f64");
        OP_NAMES.put(F32ReinterpretI32, "f32.reinterpret_i32");
        OP_NAMES.put(F64ReinterpretI64, "f64.reinterpret_i64");
        OP_NAMES.put(I32Extend8S, "i32.extend8_s");
        OP_NAMES.put(I32Extend16S, "i32.extend16_s");
        OP_NAMES.put(I64Extend8S, "i64.extend8_s");
        OP_NAMES.put(I64Extend16S, "i64.extend16_s");
        OP_NAMES.put(I64Extend32S, "i64.extend32_s");
        OP_NAMES.put(TruncSat, "trunc_sat");
    }


}
