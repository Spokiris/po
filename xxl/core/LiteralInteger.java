package xxl.core;

public class LiteralInteger extends Literal {
    int _value;

    public LiteralInteger(int value) {
        _value = value;
    }

    public String toString() {
        return Integer.toString(_value);
    }

    public String asString() {
        return Integer.toString(_value);
    }

    public int asInt() {
        return _value;
    }
}
