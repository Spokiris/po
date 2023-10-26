package xxl.core;

public abstract class BinaryFunction extends Function {
    private String _name;
    private Content _arg1;
    private Content _arg2;

    protected abstract Literal compute(Literal arg1, Literal arg2);

    public BinaryFunction(String name, Content arg1, Content arg2) {
        super(name);
        _name = name;
        _arg1 = arg1;
        _arg2 = arg2;
    }
    
    public String toString() {
        return "=" + _name + "(" + _arg1 + "," + _arg2 + ")";
    }
}
