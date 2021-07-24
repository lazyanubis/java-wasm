package wasm.nav2.function;

public interface NativeFunction {

    Object[] execute(Object[] args);

    NativeFunction PRINT_CHAR = new PrintChar();
    NativeFunction ASSERT_TRUE = new Assert.AssertTrue();
    NativeFunction ASSERT_FALSE = new Assert.AssertFalse();
    NativeFunction ASSERT_EQUAL_INT = new Assert.AssertEqualInt();
    NativeFunction ASSERT_EQUAL_LONG = new Assert.AssertEqualLong();

}
