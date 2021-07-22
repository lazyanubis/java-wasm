package wasm.core;

import wasm.instruction.Expression;
import wasm.instruction.Expressions;
import wasm.instruction.Instruction;
import wasm.model.*;
import wasm.model.describe.ExportDescribe;
import wasm.model.describe.ImportDescribe;
import wasm.model.index.*;
import wasm.model.number.U32;
import wasm.model.tag.FunctionTypeTag;
import wasm.model.tag.LimitsTag;
import wasm.model.tag.PortTag;
import wasm.model.type.*;
import wasm.util.FileReader;
import wasm.util.Leb128;

import java.util.ArrayList;
import java.util.List;

import static wasm.util.Const.*;

public class WasmReader {

    private byte[] data;

    public WasmReader(byte[] data) {
        this.data = data;
    }

    public static Module readByName(String name) {
        WasmReader reader = new WasmReader(FileReader.readByName(name));
        return reader.readModule();
    }


    public Module readModule() {
        Module module = new Module();

        module.magic = new Magic(readByte(), readByte(), readByte(), readByte());
        module.version = new Version(readU32());
        module.customSections = new Custom[0];

        byte previousSectionId = 0;
        while (this.remaining() > 0) {
            byte sectionId = this.readByte();

            if (sectionId == SECTION_CUSTOM_ID) {
                Custom[] customs = new Custom[module.customSections.length + 1];
                System.arraycopy(module.customSections, 0, customs, 0, module.customSections.length);
                int size = readLeb128U32().intValue();
                byte[] data = new byte[size];
                System.arraycopy(this.data, 0, data, 0, size);
                drop(size);
                customs[customs.length - 1] = new WasmReader(data).readCustomSection();
                module.customSections = customs;
                continue;
            }

            if (sectionId > SECTION_DATA_COUNT_ID) {
                throw new RuntimeException(String.format("malformed section id: %d", sectionId));
            }
            if (sectionId <= previousSectionId) {
                if (previousSectionId == SECTION_DATA_COUNT_ID 
                        && (sectionId == SECTION_CODE_ID || sectionId == SECTION_DATA_ID)) {

                } else {
                    throw new RuntimeException(String.format("junk after last section, id: %d", sectionId));
                }
            }

            previousSectionId = sectionId;

            // 当前段的长度
            int n = readLeb128U32().intValue();

            int remaining = this.remaining();

            switch (sectionId) {
                case SECTION_TYPE_ID: module.typeSections = this.readTypeSections(); break;
                case SECTION_IMPORT_ID: module.importSections = this.readImportSections(); break;
                case SECTION_FUNCTION_ID: module.functionSections = this.readFunctionSections(); break;
                case SECTION_TABLE_ID: module.tableSections = this.readTableSections(); break;
                case SECTION_MEMORY_ID: module.memorySections = this.readMemorySections(); break;
                case SECTION_GLOBAL_ID: module.globalSections = this.readGlobalSections(); break;
                case SECTION_EXPORT_ID: module.exportSections = this.readExportSections(); break;
                case SECTION_START_ID: module.startFunctionIndex = new FunctionIndex(this.readLeb128U32()); break;
                case SECTION_ELEMENT_ID: module.elementSections = this.readElementSections(); break;
                case SECTION_CODE_ID: module.codeSections = this.readCodeSections(); break;
                case SECTION_DATA_ID: module.dataSections = this.readDataSections(); break;
            }

            if (this.remaining() + n != remaining) {
                throw new RuntimeException(String.format("section size mismatch, id: %d", sectionId));
            }

        }

        return module;
    }

    // =========================== tool ===========================

    private void drop(int length) {
        byte[] d = new byte[data.length - length];
//        System.out.print("drop: ");
//        for (int i = 0; i < length; i++) {
//            System.out.print(toHex(data[i]));
//            System.out.print(" ");
//        }
//        System.out.println();
        System.arraycopy(data, length, d, 0, d.length);
        this.data = d;
    }

    public byte readByte() {
        return readByte(true);
    }

