package xxl.core;

public abstract class Content implements Observer{
    public abstract String toString();
    abstract Literal value();
    public abstract void update();

    public String asString() {
        return value().asString();
    }

    public int asInt() {
        return value().asInt();
    }
}
