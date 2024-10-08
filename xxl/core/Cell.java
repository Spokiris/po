package xxl.core;

import java.io.Serializable;


public class Cell implements Serializable{
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

    void setContent() {
        _content = null;
    }

    public String toString(){
        if (_content == null) {
            return _row + ";" + _column + "|";
        }
        return _row + ";" + _column + "|" + _content.toString();
    }

    Literal value() {
        if (_content == null) {
            return null;
        }
        return _content.value();
    }

    public String asString() {
        if (_content == null) {
            return "";
        }
        return _content.asString();
    }

    public int asInt() {
        if (_content == null) {
            return 0;
        }
        return _content.asInt();
    }

}
