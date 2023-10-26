package xxl.core;

import java.util.ArrayList;
import java.util.List;

public class CutBuffer { //FIXME abstract class
    
    private List<Cell> _buffer = new ArrayList<>();
    private Range _range;

    public CutBuffer(String range) {
        _range = new Range(range);
        
        for (int i = _range.getStartRow(); i <= _range.getEndRow(); i++) {
            for (int j = _range.getStartColumn(); j <= _range.getEndColumn(); j++) {
                _buffer.add(new Cell(i, j)); //FIXME implement Cell and only add Content to buffer
            }
        }
    }   

    public CutBuffer(Range range) {
        _range = range;
        for (int i = _range.getStartRow(); i <= _range.getEndRow(); i++) {
            for (int j = _range.getStartColumn(); j <= _range.getEndColumn(); j++) {
                _buffer.add(new Cell(i, j)); //FIXME implement Cell and only add Content to buffer
            }
        }
    }

    public Range getRange(){
        return _range;
    }

    public void copy(String range) {
        clearBuff();
        new CutBuffer(range);
    }

    public void copy(Range range) {
        clearBuff();
        new CutBuffer(range);
    }
    
    public void clearBuff() {
            _buffer.clear(); 
    }

    List<Cell> getCells() {
        return _buffer;
    }

}
