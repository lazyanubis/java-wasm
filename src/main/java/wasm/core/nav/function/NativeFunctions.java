package wasm.core.nav.function;

import wasm.core.structure.Function;

public class NativeFunctions {

    public static final Function PRINT_CHAR = new Print.PrintChar();
    public static final Function PRINT_I64 = new Print.PrintI64();
    public static final Function PRINT_I32 = new Print.PrintI32();

    public static final Function ASSERT_TRUE = new Assert.AssertTrue();
    public static final Function ASSERT_FALSE = new Assert.AssertFalse();
    public static final Function ASSERT_EQUAL_INT = new Assert.AssertEqualInt();
    public static final Function ASSERT_EQUAL_LONG = new Assert.AssertEqualLong();

}
