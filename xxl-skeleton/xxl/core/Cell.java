package xxl.core;

public class Cell {
    private int _row;
    private int _column;
    private Content _content;
    

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
    
    void setContent(String content) {
        _content = asContent(content);
    }

    public String toString() {
        return _row + ";" + _column + "|" + _content.toString();
    }

    Literal value(int row, int column) {
        return _content.value();
    }

    public String asString() {
        return _content.asString();
    }

    public int asInt() {
        return _content.asInt();
    }

    public Content asContent(String content) {
        if (content.startsWith("=(")) {
            return new Reference(content.substring(2, content.length() - 1)).value();
        } else if (content.startsWith("=")) {
            return new Function(content.substring(1)).compute().value();
        } else {
            try {
                return new LiteralInteger(Integer.parseInt(content));
            } catch (NumberFormatException e) {
                return new LiteralString(content);
            }
        }
    }
}
