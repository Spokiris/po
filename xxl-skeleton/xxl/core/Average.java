package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public class Average extends IntervalFunction{

    public Average(Range range) {
        super("AVERAGE", range);
    }

    @Override
    public Literal compute() throws UnrecognizedEntryException {
        Literal result = new LiteralInteger(0);
        int sum = 0;
        int count = 0;
        
        
        for(Cell cell : getRange().getCells()) {
                if (cell.value().isInt()) {
                    sum += cell.value().asInt();
                    count++;
                }
                else {
                    throw new UnrecognizedEntryException(null);
                }
                
        }
        return new LiteralInteger(result.asInt() + sum/count);
    }
}
