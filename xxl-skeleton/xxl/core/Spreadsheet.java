package xxl.core;

// FIXME import classes


import java.io.Serializable;

import xxl.core.exception.UnrecognizedEntryException;

import java.util.ArrayList;
import java.util.List;
/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;
  private int _rows;
  private int _columns;
  private boolean _changed;
  private List<User> _users = new ArrayList<>();
  private CutBuffer _cutBuffer;
  private Cell[][] _cells = new Cell[_rows][_columns];
  
  public Spreadsheet(int rows, int columns) {
    _rows = rows;
    _columns = columns;
    _changed = false;
  }

  public boolean isCell(int row, int column) {
    return row <= _rows && column <= _columns && row > 0 && column > 0;
  }

  public int getRows() {
    return _rows;
  }

  public int getColumns() {
    return _columns;
  }

  public boolean isChanged() {
    return _changed;
  }

  public List<Cell> getCutBuffer() {
    return _cutBuffer.getCells(); 
  }

  public void copy(Range range) {
    _cutBuffer.copy(range); 
  }

  public void clear(Range range) {
    if(inRange(range)) {
      for (int i = range.getStartRow(); i <= range.getEndRow() + 1; i++) {     
        for (int j = range.getStartColumn(); j <= range.getEndColumn() + 1 ; j++) {         
          _cells[i][j].setContent(); 
        }
      }
    }
  }

  public void paste(Range range) {
    if(inRange(range)) {
      for (int i = range.getStartRow(); i <= range.getEndRow() + 1; i++) {     
        for (int j = range.getStartColumn(); j <= range.getEndColumn() + 1 ; j++) {         
          for (Cell cell : _cutBuffer.getCells()) {
            _cells[i][j].setContent(cell.value()); 
          }
        }
      }
    }
  }

  public void cut(Range range) {
    paste(range);
    clear(range);
  }
  
  boolean addUser(User user) {
    if (_users.contains(user)) {
      return false;
    }
    _users.add(user);
    return true;
  }

  public boolean inRange(Range range) {
    return range.getStartRow() <= _rows && range.getEndRow() <= _rows && range.getStartColumn() <= _columns && range.getEndColumn() <= _columns;
  }
  Literal value(int row, int column) throws UnrecognizedEntryException {
    if(isCell(row, column)) {
      return _cells[row][column].value();
        }
        throw new UnrecognizedEntryException("Cell does not exist");
    
  }

  public Cell getCell(int row, int column) {
    return _cells[row][column];
  }
  
  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, Content contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    //FIXME implement method
    if(isCell(row, column)) {
      _cells[row][column].setContent(contentSpecification);
      _changed = true;
    }
  }    

  public void getRange(Range range) {
    if(inRange(range)) {
      for (int i = range.getStartRow(); i <= range.getEndRow() + 1; i++) {     
        for (int j = range.getStartColumn(); j <= range.getEndColumn() + 1 ; j++) {         
          range.addCell(_cells[i][j]);
        }
      }
    }
  }


  public void insert(int parseInt, int parseInt2, Content content) {
    
  }

Range createRange(String range) throws UnrecognizedEntryException {
  String[] rangeCoordinates;
  int firstRow, firstColumn, lastRow, lastColumn;
  
  if (range.indexOf(':') != -1) {
    rangeCoordinates = range.split("[:;]");
    firstRow = Integer.parseInt(rangeCoordinates[0]);
    firstColumn = Integer.parseInt(rangeCoordinates[1]);
    lastRow = Integer.parseInt(rangeCoordinates[2]);
    lastColumn = Integer.parseInt(rangeCoordinates[3]);
  } else {
    rangeCoordinates = range.split(";");
    firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
    firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
  }

  // check if coordinates are valid
  // if yes
  return new Range(firstRow, firstColumn, lastRow, lastColumn);
}

}
