package xxl.core;
import java.util.ArrayList;

public class Range {

    private int _startRow;
    private int _endRow;
    private int _startColumn;
    private int _endColumn;
    private Spreadsheet _spreadsheet;

    private ArrayList<Cell> _Rcells;

       public Range(int startRow, int endRow, int startColumn, int endColumn, Spreadsheet spreadsheet) {
        _startRow = startRow;
        _endRow = endRow;
        _startColumn = startColumn;
        _endColumn = endColumn;
        _spreadsheet = spreadsheet;
        _Rcells = new ArrayList<Cell>();
        for (int i = _startRow; i <= _endRow; i++) {
            for (int j = _startColumn; j <= _endColumn; j++) {
                _Rcells.add(_spreadsheet.getCell(i, j));
            }
        }
    }

    public int getStartRow() {
        return _startRow;
    }

    public int getEndRow() {
        return _endRow;
    }

    public int getStartColumn() {
        return _startColumn;
    }

    public int getEndColumn() {
        return _endColumn;
    }

    public int getRows() {
        return _endRow - _startRow;
    }

    public int getColumns() {
        return _endColumn - _startColumn;
    }
    
    public ArrayList<Cell> getCells() {
        return _Rcells;
    }

    public String toString(){
        String output = "";
        for (Cell cell : _Rcells) {
            if (cell.content() != null) {
            output += cell.toString()+"\n";
            }
        }
        return output; 
    }
}
