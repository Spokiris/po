package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public class Concat extends IntervalFunction {
    
    public Concat(Range range) {
        super("CONCAT", range);
    }

    @Override
    public Literal compute(){
        Literal result = new LiteralString("");
        for(Cell cell : getRange().getCells()) {
            result = new LiteralString(result.asString() + cell.value().asString());
        }
    return result;
    }
}
