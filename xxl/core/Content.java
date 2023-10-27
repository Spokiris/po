package xxl.core;

public abstract class Content {
    public abstract String toString();
    abstract Literal value();

    public String asString() {
        return value().asString();
    }

    public int asInt() {
        return value().asInt();
    }
}