    public byte readByte(boolean remove) {
        if (data.length < 1) {
            throw new RuntimeException("unexpected end of section or function");
        }
        byte b = data[0];
        if (remove) { drop(1); }
        return b;
    }

    public U32 readU32() {
        if (data.length < 4) {
            throw new RuntimeException("unexpected end of section or function");
        }
        U32 u32 = new U32(data[3], data[2], data[1], data[0]);
        drop(4);
        return u32;
    }

    public U32 readLeb128U32() {
        Leb128.U64Result r = Leb128.decodeVarUint(data, 32);
        drop(r.length);
        return r.result.u32();
    }

    public int readLeb128S32() {
        Leb128.S64Result r = Leb128.decodeVarInt(data, 32);
        drop(r.length);
        return (int) r.result;
    }

    public long readLeb128S64() {
        Leb128.S64Result r = Leb128.decodeVarInt(data, 64);
        drop(r.length);
        return r.result;
    }

    public byte[] readBytes() {
        U32 u32 = readLeb128U32();
        int n = u32.intValue();
        byte[] bytes = new byte[n];
        System.arraycopy(data, 0, bytes, 0, n);
        drop(n);
        return bytes;
    }

    public String readName() {
        byte[] bytes = this.readBytes();
        return new String(bytes);
    }

    public int remaining() {
        return data.length;
    }

    // 0

    public Custom readCustomSection() {
        return new Custom(readName(), this.data);
    }

    // 1

    public FunctionType[] readTypeSections() {
        FunctionType[] types = new FunctionType[readLeb128U32().intValue()];
        for (int i = 0; i < types.length; i++) {
            types[i] = readFunctionType();
        }
        return types;
    }

    private FunctionType readFunctionType() {
        byte tag = this.readByte();
        ValueType[] parameterTypes = readValueTypes();
        ValueType[] resultTypes = readValueTypes();
        return new FunctionType(FunctionTypeTag.of(tag), parameterTypes, resultTypes);
    }

    public ValueType[] readValueTypes() {
        int length = readLeb128U32().intValue();
        ValueType[] types = new ValueType[length];
        for (int i = 0; i < types.length; i++) {
            types[i] = readValueType();
        }
        return types;
    }

    private ValueType readValueType() {
        byte value = this.readByte();
        return ValueType.of(value);
    }

    // 2

    public Import[] readImportSections() {
        Import[] imports = new Import[readLeb128U32().intValue()];
        for (int i = 0; i < imports.length; i++) {
            imports[i] = new Import(this.readName(), this.readName(), this.readImportDescribe());
        }
        return imports;
    }

    public ImportDescribe readImportDescribe() {
        PortTag tag = PortTag.of(this.readByte());
        ImportDescribe.Value value = null;
        switch (tag.value()) {
            case 0x00: // FUNCTION
                value = new ImportDescribe.Function(readLeb128U32()); break;
            case 0x01: // TABLE
                value = new ImportDescribe.Table(readTableType()); break;
            case 0x02: // MEMORY
                value = new ImportDescribe.Memory(readLimitsWithMemoryType()); break;
            case 0x03: // GLOBAL
                value = new ImportDescribe.Global(readGlobalType()); break;
            default:
        }
        return new ImportDescribe(tag, value);
    }

    private TableType readTableType() {
        ReferenceType type = ReferenceType.of(this.readByte());

        Limits limits = readLimits();

        return new TableType(type, limits);
    }

    private Limits readLimits() {
        LimitsTag tag = LimitsTag.of(this.readByte());
        U32 min = this.readLeb128U32();
        U32 max = null;
        if (tag == LimitsTag.ONE) {
            max = this.readLeb128U32();
        }
        return new Limits(tag, min, max);
    }

    private MemoryType readLimitsWithMemoryType() {
        Limits limits = this.readLimits();
        return new MemoryType(limits.getTag(), limits.getMin(), limits.getMax());
    }

    private GlobalType readGlobalType() {
        return new GlobalType(ValueType.of(this.readByte()), MutableType.of(this.readByte()));
    }

