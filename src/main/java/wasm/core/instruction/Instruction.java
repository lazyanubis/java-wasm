package wasm.core.instruction;

import wasm.core2.VirtualMachine;
import wasm.core.structure.WasmReader;
import wasm.instruction2.control.*;
import wasm.instruction2.memory.*;
import wasm.instruction2.numeric.*;
import wasm.instruction2.numeric.i32.I32Const;
import wasm.instruction2.numeric.i32.compare.*;
import wasm.instruction2.numeric.i32.operate.*;
import wasm.instruction2.numeric.i64.I64Const;
import wasm.instruction2.numeric.i64.compare.*;
import wasm.instruction2.numeric.i64.operate.*;
import wasm.instruction2.parametric.Drop;
import wasm.instruction2.parametric.Select;
import wasm.instruction2.parametric.SelectC;
import wasm.instruction2.reference.RefFunc;
import wasm.instruction2.reference.RefIsNull;
import wasm.instruction2.reference.RefNull;
import wasm.instruction2.table.*;
import wasm.instruction2.variable.*;
import wasm.core.model.Dump;

import static wasm.core.util.NumberTransform.toHex;

public enum Instruction {

    // 控制指令
    UNREACHABLE  ((byte) 0x00, "unreachable", new Unreachable()),
    NOP          ((byte) 0x01, "nop", new Nop()),
    BLOCK        ((byte) 0x02, "block", new Block()),
    LOOP         ((byte) 0x03, "loop", new Loop()),
    IF_BLOCK     ((byte) 0x04, "if", new IfBlock()),
    
    BR           ((byte) 0x0C, "br", new Br()),
    BR_IF        ((byte) 0x0D, "br_if", new BrIf()),
    BR_TABLE     ((byte) 0x0E, "br_table", new BrTable()),
    RETURN       ((byte) 0x0F, "return", new Return()),
    CALL         ((byte) 0x10, "call", new Call()),
    CALL_INDIRECT((byte) 0x11, "call_indirect", new CallIndirect()),

    // 引用指令
    REF_NULL(   (byte) 0xD0, "ref.null", new RefNull()),
    REF_IS_NULL((byte) 0xD1, "ref.is_null", new RefIsNull()),
    REF_FUNC(   (byte) 0xD2, "ref.func", new RefFunc()),

    // 参数指令
    DROP(       (byte) 0x1A, "drop", new Drop()),
    SELECT(     (byte) 0x1B, "select", new Select()),
    SELECT_C(   (byte) 0x1C, "select.t*", new SelectC()),

    // 变量指令
    LOCAL_GET( (byte) 0x20, "local.get", new LocalGet()),
    LOCAL_SET( (byte) 0x21, "local.set", new LocalSet()),
    LOCAL_TEE( (byte) 0x22, "local.tee", new LocalTee()),
    GLOBAL_GET((byte) 0x23, "global.get", new GlobalGet()),
    GLOBAL_SET((byte) 0x24, "global.set", new GlobalSet()),

    // 表指令
    TABLE_GET((byte) 0x25, "table.get", new TableGet()),
    TABLE_SET((byte) 0x26, "table.set", new TableSet()),
    TABLE_INIT((byte) 0xFC, "table.init", new TableInit()),
    TABLE_DROP((byte) 0xFC, "table.drop", new TableDrop()),
    TABLE_COPY((byte) 0xFC, "table.copy", new TableCopy()),
    TABLE_GROW((byte) 0xFC, "table.grow", new TableGrow()),
    TABLE_SIZE((byte) 0xFC, "table.size", new TableSize()),
    TABLE_FILL((byte) 0xFC, "table.fill", new TableFill()),

