package wasm.nav.function;

class PrintChar implements NativeFunction {

    @Override
    public Object[] execute(Object[] args) {
        int v = (int) args[0];
        System.out.print(new String(new byte[]{(byte) v}));
        return new Object[0];
    }

}
