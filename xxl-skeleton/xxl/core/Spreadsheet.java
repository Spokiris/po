package xxl.core;

import java.io.Serializable;

import xxl.core.exception.UnrecognizedEntryException;

import java.util.ArrayList;
/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;
  private int _rows;
  private int _columns;
  private boolean _changed;
  private ArrayList<User> _users;
  private CutBuffer _cutBuffer;
  private Cell[][] _cells;
  
  public Spreadsheet(int rows, int columns){
    _rows = rows;
    _columns = columns;
    _changed = false;
    _users = new ArrayList<User>();
    _cells = new Cell[_rows][_columns];
    for (int i = 0; i < _rows; i++) {
      for (int j = 0; j < _columns; j++) {
        _cells[i][j] = new Cell(i+1,j+1);
      }
    }
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

  public CutBuffer getCutBuffer() {
    return _cutBuffer;
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
      for (int i = range.getStartRow()-1; i <= range.getEndRow()-1; i++) {     
        for (int j = range.getStartColumn()-1; j <= range.getEndColumn()-1 ; j++) {         
          for (Cell cell : _cutBuffer.getCells()) {
            _cells[i][j].setContent(cell.value());
          }
        }
      }
    }
  }

  public void cut(Range range) {
    copy(range);
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
      return _cells[row-1][column-1].value();
    }
    throw new UnrecognizedEntryException("");
  }

  public Cell getCell(int row, int column) {
    return _cells[row-1][column-1];
  }
  
  public void insert(String range_specification, String content_specification)throws UnrecognizedEntryException{
    try{
      Range range = createRange(range_specification);
      for (int i = range.getStartRow(); i <= range.getEndRow(); i++) {     
        for (int j = range.getStartColumn(); j <= range.getEndColumn(); j++) {         
          insertContent(i,j,content_specification);
        }
      }
      _changed = true;
    }catch(UnrecognizedEntryException e){
      throw new UnrecognizedEntryException(e.getMessage());
    }
  }
  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, String contentSpecification) throws UnrecognizedEntryException {
    try{
      if(isCell(row, column)) {
      Parser parser = new Parser();
      Content content = parser.parseContent(contentSpecification);
      _cells[row-1][column-1].setContent(content);
      _changed = true;
    }
    }catch(UnrecognizedEntryException e){
      throw new UnrecognizedEntryException(e.getMessage());
    }
  }    

  public void getRange(Range range) {
    if(inRange(range)) {
      for (int i = range.getStartRow()-1; i <= range.getEndRow()-1; i++) {     
        for (int j = range.getStartColumn()-1; j <= range.getEndColumn()-1; j++) {         
          range.addCell(_cells[i][j]);
        }
      }
    }
  }

public Range createRange(String range) throws UnrecognizedEntryException {
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
  if (!isCell(firstRow, firstColumn) || !isCell(lastRow, lastColumn)) {
    throw new UnrecognizedEntryException("");
  }
  return new Range(firstRow, firstColumn, lastRow, lastColumn);
}

}
