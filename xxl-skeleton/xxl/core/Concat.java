package xxl.core;

public class Concat extends IntervalFunction {
    
    public Concat(Range range) {
        super("CONCAT", range);
    }

    @Override
    public Literal compute() throws UnrecognizedEntryException {
        Literal result = new LiteralString("");
        for(Cell cell : getRange().getCells()) {
            if (cell.value().isString()) {
                result = new LiteralString(result.asString() + cell.value().asString());
            }
            else {
                throw new UnrecognizedEntryException(null);
            }
        }
        return result;
    }
}
