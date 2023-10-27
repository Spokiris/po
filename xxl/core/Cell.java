package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public class Cell {
    private int _row;
    private int _column;
    private Content _content;
    private Spreadsheet _spreadsheet;
    

    public Cell(int row, int column) {
        _row = row;
        _column = column;
        _content = null;
        
    }

    public Cell(int row, int column, Content content) {
        _row = row;
        _column = column;
        _content = content;
    }

    public Cell(Cell cell){
        _row = cell.row();
        _column = cell.column();
        _content = cell.content();
    }

    public int row() {
        return _row;
    }

    public int column() {
        return _column;
    }

    public Content content() {
        return _content;
    }

    void setContent(Content content) {
        _content = content;
    }
    
    void setContent(String content) throws UnrecognizedEntryException {
        _content = asContent(content);
    }

    void setContent() {
        _content = null;
    }

    public String toString() {
        return _row + ";" + _column + "|" + _content.toString();
    }

    Literal value() {
        return _content.value();
    }

    public String asString() {
        return _content.asString();
    }

    public int asInt() {
        return _content.asInt();
    }

    public Content asContent(String content) throws UnrecognizedEntryException {
        if (content.startsWith("=(")) {
            return new Reference(content.substring(2, content.length() - 1),_spreadsheet).value();
        } else if (content.startsWith("=")) {
            return new Function(content.substring(1)) {
                @Override
                public Literal compute() {
                    return null;
                }

                @Override
                public String toString() {
                    return null;
                }
            }.compute().value();
        } else {
            try {
                return new LiteralInteger(Integer.parseInt(content));
            } catch (NumberFormatException e) {
                return new LiteralString(content);
            }
        }
    }
}
