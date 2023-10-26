package xxl.core;

public class Range {

    private int _startRow;
    private int _endRow;
    private int _startColumn;
    private int _endColumn;

    public Range(String range) {
        String[] parts = range.split(":");
        String[] start = parts[0].split("(?<=\\D)(?=\\d)");
        String[] end = parts[1].split("(?<=\\D)(?=\\d)");
        _startRow = Integer.parseInt(start[1]);
        _endRow = Integer.parseInt(end[1]);
        _startColumn = (int) start[0].charAt(0) - 64;
        _endColumn = (int) end[0].charAt(0) - 64;
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
}
