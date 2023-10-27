package xxl.core;

import java.util.ArrayList;

public class CutBuffer {
    
    private ArrayList<Cell> _buffer;

    public CutBuffer(){
        _buffer = new ArrayList<Cell>();
    }
    
    public void setBuffer(Range range){
        if (_buffer != null){
            clearBuff();
        }
        ArrayList<Cell> buffer = range.getCells();
        for (Cell cell : buffer){
            _buffer.add(new Cell(cell));    
        }
    } 

    public void copy(Range range) {
        setBuffer(range);
    }
    
    public void clearBuff() {
        if (_buffer != null){
            _buffer.clear();
        }
    }

    public ArrayList<Cell> getBuffer() {
        return _buffer;
    }

    public String toString(){
        String result = "";
        for (Cell cell : _buffer) {
            result += cell.toString()+"\n";
        }
        return result;
    }

}
