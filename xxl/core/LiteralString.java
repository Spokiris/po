package xxl.core;

public class LiteralString extends Literal {
    String _value;
    
    public LiteralString(String value) {
        _value = value;
    }

    public String toString() {
        return "'" + _value;
    }

    public String asString() {
        return _value;
    }

    public int asInt() {
        return Integer.parseInt(_value);
    }
}
