package xxl.core;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

import xxl.core.exception.UnrecognizedEntryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
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
    _cutBuffer.clear(range); //FIXME implement clear
  }
  
  boolean addUser(User user) {
    if (_users.contains(user)) {
      return false;
    }
    _users.add(user);
    return true;
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
}
