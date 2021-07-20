package wasm.model;

import wasm.model.instruction.*;
import wasm.model.instruction.util.*;
import wasm.model.section.*;
import wasm.model.section.util.*;
import wasm.util.FileReader;

import java.util.ArrayList;
import java.util.List;

import static wasm.model.instruction.Instruction.*;
import static wasm.model.section.util.Const.*;

public class WasmReader {

    private static String toBinaryString(byte b) {
        String s = Integer.toBinaryString(b);
        if (s.length() > 8) {
            return s.substring(24);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8 - s.length(); i++) {
            sb.append("0");
        }
        return sb + s;
    }

    private byte[] data;

    public WasmReader(byte[] data) {
        this.data = data;
    }

    private void drop(int length) {
        byte[] d = new byte[data.length - length];
        System.arraycopy(data, length, d, 0, d.length);
        this.data = d;
    }

    public byte readByte() {
        if (data.length < 1) {
            throw new RuntimeException("unexpected end of section or function");
        }
        byte b = data[0];
        drop(1);
        return b;
    }

    public Uint32 readUint32() {
        if (data.length < 4) {
            throw new RuntimeException("unexpected end of section or function");
        }
        String sb = toBinaryString(data[3]) +
                toBinaryString(data[2]) +
                toBinaryString(data[1]) +
                toBinaryString(data[0]);
        drop(4);
        return new Uint32(sb);
    }

    public float readFloat32() {
        if (data.length < 4) {
            throw new RuntimeException("unexpected end of section or function");
        }
        String sb = toBinaryString(data[3]) +
                toBinaryString(data[2]) +
                toBinaryString(data[1]) +
                toBinaryString(data[0]);
        drop(4);
        return Float.intBitsToFloat(Integer.valueOf(sb, 2));
    }

    public double readFloat64() {
        if (data.length < 8) {
            throw new RuntimeException("unexpected end of section or function");
        }
        String sb =
                toBinaryString(data[7]) +
                toBinaryString(data[6]) +
                toBinaryString(data[5]) +
                toBinaryString(data[4]) +
                toBinaryString(data[3]) +
                toBinaryString(data[2]) +
                toBinaryString(data[1]) +
                toBinaryString(data[0]);
        drop(8);
        return Double.longBitsToDouble((Long.valueOf(sb, 2)));
    }

    public Uint32 readLeb128Uint32() {
        Leb128.Uint64Result r = Leb128.decodeVarUint(data, 32);
        drop(r.length);
        return new Uint32(r.result.getValue());
    }

    public int readLeb128Int32() {
        Leb128.Int64Result r = Leb128.decodeVarInt(data, 32);
        drop(r.length);
        return (int) r.result;
    }

    public long readLeb128Int64() {
        Leb128.Int64Result r = Leb128.decodeVarInt(data, 64);
        drop(r.length);
        return r.result;
    }

    public byte[] readBytes() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
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


    public FunctionType[] readTypeSections() {
        FunctionType[] types = new FunctionType[Integer.valueOf(this.readLeb128Uint32().getValue(), 2)];
        for (int i = 0; i < types.length; i++) {
            types[i] = readFunctionType();
        }
        return types;
    }

    private FunctionType readFunctionType() {
        byte tag = this.readByte();
        ValueType[] parameterTypes = readValueTypes();
        ValueType[] resultTypes = readValueTypes();
        return new FunctionType(ValueType.valueOf(tag), parameterTypes, resultTypes);
    }

    private ValueType[] readValueTypes() {
        int length = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        ValueType[] types = new ValueType[length];
        for (int i = 0; i < types.length; i++) {
            types[i] = readValueType();
        }
        return types;
    }

    private ValueType readValueType() {
        byte value = this.readByte();
        return ValueType.valueOf(value);
    }

    public ImportDescribe readImportDescribe() {
        ImportType type = ImportType.valueOf(this.readByte());
        Uint32 functionType = null;
        TableType table = null;
        MemoryType memory = null;
        Global global = null;
        switch (type) {
            case FUNCTION: functionType = this.readLeb128Uint32(); break;
            case TABLE: table = this.readTableType(); break;
            case MEMORY: memory = readLimitWithMemoryType(); break;
            case GLOBAL: global = readGlobal(); break;
            default:
        }
        return new ImportDescribe(type, functionType, table, memory, global);
    }

    private TableType readTableType() {
        ValueType type = ValueType.valueOf(this.readByte());

        Limit limit = this.readLimit();

        return new TableType(type, limit);
    }

    private Limit readLimit() {
        LimitTag tag = LimitTag.valueOf(this.readByte());
        Uint32 min = this.readLeb128Uint32();
        Uint32 max = null;
        switch (tag) {
            case TAG1: max = this.readLeb128Uint32(); break;
        }
        return new Limit(tag, min, max);
    }

