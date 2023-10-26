package xxl.core;

import java.util.ArrayList;
import java.util.List;

public class CutBuffer { //FIXME abstract class
    
    private List<Cell> _buffer = new ArrayList<>();
    private Range _range;

    public CutBuffer(String range) {
        _range = new Range(range);//FIXME implemnt parse in range
        
        for (int i = _range.getStartRow(); i <= _range.getEndRow(); i++) {
            for (int j = _range.getStartColumn(); j <= _range.getEndColumn(); j++) {
                _buffer.add(new Cell(i, j)); //FIXME implement Cell and only add Content to buffer
            }
        }
    }   

    public void copy(String range) {
        CutBuffer cutBuffer = new CutBuffer(range); //FIXME implement CutBuffer
        
        
    }

    public void clear() {
        _buffer.removeAll(_buffer); //FIXME implement removeAll
        // FIXME implement clear
    }

    public void paste(String range) {
        // FIXME implement paste dimensions must match

        
    }

    public void cut(String range) {
        // FIXME implement cut
    }

    public void insert(String range) {
        // FIXME implement insert
    }

    public void delete(String range) {
        // FIXME implement delete
    }

    List<Cell> getCells() {
        return _buffer;
    }

}
