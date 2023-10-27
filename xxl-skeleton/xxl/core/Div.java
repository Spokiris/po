package xxl.core;

public class Div extends BinaryFunction {
    
    public Div(Content arg0, Content arg1) {    
        super("DIV", arg0, arg1);
    }

    @Override
    protected Literal compute() {
        if(getArg1().asInt() != 0) {
            return new LiteralInteger(getArg0().asInt() / getArg1().asInt());
        } else {
            return new LiteralString("#VALUE");
        }
    }

    @Override
    public String toString() {
        return compute().asString() + super.toString();
    }
}
