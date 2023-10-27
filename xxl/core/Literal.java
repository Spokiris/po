package xxl.core;

public abstract class Literal extends Content {
    private boolean _isInt;
    private boolean _isString = !_isInt;
    
    public void update(){
        // Do Nothing
    }
    
    public Literal() {//FIXME
    }
    
    Literal value() {
        return this;
    }
    
    public boolean isInt() {
        return _isInt;
    }

    public boolean isString() {
        return _isString;
    }

    

}