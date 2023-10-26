package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public class Reference {
    private int _row;
    private int _column;
    private Spreadsheet _spreadsheet;

    public Reference(int row, int column, Spreadsheet spreadsheet) {
        _row = row;
        _column = column;
        _spreadsheet = spreadsheet;
    }   

    public Reference(String substring) {
        String[] parts = substring.split(";");
        _row = Integer.parseInt(parts[0]);
        _column = Integer.parseInt(parts[1]);
        _spreadsheet = null;//FIXME
    }

    public int row() {
        return _row;
    }
    
    public int column() {
        return _column;
    }

    public Spreadsheet spreadsheet() {
        return _spreadsheet;
    }   

    public String toString() {
        return "=" + _row + ";" + _column ;
    }
    
    Literal value() throws UnrecognizedEntryException {
        return _spreadsheet.value(_row, _column);
    }
}