    // 3

    public TypeIndex[] readFunctionSections() {
        int n = readLeb128U32().intValue();
        TypeIndex[] indices = new TypeIndex[n];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = new TypeIndex(this.readLeb128U32());
        }
        return indices;
    }

    // 4

    public TableType[] readTableSections() {
        int n = readLeb128U32().intValue();
        TableType[] types = new TableType[n];
        for (int i = 0; i < types.length; i++) {
            types[i] = this.readTableType();
        }
        return types;
    }

    // 5

    public MemoryType[] readMemorySections() {
        int n = readLeb128U32().intValue();
        MemoryType[] types = new MemoryType[n];
        for (int i = 0; i < types.length; i++) {
            types[i] = readLimitsWithMemoryType();
        }
        return types;
    }

    // 6

    public Global[] readGlobalSections() {
        int n = readLeb128U32().intValue();
        Global[] globals = new Global[n];
        for (int i = 0; i < globals.length; i++) {
            globals[i] = this.readGlobal();
        }
        return globals;
    }

    private Global readGlobal() {
        GlobalType type = new GlobalType(ValueType.of(this.readByte()), MutableType.of(this.readByte()));
        Expressions expressions = readExpressions();
        return new Global(type, expressions);
    }

    // 7

    public Export[] readExportSections() {
        int n = readLeb128U32().intValue();
        Export[] exports = new Export[n];
        for (int i = 0; i < exports.length; i++) {
            exports[i] = new Export(this.readName(),
                    new ExportDescribe(PortTag.of(this.readByte()), this.readLeb128U32()));
        }
        return exports;
    }

    // 9

    public Element[] readElementSections() {
        int n = readLeb128U32().intValue();
        Element[] elements = new Element[n];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = readElement();
        }
        return elements;
    }

    private Element readElement() {
        byte tag = readByte();

        Element.Value value = null;

        switch (tag) {
            case 0x00: value = new Element.Value0(readExpressions(), readFunctionIndices()); break;
            case 0x01: value = new Element.Value1(readByte(), readFunctionIndices()); break;
            case 0x02: value = new Element.Value2(new TableIndex(readLeb128U32()), readExpressions(), readByte(), readFunctionIndices()); break;
            case 0x03: value = new Element.Value3(readByte(), readFunctionIndices()); break;
            case 0x04: value = new Element.Value4(readExpressions(), readExpresses()); break;
            case 0x05: value = new Element.Value5(ReferenceType.of(readByte()), readExpresses()); break;
            case 0x06: value = new Element.Value6(new TableIndex(readLeb128U32()), readExpressions(), ReferenceType.of(readByte()), readExpresses()); break;
            case 0x07: value = new Element.Value7(ReferenceType.of(readByte()), readExpresses()); break;
        }

        return new Element(tag, value);
    }

    private FunctionIndex[] readFunctionIndices() {
        int n = readLeb128U32().intValue();
        FunctionIndex[] indices = new FunctionIndex[n];
        for (int i = 0; i < n; i++) {
            indices[i] = new FunctionIndex(readLeb128U32());
        }
        return indices;
    }

    private Expressions[] readExpresses() {
        int n = readLeb128U32().intValue();
        Expressions[] expressions = new Expressions[n];
        for (int i = 0; i < n; i++) {
            expressions[i] = readExpressions();
        }
        return expressions;
    }

    // 10

    public Code[] readCodeSections() {
//        System.out.println(">>>> read code");
        int n = readLeb128U32().intValue();
        Code[] codes = new Code[n];
        for (int i = 0; i < codes.length; i++) {
            U32 size = this.readLeb128U32();
            int s = size.intValue();
//            System.out.println("code [" + i + "] size: " + s);
            byte[] data = new byte[s];
            System.arraycopy(this.data, 0, data, 0, s);
            drop(s);
            WasmReader reader = new WasmReader(data);
            codes[i] = new Code(size, reader.readLocals(), reader.readExpressions());
        }
        return codes;
    }

    private Local[] readLocals() {
        Local[] locals = new Local[readLeb128U32().intValue()];
        for (int i = 0; i < locals.length; i++) {
            locals[i] = new Local(this.readLeb128U32(), ValueType.of(this.readByte()));
        }
        return locals;
    }

    // 11

    public Data[] readDataSections() {
        int n = readLeb128U32().intValue();
        Data[] data = new Data[n];
        for (int i = 0; i < data.length; i++) {
            data[i] = readData();
        }
        return data;
    }

    private Data readData() {
        byte tag = readByte();

        Data.Value value = null;

        switch (tag) {
            case 0x00: value = new Data.Value0(readExpressions(), readBytes()); break;
            case 0x01: value = new Data.Value1(readBytes()); break;
            case 0x02: value = new Data.Value2(new MemoryIndex(readLeb128U32()), readExpressions(), readBytes()); break;
        }

        return new Data(tag, value);
    }

    // 读取表达式

    public Expressions readExpressions() {
//        System.out.println("read expressions");
        Expression[] expressions = readExpressionArray();
        byte end = readByte();
        if (end != EXPRESS_END && end != EXPRESS_ELSE) {
            // 读取完表达式后，末尾应该有结尾
            throw new RuntimeException(String.format("invalid expr end: %d", end));
        }
        return new Expressions(expressions);
    }

    private Expression[] readExpressionArray() {
        List<Expression> expressions = new ArrayList<>();
        while (true) {
            byte end = data[0];
            if (end == EXPRESS_END || end == EXPRESS_ELSE) {
                return expressions.toArray(new Expression[0]);
            }
            Expression expression = readExpression();
            expressions.add(expression);
        }
    }

    private Expression readExpression() {
        byte opcode = readByte();

        Instruction instruction = Instruction.of(opcode);
//        System.out.println("read expressions code: " + toHex(opcode) + " " + instruction.name);

        switch (instruction.opcode) {
            case (byte) 0x3F: readZero(); break; // 当前指令后面还有个0
            case (byte) 0x40: readZero(); break; // 当前指令后面还有个0
            case (byte) 0xFC:
                U32 n = readLeb128U32();
                switch (n.intValue()) {
                    // 表格指令拓展
                    case 12: instruction = Instruction.TABLE_INIT; break;
                    case 13: instruction = Instruction.TABLE_DROP; break;
                    case 14: instruction = Instruction.TABLE_COPY; break;
                    case 15: instruction = Instruction.TABLE_GROW; break;
                    case 16: instruction = Instruction.TABLE_SIZE; break;
                    case 17: instruction = Instruction.TABLE_FILL; break;
                    // 内存指令拓展
                    case  8: instruction = Instruction.MEMORY_INIT; readZero(); break;
                    case  9: instruction = Instruction.DATA_DROP; break;
                    case 10: instruction = Instruction.MEMORY_COPY; readZero(); readZero(); break;
                    case 11: instruction = Instruction.MEMORY_FILL; readZero(); break;
                    // 0~7 浮点数操作相关 不支持
                    // 0 => i32.trunc_sat_f32_s
                    // 1 => i32.trunc_sat_f32_u
                    // 2 => i32.trunc_sat_f64_s
                    // 3 => i32.trunc_sat_f64_u
                    // 4 => i64.trunc_sat_f32_s
                    // 5 => i64.trunc_sat_f32_u
                    // 6 => i64.trunc_sat_f64_s
                    // 7 => i64.trunc_sat_f64_u
                    default: throw new RuntimeException("what ?");
                }
                break;
        }

        Dump args = instruction.readArgs(this);
        return new Expression(instruction, args);
    }


    public LocalIndex readLocalIndex() {
        return new LocalIndex(this.readLeb128U32());
    }

    public FunctionIndex readFunctionIndex() {
        return new FunctionIndex(this.readLeb128U32());
    }

    public GlobalIndex readGlobalIndex() {
        return new GlobalIndex(this.readLeb128U32());
    }

    public TableIndex readTableIndex() {
        return new TableIndex(this.readLeb128U32());
    }

    public ElementIndex readElementIndex() {
        return new ElementIndex(this.readLeb128U32());
    }

    public DataIndex readDataIndex() {
        return new DataIndex(this.readLeb128U32());
    }

    private byte readZero() {
        byte zero = readByte();
        if (zero != 0) {
            throw new RuntimeException(String.format("zero flag expected, got %d", zero));
        }
        return 0x0;
    }

    public TypeIndex readTypeIndex() {
        return new TypeIndex(this.readLeb128U32());
    }

    public BlockType readBlockType() {
        ValueType valueType = null;
        long s33 = 0;
        byte b = data[0];
        if ((b & 0b10000000) == 0) {
            // 首位是0 必须是ValueType
            valueType = ValueType.of(readByte());
        } else {
            Leb128.S64Result s = Leb128.decodeVarInt33(data);
            drop(s.length);
            s33 = s.result;
        }
        return new BlockType(valueType, s33);
    }

    public LabelIndex readLabelIndex() {
        return new LabelIndex(readLeb128U32());
    }

    public LabelIndex[] readLabelIndices() {
        int n = readLeb128U32().intValue();
        LabelIndex[] indices = new LabelIndex[n];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = readLabelIndex();
        }
        return indices;
    }

