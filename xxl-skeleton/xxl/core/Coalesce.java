package xxl.core;

public class Coalesce extends IntervalFunction {
    
    public Coalesce(Range range) {
        super("COALESCE", range);
    }

    @Override   
    public Literal compute() {
        Literal result = new Literal();
        for (Literal literal : getRange()) {
            if (literal.isInt()) {
                result = literal;
                break;
            }
        }
        return result;
    }

}
