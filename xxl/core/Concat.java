package xxl.core;
public class Concat extends IntervalFunction {
    
    public Concat(Range range) {
        super("CONCAT", range);
    }
    
    @Override
    public String toString(){
        return compute().asString() + super.toString();
    }

    @Override
    public Literal compute(){
        Literal result = new LiteralString("");
        for(Cell cell : getRange().getCells()) {
            try{
            result = new LiteralString(result.asString() + cell.value().asString());
            } catch (ArithmeticException e){
                continue;
            }
        }
    return result;
    }
}
