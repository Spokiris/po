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
  // FIXME define attributes
  private int _rows;
  private int _columns;
  private boolean _changed;
  private List<User> _users = new ArrayList<>();
  private CutBuffer _cutBuffer;
  private Cell[][] _cells = new Cell[_rows][_columns];
  
  // FIXME define contructor(s)
  public Spreadsheet(int rows, int columns) {
    _rows = rows;
    _columns = columns;
    _changed = false;
  }

  public boolean isCell(int row, int column) {
    return row <= _rows && column <= _columns && row > 0 && column > 0;
  }
  
  // FIXME define methods
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

  public void copy(String range) {
    _cutBuffer.copy(range); 
  }

  public void clear(String range) {
    Range r = new Range(range);
    if(inRange(range)) {
      for (int i = r.getStartRow(); i <= r.getEndRow() + 1; i++) {     
        for (int j = r.getStartColumn(); j <= r.getEndColumn() + 1 ; j++) {         
          _cells[i][j].setContent(); 
        }
      }
    }
  }

  public void paste(String range) {
    Range r = new Range(range);
    if(inRange(range)) {
      for (int i = r.getStartRow(); i <= r.getEndRow() + 1; i++) {     
        for (int j = r.getStartColumn(); j <= r.getEndColumn() + 1 ; j++) {         
          for (Cell cell : _cutBuffer.getCells()) {
            _cells[i][j].setContent(cell.value(cell.row(), cell.column())); 
          }
        }
      }
    }
  }

  public void cut(String range) {
    _cutBuffer.cut(range); //FIXME implement cut
  }
  
  boolean addUser(User user) {
    if (_users.contains(user)) {
      return false;
    }
    _users.add(user);
    return true;
  }

  public boolean inRange(String range) {
    Range r = new Range(range);
    return r.getStartRow() <= _rows && r.getEndRow() <= _rows && r.getStartColumn() <= _columns && r.getEndColumn() <= _columns;
  }

  Literal value(int row, int column) throws UnrecognizedEntryException {
    if(isCell(row, column)) {
      return _cells[row][column].value(row, column);
        }
        throw new UnrecognizedEntryException("Cell does not exist");
    
  }
  
  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    //FIXME implement method
    if(isCell(row, column)) {
      _cells[row][column].setContent(contentSpecification);
      _changed = true;
    }
  }
  
  public void getRange(String range) {
    Range r = new Range(range);
    
    if(inRange(range)) {
      for (int i = r.getStartRow(); i <= r.getEndRow() + 1; i++) {     
        for (int j = r.getStartColumn(); j <= r.getEndColumn() + 1 ; j++) {         
          r.addCell(_cells[i][j]);
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
  return new Range with firstRow, firstColumn, lastRow, lastColumn, spreadsheet;
}

}
