package xxl.core;

import java.util.List;

public class CutBuffer {
    
    private List<Cell> _buffer;

    public CutBuffer(){
    }
    
    public void setBuffer(Range range){
        for (int i = range.getStartRow(); i <= range.getEndRow(); i++) {
            for (int j = range.getStartColumn(); j <= range.getEndColumn(); j++) {
                _buffer.add(new Cell(i, j));
            }
        }
    } 

    public void copy(Range range) {
        clearBuff();
        setBuffer(range);
    }
    
    public void clearBuff() {
        _buffer.clear(); 
    }

    public List<Cell> getBuffer() {
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
