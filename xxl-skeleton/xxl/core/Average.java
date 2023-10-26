package xxl.core;

public class Average extends IntervalFunction{

    public Average(Range range) {
        super("AVERAGE", range);
    }

    @Override
    public Literal compute(){
        Literal result = new LiteralInteger(0);
        int sum = 0;
        int count = 0;
        
        
        for(Cell cell : getRange().getCells()) {
                if (cell.value().isInt()) {
                    sum += cell.value().asInt();
                    count++;
                }
            }
        return new LiteralInteger(result.asInt() + sum/count);
    }
}
