package xxl.core;

public abstract class IntervalFunction extends Function {
    
    private String _name;
    protected Range _range;
    
    public IntervalFunction(String name, Range range) {
        super(name);
        _name = name;
        _range = range;
    }
    
    protected abstract Literal compute();
    public String toString(){
        return "=" + _name + "(" + _range.toString() + ")";
    }
    
    public Range getRange() {
        return _range;
    }
}