    private MemoryType readLimitWithMemoryType() {
        Limit limit = this.readLimit();
        return new MemoryType(limit.tag, limit.min, limit.max);
    }


    private Global readGlobal() {
        GlobalType type = new GlobalType(ValueType.valueOf(this.readByte()), VariableType.valueOf(this.readByte()));
        Object init = readExpression();
        return new Global(type, init);
    }

    private Instruction[] readExpression() {
        Instruction[] instructions = readInstructions();
        byte end = readByte();
        if (end != End_) {
            throw new RuntimeException(String.format("invalid expr end: %d", end));
        }
        return instructions;
    }

    private Instruction[] readInstructions() {
        List<Instruction> instructions = new ArrayList<>();
        while (true) {
            byte end = data[0];
            if (end == Else_ || end == End_) {
                return instructions.toArray(new Instruction[0]);
            }
            Instruction instruction = readInstruction();
            instructions.add(instruction);
        }
    }

    private Instruction readInstruction() {
        Instruction instruction = new Instruction();
        instruction.opcode = readByte();
        if (!OP_NAMES.containsKey(instruction.opcode)) {
            throw new RuntimeException(String.format("undefined opcode: 0x%02x", instruction.opcode));
        }
        instruction.args = readArgs(instruction.opcode);
        return instruction;
    }

    private Dump readArgs(byte opcode) {
        switch (opcode) {
            case Block: return readBlockArgs();
            case Loop: return readLoopArgs();

            case If: return readIfArgs();

            case Br:
            case BrIf:

            case Call:

            case LocalGet:
            case LocalSet:
            case LocalTee:

            case GlobalGet:
            case GlobalSet:
                return new DumpUint32(readLeb128Uint32());

            case BrTable: return readBrTableArgs();

            case CallIndirect: return new DumpUint32(readCallIndirectArgs());

            case MemorySize:
            case MemoryGrow: return new DumpZero(readZero());

            case I32Const: return new DumpInt32(readLeb128Int32());

            case I64Const: return new DumpInt64(readLeb128Int64());

            case F32Const: return new DumpFloat32(readFloat32());

            case F64Const: return new DumpFloat64(readFloat64());

            case TruncSat: return new DumpByte(readByte());

            default:
                if (opcode >= I32Load && opcode <= I64Store32) {
                    return readMemArg();
                }
        }
        return null;
    }

    private BlockArgs readBlockArgs() {
        BlockArgs r = new BlockArgs();
        r.blockType = BlockType.valueOf((byte) readLeb128Int32());
        r.instructions = readInstructions();
        byte end = readByte();
        if (end != End_) {
            throw new RuntimeException(String.format("invalid block end: %d", end));
        }
        return r;
    }

    private LoopArgs readLoopArgs() {
        LoopArgs r = new LoopArgs();
        r.blockType = BlockType.valueOf((byte) readLeb128Int32());
        r.instructions = readInstructions();
        byte end = readByte();
        if (end != End_) {
            throw new RuntimeException(String.format("invalid block end: %d", end));
        }
        return r;
    }

    private IfArgs readIfArgs() {
        IfArgs r = new IfArgs();
        r.blockType = BlockType.valueOf((byte) readLeb128Int32());
        r.instructions1 = readInstructions();
        byte end = readByte();
        if (end == Else_) {
            r.instructions2 = readInstructions();
            end = readByte();
        }
        if (end != End_) {
            throw new RuntimeException(String.format("invalid block end: %d", end));
        }
        return r;
    }

    private BrTableArgs readBrTableArgs() {
        BrTableArgs r = new BrTableArgs();
        r.labels = readLeb128Uint32s();
        r.omit = readLeb128Uint32();
        return r;
    }

    private Uint32 readCallIndirectArgs() {
        Uint32 index = readLeb128Uint32();
        readZero();
        return index;
    }

    private byte readZero() {
        byte zero = readByte();
        if (zero != 0) {
            throw new RuntimeException(String.format("zero flag expected, got %d", zero));
        }
        return 0x0;
    }

    private MemoryArg readMemArg() {
        MemoryArg r = new MemoryArg();

        r.align = readLeb128Uint32();
        r.offset = readLeb128Uint32();

        return r;
    }

//    public Code readCode() {
//        byte[] data = this.readBytes();
//        WasmReader reader = new WasmReader(data);
//        return new Code(reader.readLocals());
//    }

    private Local[] readLocals() {
        Local[] locals = new Local[Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue()];
        for (int i = 0; i < locals.length; i++) {
            locals[i] = new Local(this.readLeb128Uint32(), ValueType.valueOf(this.readByte()));
        }
        return locals;
    }



    public Custom readCustomSection() {
        return new Custom(readName(), this.data);
    }

