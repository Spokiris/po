package xxl.core;

import java.util.List;
import java.util.ArrayList;

public class Range {

    private int _startRow;
    private int _endRow;
    private int _startColumn;
    private int _endColumn;

    private List<Cell> _Rcells = new ArrayList<>();

    public Range(String range) {
        String[] parts = range.split(":");
        String[] start = parts[0].split("(?<=\\D)(?=\\d)");
        String[] end = parts[1].split("(?<=\\D)(?=\\d)");
        _startRow = Integer.parseInt(start[1]);
        _endRow = Integer.parseInt(end[1]);
        _startColumn = (int) start[0].charAt(0) - 64;
        _endColumn = (int) end[0].charAt(0) - 64;
    }

    public Range(int startRow, int endRow, int startColumn, int endColumn) {
        _startRow = startRow;
        _endRow = endRow;
        _startColumn = startColumn;
        _endColumn = endColumn;
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
        return _endRow - _startRow + 1;
    }

    public int getColumns() {
        return _endColumn - _startColumn + 1;
    }
    
    List<Cell> getCells() {
        return _Rcells;
    }

    public void addCell(Cell cell) {
        _Rcells.add(cell);
    }

    public String toString(){ //FIXME
        String output = "";
        for (Cell cell : _Rcells) {
            output += cell.toString() + "\n";
        }
        return output; 
    }
}
