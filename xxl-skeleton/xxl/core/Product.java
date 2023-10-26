package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public class Product extends IntervalFunction { //FIX

    public Product(Range range) {
        super("PRODUCT", range);
    }

    @Override
    public Literal compute() throws UnrecognizedEntryException {
        Literal result = new LiteralInteger(1);
        for (Cell cell : getRange().getCells()) {
            if(cell.value().isInt()) {
                result = new LiteralInteger(result.asInt() * cell.value().asInt());
            }
            else {
                throw new UnrecognizedEntryException(null);
            }
        }
        return result;
    }

    
}
