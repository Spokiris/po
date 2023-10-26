package xxl.core;

public class Sub extends BinaryFunction {

    public Sub(Literal arg0, Literal arg1) {
        super("SUB", arg0, arg1);
    }

    @Override
    protected Literal compute() {
        return new LiteralInteger(getArg0().asInt() - getArg1().asInt());
    }
    
}
