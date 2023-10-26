package xxl.core;

public class Average extends IntervalFunction{

    public Average(Range range) {
        super("AVERAGE", range);
    }

    @Override
    public Literal compute() {
        Literal result = new LiteralInteger(0);
        int sum = 0;
        int count = 0;
        
        
        for(Cell cell : getRange().getCells()) {
                if (cell.value().isInt()) {
                    sum += cell.value().asInt();
                    count++;
                }
                else {
                    throw new IllegalArgumentException("Cell value is not an integer");
                    return new LiteralString("#VALUE");
                    break;
                }
                
        }
        return new LiteralInteger(result.asInt() + sum/count);
    }
}
