package xxl.core;
public abstract class Function extends Content {
    private String _name;
    public void update(){
        value();
    }
    protected abstract Literal compute();

    public Function(String name) {
        _name = name;
        ConcreteSubject subject = ConcreteSubject.getInstance();
        subject.addObserver(this);
    }

    public String asString() {
        return _name;
    }

    public int asInt() {
        return compute().asInt();
    }

    public Literal value() {
        return compute();
    }
}
