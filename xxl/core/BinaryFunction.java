package xxl.core;

public abstract class BinaryFunction extends Function {
    private String _name;
    private Content _arg0;
    private Content _arg1;

    protected abstract Literal compute();

    public BinaryFunction(String name, Content arg0, Content arg1) {
        super(name);
        _name = name;
        _arg0 = arg0;
        _arg1 = arg1;
        ConcreteSubject subject = ConcreteSubject.getInstance();
        subject.notifyObservers();
    }
    
    public String toString() {
        return "=" + _name + "(" + _arg0 + "," + _arg1 + ")";
    }

    public Content getArg0() {
        return _arg0;
    }

    public Content getArg1() {
        return _arg1;
    }
}
