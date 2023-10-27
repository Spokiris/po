package xxl.core;

public class Reference extends Content{
    private int _row;
    private int _column;
    private Cell _cell;

    public Reference(int row, int column, Spreadsheet spreadsheet) {
        _row = row;
        _column = column;
        _cell = spreadsheet.getCell(row, column);
    }   

    public Reference(String substring, Spreadsheet spreadsheet) {
        String[] parts = substring.split(";");
        _row = Integer.parseInt(parts[0]);
        _column = Integer.parseInt(parts[1]);
        _cell = spreadsheet.getCell(_row, _column);
    }

    public int row() {
        return _row;
    }
    
    public int column() {
        return _column;
    }

    public String toString() {
        return  "=" + _row + ";" + _column ;
    }

    public Cell getCell(){
        return _cell;
    }
    
    @Override
    Literal value(){
        return _cell.value();
    }
}
