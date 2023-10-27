package xxl.core;

public class Add extends BinaryFunction {

    public Add(Content arg0, Content arg1) {    
        super("ADD", arg0, arg1);
    }
    
    
    @Override
    protected Literal compute() {
        try{
        return new LiteralInteger(getArg0().asInt() + getArg1().asInt());
        } catch (ArithmeticException e){
            return new LiteralString("#VALUE");
        }
    }

    @Override
    public String toString() {
        return compute().asString() + super.toString();
    }
}
