package xxl.core;

import java.util.ArrayList;
public class CutBuffer {
    
    private ArrayList<Cell> _buffer;

    public CutBuffer() {
        _buffer = new ArrayList<Cell>();
    }

    public void copy(Range range) {
        clearBuff();
        ArrayList<Cell> cells = range.getCells();
        for (Cell c : cells){
            _buffer.add(new Cell(c));
        }
    }
    
    public void clearBuff() {
        if (_buffer != null){
            _buffer.clear();
        }
    }

    public ArrayList<Cell> paste(){
        return _buffer;
    }
}
