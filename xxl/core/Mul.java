package xxl.core;

public class Mul extends BinaryFunction {
    
    public Mul(Content arg0, Content arg1) {
        super("MUL", arg0, arg1);
    }

    @Override
    protected Literal compute() {
        try{
        return new LiteralInteger(getArg0().asInt() * getArg1().asInt());
        } catch(ArithmeticException e){
            return new LiteralString("#VALUE");
        }
    }

    @Override
    public String toString() {
        return compute().asString() + super.toString();
    }
}