    // 内存指令
    I32_LOAD((byte) 0x28, "i32.load", new I32Load()),
    I64_LOAD((byte) 0x29, "i64.load", new I64Load()),
    // 浮点数操作 0x2A m:memarg => f32.load m
    // 浮点数操作 0x2B m:memarg => f64.load m
    I32_LOAD8_S( (byte) 0x2C, "i32.load8_s", new I32Load8S()),
    I32_LOAD8_U( (byte) 0x2D, "i32.load8_u", new I32Load8U()),
    I32_LOAD16_S((byte) 0x2E, "i32.load16_s", new I32Load16S()),
    I32_LOAD16_U((byte) 0x2F, "i32.load16_u", new I32Load16U()),
    I64_LOAD8_S( (byte) 0x30, "i64.load8_s", new I64Load8S()),
    I64_LOAD8_U( (byte) 0x31, "i64.load8_u", new I64Load8U()),
    I64_LOAD16_S((byte) 0x32, "i64.load16_s", new I64Load16S()),
    I64_LOAD16_U((byte) 0x33, "i64.load16_u", new I64Load16U()),
    I64_LOAD32_S((byte) 0x34, "i64.load32_s", new I64Load32S()),
    I64_LOAD32_U((byte) 0x35, "i64.load32_u", new I64Load32U()),
    I32_STORE((byte) 0x36, "i32.store", new I32Store()),
    I64_STORE((byte) 0x37, "i64.store", new I64Store()),
    // 浮点数操作 0x38 m:memarg => f32.store m
    // 浮点数操作 0x39 m:memarg => f64.store m
    I32_STORE8( (byte) 0x3A, "i32.store8", new I32Store8()),
    I32_STORE16((byte) 0x3B, "i32.store16", new I32Store16()),
    I64_STORE8( (byte) 0x3C, "i64.store8", new I64Store8()),
    I64_STORE16((byte) 0x3D, "i64.store16", new I64Store16()),
    I64_STORE32((byte) 0x3E, "i64.store32", new I64Store32()),
    MEMORY_SIZE((byte) 0x3F, "memory.size", new MemorySize()),
    MEMORY_GROW((byte) 0x40, "memory.grow", new MemoryGrow()),
    MEMORY_INIT((byte) 0xFC, "memory.init", new MemoryInit()),
    DATA_DROP(  (byte) 0xFC, "data.drop", new DataDrop()),
    MEMORY_COPY((byte) 0xFC, "memory.copy", new MemoryCopy()),
    MEMORY_FILL((byte) 0xFC, "memory.fill", new MemoryFill()),

