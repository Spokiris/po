package xxl.core;

public abstract class Content implements java.io.Serializable{
    static final long serialVersionUID = 202010272136L;
    public abstract String toString();
    abstract Literal value();

    public String asString() {
        return value().asString();
    }

    public int asInt() {
        return value().asInt();
    }
}
