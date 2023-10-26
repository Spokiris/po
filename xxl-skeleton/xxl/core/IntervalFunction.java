package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public abstract class IntervalFunction extends Function {
    
    private String _name;
    private Range _range;
    
    public IntervalFunction(String name, Range range) {
        super(name);
        _name = name;
        _range = range;
    }
    
    protected abstract Literal compute() throws UnrecognizedEntryException;
    
    public String toString() {
        return "=" + _name + "(" + _range.toString() + ")";
    }

    public Range getRange() {
        return _range;
    }
}