    // 数值指令
    I32_CONST((byte) 0x41, "i32.const", new I32Const()),
    I64_CONST((byte) 0x42, "i64.const", new I64Const()),
    // 浮点数操作 0x43 z:f32 => f32.const z
    // 浮点数操作 0x44 z:f64 => f64.const z
    I32_EQZ ((byte) 0x45, "i32.eqz", new I32Eqz()),
    I32_EQ  ((byte) 0x46, "i32.eq", new I32Eq()),
    I32_NE  ((byte) 0x47, "i32.ne", new I32Ne()),
    I32_LT_S((byte) 0x48, "i32.lt_s", new I32LtS()),
    I32_LT_U((byte) 0x49, "i32.lt_u", new I32LtU()),
    I32_GT_S((byte) 0x4A, "i32.gt_s", new I32GtS()),
    I32_GT_U((byte) 0x4B, "i32.gt_u", new I32GtU()),
    I32_LE_S((byte) 0x4C, "i32.le_s", new I32LeS()),
    I32_LE_U((byte) 0x4D, "i32.le_u", new I32LeU()),
    I32_GE_S((byte) 0x4E, "i32.ge_s", new I32GeS()),
    I32_GE_U((byte) 0x4F, "i32.ge_u", new I32GeU()),
    I64_EQZ ((byte) 0x50, "i64.eqz", new I64Eqz()),
    I64_EQ  ((byte) 0x51, "i64.eq", new I64Eq()),
    I64_NE  ((byte) 0x52, "i64.ne", new I64Ne()),
    I64_LT_S((byte) 0x53, "i64.lt_s", new I64LtS()),
    I64_LT_U((byte) 0x54, "i64.lt_u", new I64LtU()),
    I64_GT_S((byte) 0x55, "i64.gt_s", new I64GtS()),
    I64_GT_U((byte) 0x56, "i64.gt_u", new I64GtU()),
    I64_LE_S((byte) 0x57, "i64.le_s", new I64LeS()),
    I64_LE_U((byte) 0x58, "i64.le_u", new I64LeU()),
    I64_GE_S((byte) 0x59, "i64.ge_s", new I64GeS()),
    I64_GE_U((byte) 0x5A, "i64.ge_u", new I64GeU()),
    // 浮点数操作 0x5B => f32.eq
    // 浮点数操作 0x5C => f32.ne
    // 浮点数操作 0x5D => f32.lt
    // 浮点数操作 0x5E => f32.gt
    // 浮点数操作 0x5F => f32.le
    // 浮点数操作 0x60 => f32.ge
    // 浮点数操作 0x61 => f64.eq
    // 浮点数操作 0x62 => f64.ne
    // 浮点数操作 0x63 => f64.lt
    // 浮点数操作 0x64 => f64.gt
    // 浮点数操作 0x65 => f64.le
    // 浮点数操作 0x66 => f64.ge
    I32_CLZ   ((byte) 0x67, "i32.clz", new I32Clz()),
    I32_CTZ   ((byte) 0x68, "i32.ctz", new I32Ctz()),
    I32_POPCNT((byte) 0x69, "i32.popcnt", new I32Popcnt()),
    I32_ADD   ((byte) 0x6A, "i32.add", new I32Add()),
    I32_SUB   ((byte) 0x6B, "i32.sub", new I32Sub()),
    I32_MUL   ((byte) 0x6C, "i32.mul", new I32Mul()),
    I32_DIV_S ((byte) 0x6D, "i32.div_s", new I32DivS()),
    I32_DIV_U ((byte) 0x6E, "i32.div_u", new I32DivU()),
    I32_REM_S ((byte) 0x6F, "i32.rem_s", new I32RemS()),
    I32_REM_U ((byte) 0x70, "i32.rem_u", new I32RemU()),
    I32_AND   ((byte) 0x71, "i32.and", new I32And()),
    I32_OR    ((byte) 0x72, "i32.or", new I32Or()),
    I32_XOR   ((byte) 0x73, "i32.xor", new I32Xor()),
    I32_SHL   ((byte) 0x74, "i32.shl", new I32Shl()),
    I32_SHR_S ((byte) 0x75, "i32.shr_s", new I32ShrS()),
    I32_SHR_U ((byte) 0x76, "i32.shr_u", new I32ShrU()),
    I32_ROTL  ((byte) 0x77, "i32.rotl", new I32Rotl()),
    I32_ROTR  ((byte) 0x78, "i32.rotr", new I32Rotr()),
    I64_CLZ   ((byte) 0x79, "i64.clz", new I64Clz()),
    I64_CTZ   ((byte) 0x7A, "i64.ctz", new I64Ctz()),
    I64_POPCNT((byte) 0x7B, "i64.popcnt", new I64Popcnt()),
    I64_ADD   ((byte) 0x7C, "i64.add", new I64Add()),
    I64_SUB   ((byte) 0x7D, "i64.sub", new I64Sub()),
    I64_MUL   ((byte) 0x7E, "i64.mul", new I64Mul()),
    I64_DIV_S ((byte) 0x7F, "i64.div_s", new I64DivS()),
    I64_DIV_U ((byte) 0x80, "i64.div_u", new I64DivU()),
    I64_REM_S ((byte) 0x81, "i64.rem_s", new I64RemS()),
    I64_REM_U ((byte) 0x82, "i64.rem_u", new I64RemU()),
    I64_AND   ((byte) 0x83, "i64.and", new I64And()),
    I64_OR    ((byte) 0x84, "i64.or", new I64Or()),
    I64_XOR   ((byte) 0x85, "i64.xor", new I64Xor()),
    I64_SHL   ((byte) 0x86, "i64.shl", new I64Shl()),
    I64_SHR_S ((byte) 0x87, "i64.shr_s", new I64ShrS()),
    I64_SHR_U ((byte) 0x88, "i64.shr_u", new I64ShrU()),
    I64_ROTL  ((byte) 0x89, "i64.rotl", new I64Rotl()),
    I64_ROTR  ((byte) 0x8A, "i64.rotr", new I64Rotr()),
    // 浮点数操作 0x8B => f32.abs
    // 浮点数操作 0x8C => f32.neg
    // 浮点数操作 0x8D => f32.ceil
    // 浮点数操作 0x8E => f32.floor
    // 浮点数操作 0x8F => f32.trunc
    // 浮点数操作 0x90 => f32.nearest
    // 浮点数操作 0x91 => f32.sqrt
    // 浮点数操作 0x92 => f32.add
    // 浮点数操作 0x93 => f32.sub
    // 浮点数操作 0x94 => f32.mul
    // 浮点数操作 0x95 => f32.div
    // 浮点数操作 0x96 => f32.min
    // 浮点数操作 0x97 => f32.max
    // 浮点数操作 0x98 => f32.copysign
    // 浮点数操作 0x99 => f64.abs
    // 浮点数操作 0x9A => f64.neg
    // 浮点数操作 0x9B => f64.ceil
    // 浮点数操作 0x9C => f64.floor
    // 浮点数操作 0x9D => f64.trunc
    // 浮点数操作 0x9E => f64.nearest
    // 浮点数操作 0x9F => f64.sqrt
    // 浮点数操作 0xA0 => f64.add
    // 浮点数操作 0xA1 => f64.sub
    // 浮点数操作 0xA2 => f64.mul
    // 浮点数操作 0xA3 => f64.div
    // 浮点数操作 0xA4 => f64.min
    // 浮点数操作 0xA5 => f64.max
    // 浮点数操作 0xA6 => f64.copysign
    I32_WRAP_I64((byte) 0xA7, "i32.wrap_i64", new I32WrapI64()),
    // 浮点数操作 0xA8 => i32.trunc_f32_s
    // 浮点数操作 0xA9 => i32.trunc_f32_u
    // 浮点数操作 0xAA => i32.trunc_f64_s
    // 浮点数操作 0xAB => i32.trunc_f64_u
    I64_EXTEND_I32_S((byte) 0xAC, "i64.extend_i32_s", new I64ExtendI32S()),
    I64_EXTEND_I32_U((byte) 0xAD, "i64.extend_i32_u", new I64ExtendI32U()),
    // 浮点数操作 0xAE => i64.trunc_f32_s
    // 浮点数操作 0xAF => i64.trunc_f32_u
    // 浮点数操作 0xB0 => i64.trunc_f64_s
    // 浮点数操作 0xB1 => i64.trunc_f64_u
    // 浮点数操作 0xB2 => f32.convert_i32_s
    // 浮点数操作 0xB3 => f32.convert_i32_u
    // 浮点数操作 0xB4 => f32.convert_i64_s
    // 浮点数操作 0xB5 => f32.convert_i64_u
    // 浮点数操作 0xB6 => f32.demote_f64
    // 浮点数操作 0xB7 => f64.convert_i32_s
    // 浮点数操作 0xB8 => f64.convert_i32_u
    // 浮点数操作 0xB9 => f64.convert_i64_s
    // 浮点数操作 0xBA => f64.convert_i64_u
    // 浮点数操作 0xBB => f64.promote_f32
    // 浮点数操作 0xBC => i32.reinterpret_f32
    // 浮点数操作 0xBD => i64.reinterpret_f64
    // 浮点数操作 0xBE => f32.reinterpret_i32
    // 浮点数操作 0xBF => f64.reinterpret_f64
    I32_EXTEND8_S ((byte) 0xC0, "i32.extend8_s", new I32Extend8S()),
    I32_EXTEND16_S((byte) 0xC1, "i32.extend16_s", new I32Extend16S()),
    I64_EXTEND8_S ((byte) 0xC2, "i64.extend8_s", new I64Extend8S()),
    I64_EXTEND16_S((byte) 0xC3, "i64.extend16_s", new I64Extend16S()),
    I64_EXTEND32_S((byte) 0xC4, "i64.extend32_s", new I64Extend32S()),


    ;

    public byte opcode;
    public String name;
    public Operate operate;

    Instruction(byte opcode, String name, Operate operate) {
        this.opcode = opcode;
        this.name = name;
        this.operate = operate;
    }

    public void operate(VirtualMachine vm, Dump args) {
        assert null != operate;
        operate.operate(vm, args);
    }

    public Dump readArgs(WasmReader reader) {
        assert null != operate;
        return operate.read(reader);
    }

    public static Instruction of(byte value) {
        for (Instruction instruction : Instruction.values()) {
            if (instruction.opcode == value) {
                return instruction;
            }
        }
        throw new RuntimeException("unknown code: 0x" + toHex(value));
    }

}
