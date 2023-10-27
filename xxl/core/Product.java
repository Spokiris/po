package xxl.core;

public class Product extends IntervalFunction {

    public Product(Range range) {
        super("PRODUCT", range);
    }

    @Override
    public String toString(){
        return compute().asString() + super.toString();
    }

    @Override
    public Literal compute(){
        Literal result = new LiteralInteger(1);
        for (Cell cell : getRange().getCells()) {
            if(cell.value() != null && cell.value().isInt()) {
                result = new LiteralInteger(result.asInt() * cell.value().asInt());
            } else{
                return new LiteralString("#VALUE");
            }
        }
        return result;
    }

    
}
