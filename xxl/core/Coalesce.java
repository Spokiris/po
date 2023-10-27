package xxl.core;

public class Coalesce extends IntervalFunction {
    
    public Coalesce(Range range) {
        super("COALESCE", range);
    }

    @Override
    public String toString(){
        return compute().asString() + super.toString();
    }

    @Override   
    public Literal compute() {
        Literal result = new LiteralString("");
        for(Cell cell : getRange().getCells()) {
        try{
            cell.value().asString();
            result = new LiteralString(cell.value().asString());
            return result;
        }
        catch(ArithmeticException e){
            continue;
            }
        }
        return result;
    }
}


