package xxl.core;

public class Sub extends BinaryFunction {

    public Sub(Content arg0, Content arg1) {
        super("SUB", arg0, arg1);
    }

    @Override
    protected Literal compute() {
        return new LiteralInteger(getArg0().asInt() - getArg1().asInt());
    }
    
    @Override
    public String toString() {
        return compute().asString() + super.toString();
    }
}
