package xxl.core;

public abstract class Content implements java.io.Serializable{
    static final long serialVersionUID = 202010272136L;
    public abstract String toString();
    abstract Literal value();

    public String asString() {
        if (value()==null)
            return "";
        return value().asString();
    }

    public int asInt() {
        if (value()==null)
            return 0;
        return value().asInt();
    }
}
