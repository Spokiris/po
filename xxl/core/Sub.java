package xxl.core;

public class Sub extends BinaryFunction {

    public Sub(Content arg0, Content arg1) {
        super("SUB", arg0, arg1);
    }

    @Override
    protected Literal compute() {
        try{
        return new LiteralInteger(getArg0().asInt() - getArg1().asInt());
        } catch(ArithmeticException e){
            return new LiteralString("#VALUE");
        }
    }
    
    @Override
    public String toString() {
        return compute().asString() + super.toString();
    }
}