    public Import[] readImportSections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        Import[] imports = new Import[n];
        for (int i = 0; i < imports.length; i++) {
            imports[i] = new Import(this.readName(), this.readName(), this.readImportDescribe());
        }
        return imports;
    }

    public TypeIndex[] readFunctionSections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        TypeIndex[] indices = new TypeIndex[n];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = new TypeIndex(this.readLeb128Uint32());
        }
        return indices;
    }

    public TableType[] readTableSections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        TableType[] types = new TableType[n];
        for (int i = 0; i < types.length; i++) {
            types[i] = this.readTableType();
        }
        return types;
    }

    public MemoryType[] readMemorySections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        MemoryType[] types = new MemoryType[n];
        for (int i = 0; i < types.length; i++) {
            types[i] = readLimitWithMemoryType();
        }
        return types;
    }

    public Global[] readGlobalSections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        Global[] globals = new Global[n];
        for (int i = 0; i < globals.length; i++) {
            globals[i] = this.readGlobal();
        }
        return globals;
    }

    public Export[] readExportSections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        Export[] exports = new Export[n];
        for (int i = 0; i < exports.length; i++) {
            exports[i] = new Export(this.readName(),
                    new ExportDescribe(ExportType.valueOf(this.readByte()), this.readLeb128Uint32()));
        }
        return exports;
    }

    public Element[] readElementSections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        Element[] elements = new Element[n];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Element(this.readLeb128Uint32(), this.readExpression(), readLeb128Uint32s());
        }
        return elements;
    }

    private Uint32[] readLeb128Uint32s() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        Uint32[] uint32s = new Uint32[n];
        for (int i = 0; i < uint32s.length; i++) {
            uint32s[i] = this.readLeb128Uint32();
        }
        return uint32s;
    }


    public Code[] readCodeSections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        Code[] codes = new Code[n];
        for (int i = 0; i < codes.length; i++) {
            Uint32 size = this.readLeb128Uint32();
            int s = size.value();
            byte[] data = new byte[s];
            System.arraycopy(this.data, 0, data, 0, s);
            drop(s);
            WasmReader reader = new WasmReader(data);
            codes[i] = new Code(size, reader.readLocals(), reader.readExpression());
        }
        return codes;
    }

    public Data[] readDataSections() {
        int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();
        Data[] data = new Data[n];
        for (int i = 0; i < data.length; i++) {
            data[i] = readData();
        }
        return data;
    }

    private Data readData() {
        Uint32 memory = this.readLeb128Uint32();
        return new Data(memory, this.readExpression(), this.readBytes());
    }

    public Module readModule() {
        Module module = new Module();

        module.magic = new Magic(this.readUint32().getValue());
        module.version = new Version(this.readUint32().getValue());
        module.customSections = new Custom[0];

        byte previousSectionId = 0;
        while (this.remaining() > 0) {
            byte sectionId = this.readByte();

            if (sectionId == SECTION_CUSTOM_ID) {
                Custom[] customs = new Custom[module.customSections.length + 1];
                System.arraycopy(module.customSections, 0, customs, 0, module.customSections.length);
                int size = (int) Long.parseLong(this.readLeb128Uint32().getValue(), 2);
                byte[] data = new byte[size];
                System.arraycopy(this.data, 0, data, 0, size);
                drop(size);
                customs[customs.length - 1] = new WasmReader(data).readCustomSection();
                module.customSections = customs;
                continue;
            }

            if (sectionId > SECTION_DATA_ID) {
                throw new RuntimeException(String.format("malformed section id: %d", sectionId));
            }
            if (sectionId <= previousSectionId) {
                throw new RuntimeException(String.format("junk after last section, id: %d", sectionId));
            }

            previousSectionId = sectionId;

            int n = Long.valueOf(this.readLeb128Uint32().getValue(), 2).intValue();

            int remaining = this.remaining();

            switch (sectionId) {
                case SECTION_TYPE_ID: module.typeSections = this.readTypeSections(); break;
                case SECTION_IMPORT_ID: module.importSections = this.readImportSections(); break;
                case SECTION_FUNCTION_ID: module.functionSections = this.readFunctionSections(); break;
                case SECTION_TABLE_ID: module.tableSections = this.readTableSections(); break;
                case SECTION_MEMORY_ID: module.memorySections = this.readMemorySections(); break;
                case SECTION_GLOBAL_ID: module.globalSections = this.readGlobalSections(); break;
                case SECTION_EXPORT_ID: module.exportSections = this.readExportSections(); break;
                case SECTION_START_ID: module.startFunctionIndex = this.readLeb128Uint32(); break;
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

    public static Module readByName(String name) {
        WasmReader reader = new WasmReader(FileReader.readByName(name));
        return reader.readModule();
    }

}
