package xxl.core;

public class Coalesce extends IntervalFunction {
    
    public Coalesce(Range range) {
        super("COALESCE", range);
    }

    @Override   
    public Literal compute() {
        Literal result = new LiteralString("");
        for(Cell cell : getRange().getCells()) {
            if (cell.value().isString()) {
                result = new LiteralString(result.asString());
                return result;
            }
        }
        return result;
    }
}

