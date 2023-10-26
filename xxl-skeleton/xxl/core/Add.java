package xxl.core;

public class Add extends BinaryFunction {

    public Add(Content arg0, Content arg1) {    
        super("ADD", arg0, arg1);
    }
    
    
    @Override
    protected Literal compute() {
        return new LiteralInteger(getArg0().asInt() + getArg1().asInt());
    }

}
