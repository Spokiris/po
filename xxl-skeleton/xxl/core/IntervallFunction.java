package xxl.core;

public abstract class IntervallFunction extends Function {
    
    private String _name;
    private Range _range;
    
    public IntervallFunction(String name, Range range) {
        super(name);
        _name = name;
        _range = range;
    }
    
    protected abstract Literal compute(Range range);
    
    public String toString() {
        return "=" + _name + "(" + _range.toString() + ")";
    }
}
