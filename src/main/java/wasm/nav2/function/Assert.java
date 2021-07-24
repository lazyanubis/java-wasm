package wasm.nav2.function;

class Assert {

    static class AssertTrue implements NativeFunction {
        @Override
        public Object[] execute(Object[] args) {
            if ((int) args[0] == 0) {
                // 0 则为 false
                throw new RuntimeException("not equals: " + args[0] + " == 1");
            }
            return new Object[0];
        }
    }

    static class AssertFalse implements NativeFunction {
        @Override
        public Object[] execute(Object[] args) {
            if ((int) args[0] != 0) {
                // 0 则为 true
                throw new RuntimeException("not equals: " + args[0] + " == 0");
            }
            return new Object[0];
        }
    }

    static class AssertEqualInt implements NativeFunction {
        @Override
        public Object[] execute(Object[] args) {
            if ((int) args[0] != (int) args[1]) {
                // 0 则为 true
                throw new RuntimeException("not equals: " + args[0] + " == " + args[1]);
            }
            return new Object[0];
        }
    }

    static class AssertEqualLong implements NativeFunction {
        @Override
        public Object[] execute(Object[] args) {
            if ((long) args[0] != (long) args[1]) {
                // 0 则为 true
                throw new RuntimeException("not equals: " + args[0] + " == " + args[1]);
            }
            return new Object[0];
        }
    }

}
