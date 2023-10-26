package xxl.core;

public class Product extends IntervalFunction { //FIX

    public Product(Range range) {
        super("PRODUCT", range);
    }

    @Override
    public Literal compute() {
        Literal result = new Literal(1);
        for (Literal literal : getRange()) {
            result = result.multiply(literal);
        }
        return result;
    }

    
}
