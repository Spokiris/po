package xxl.core;

public class Mul extends BinaryFunction {
    
    public Mul(Content arg0, Content arg1) {
        super("MUL", arg0, arg1);
    }

    @Override
    protected Literal compute() {
        return new LiteralInteger(getArg0().asInt() * getArg1().asInt());
    }

    @Override
    public String toString() {
        return compute().asString() + super.toString();
    }
}