//    private Dump readArgs(byte opcode) {
//        switch (opcode) {
//            case Block: return readBlockArgs();
//            case Loop: return readLoopArgs();
//
//            case If: return readIfArgs();
//
//            case Br:
//            case BrIf:
//
//            case Call:
//
//            case LocalGet:
//            case LocalSet:
//            case LocalTee:
//
//            case GlobalGet:
//            case GlobalSet:
//                return new DumpUint32(readLeb128Uint32());
//
//            case BrTable: return readBrTableArgs();
//
//            case CallIndirect: return new DumpUint32(readCallIndirectArgs());
//
//            case MemorySize:
//            case MemoryGrow: return new DumpZero(readZero());
//
//            case I32Const: return new DumpInt32(readLeb128Int32());
//
//            case I64Const: return new DumpInt64(readLeb128Int64());
//
//            case F32Const: return new DumpFloat32(readFloat32());
//
//            case F64Const: return new DumpFloat64(readFloat64());
//
//            case TruncSat: return new DumpByte(readByte());
//
//            default:
//                if (opcode >= I32Load && opcode <= I64Store32) {
//                    return readMemArg();
//                }
//        }
//        return null;
//    }
//
//    private BlockArgs readBlockArgs() {
//        BlockArgs r = new BlockArgs();
//        r.blockType = BlockType.valueOf((byte) readLeb128Int32());
//        r.instructions = readInstructions();
//        byte end = readByte();
//        if (end != End_) {
//            throw new RuntimeException(String.format("invalid block end: %d", end));
//        }
//        return r;
//    }
//
//    private LoopArgs readLoopArgs() {
//        LoopArgs r = new LoopArgs();
//        r.blockType = BlockType.valueOf((byte) readLeb128Int32());
//        r.instructions = readInstructions();
//        byte end = readByte();
//        if (end != End_) {
//            throw new RuntimeException(String.format("invalid block end: %d", end));
//        }
//        return r;
//    }
//
//    private IfArgs readIfArgs() {
//        IfArgs r = new IfArgs();
//        r.blockType = BlockType.valueOf((byte) readLeb128Int32());
//        r.instructions1 = readInstructions();
//        byte end = readByte();
//        if (end == Else_) {
//            r.instructions2 = readInstructions();
//            end = readByte();
//        }
//        if (end != End_) {
//            throw new RuntimeException(String.format("invalid block end: %d", end));
//        }
//        return r;
//    }
//
//    private BrTableArgs readBrTableArgs() {
//        BrTableArgs r = new BrTableArgs();
//        r.labels = readLeb128Uint32s();
//        r.omit = readLeb128Uint32();
//        return r;
//    }
//
//    private Uint32 readCallIndirectArgs() {
//        Uint32 index = readLeb128Uint32();
//        readZero();
//        return index;
//    }



}
