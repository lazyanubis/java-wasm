package wasm.core2.structure;

import wasm.core3.model.index.FunctionIndex;
import wasm.core3.model.index.GlobalIndex;
import wasm.core3.model.index.MemoryIndex;
import wasm.core3.model.index.TableIndex;
import wasm.core3.nav.function.NativeFunctions;
import wasm.core.numeric.U32;
import wasm.core.numeric.U64;
import wasm.core3.structure.Function;
import wasm.core2.instruction.Action;
import wasm.core2.instruction.Expression;
import wasm.core2.instruction.Instruction;
import wasm.core2.model.section.FunctionType;
import wasm.instruction2.dump.DumpMemory;

import java.util.HashMap;
import java.util.Map;

public class NativeInstance implements ModuleInstance {

    private static final Map<String, Object> EXPORTS = new HashMap<>();

    static {
        EXPORTS.put("print_char", NativeFunctions.PRINT_CHAR);
        EXPORTS.put("assert_true", NativeFunctions.ASSERT_TRUE);
        EXPORTS.put("assert_false", NativeFunctions.ASSERT_FALSE);
        EXPORTS.put("assert_eq_i32", NativeFunctions.ASSERT_EQUAL_INT);
        EXPORTS.put("assert_eq_i64", NativeFunctions.ASSERT_EQUAL_LONG);
    }

    @Override
    public Object getMember(String name) {
        return EXPORTS.get(name);
    }

    @Override
    public U64[] invoke(String name, U64... args) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void clearOperandStack() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void pushU64(U64 value) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void pushS64(long value) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void pushU32(U32 value) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void pushS32(int value) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void pushBool(boolean value) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void pushU64s(U64[] values) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public U64 popU64() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public long popS64() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public U32 popU32() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public int popS32() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public boolean popBool() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public U64[] popU64s(int size) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public U64 getOperand(int index) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void setOperand(int index, U64 value) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public int getFrameOffset() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public ControlFrame popFrame() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public ControlFrame topFrame() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public ControlFrame topCallFrame(int[] index) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public Memory getMemory(MemoryIndex index) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void setMemory(MemoryIndex index, Memory value) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void write(MemoryIndex index, U64 offset, byte[] data) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void read(MemoryIndex index, U64 offset, byte[] buffer) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void writeBytes(MemoryIndex index, DumpMemory args, byte[] data) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public byte[] readBytes(MemoryIndex index, DumpMemory args, int size) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public U32 size(MemoryIndex index) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public U32 grow(MemoryIndex index, U32 grow) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public Global getGlobal(GlobalIndex index) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void setGlobal(GlobalIndex index, Global value) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public Function getFunction(FunctionIndex index) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void setFunction(FunctionIndex index, Function function) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public Table getTable(TableIndex index) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void setTable(TableIndex index, Table table) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void loop() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void executeExpression(Expression expression) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void executeAction(Action action) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void enterBlock(Instruction instruction, FunctionType type, Expression expression) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void exitBlock() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void resetBlock(ControlFrame frame) {
        throw new RuntimeException("not for native module");
    }

    @Override
    public ModuleInfo getModuleInfo() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void linkImports() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void initFunctions() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void initTables() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void initMemories() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void initGlobals() {
        throw new RuntimeException("not for native module");
    }

    @Override
    public void execStartFunction() {
        throw new RuntimeException("not for native module");
    }

}
