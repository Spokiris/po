package xxl.core;

import java.io.Serializable;

import xxl.core.exception.UnrecognizedEntryException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    for (int i = 1; i <= _rows; i++) {
      for (int j = 1; j <= _columns; j++) {
        _cells[i-1][j-1] = new Cell(i,j);
      }
    }
    _cutBuffer = new CutBuffer();
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

  public void copy(String range) throws UnrecognizedEntryException {
    try{
      Range tempRange = createRange(range,this);
      _cutBuffer.copy(tempRange);
    }catch(UnrecognizedEntryException e){
      throw new UnrecognizedEntryException(e.getMessage());
    }
  }

  public void clear(String range) throws UnrecognizedEntryException{
    try{
      Range tempRange = createRange(range,this);
    if(inRange(tempRange)) {
      for (int i = tempRange.getStartRow(); i <= tempRange.getEndRow(); i++) {     
        for (int j = tempRange.getStartColumn(); j <= tempRange.getEndColumn() ; j++) {         
            _cells[i-1][j-1].setContent(); 
          }
        }
      }
    }catch(UnrecognizedEntryException e){
      throw new UnrecognizedEntryException(e.getMessage());
    }
  }

  public void paste(String range) throws UnrecognizedEntryException {
    try{
      Range tempRange = createRange(range,this);
    if(inRange(tempRange)){
      for (int i = tempRange.getStartRow(); i <= tempRange.getEndRow(); i++) {     
        for (int j = tempRange.getStartColumn(); j <= tempRange.getEndColumn() ; j++) {         
          for (Cell cell : _cutBuffer.getBuffer()) {
              if (cell.row() == i && cell.column() == j){
                _cells[i-1][j-1].setContent(cell.content());
              }
            }
          }
        }
      }
    } catch (UnrecognizedEntryException e){
      throw new UnrecognizedEntryException(e.getMessage());
    }
  }

  public void cut(String range) throws UnrecognizedEntryException{
    try{
      copy(range);
      clear(range);
    }catch(UnrecognizedEntryException e){
      throw new UnrecognizedEntryException(e.getMessage());
    }
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
          Range range = createRange(range_specification,this);
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
              Content content =new Parser().parseContent(contentSpecification);
              _cells[row-1][column-1].setContent(content);
              _changed = true;
          }
      } catch(UnrecognizedEntryException e){
          throw new UnrecognizedEntryException(e.getMessage());
      }
    }

public Range createRange(String range,Spreadsheet spreadsheet) throws UnrecognizedEntryException {
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
    if(firstRow != lastRow && firstColumn != lastColumn){
        throw new UnrecognizedEntryException("");
    }
    return new Range(firstRow, lastRow, firstColumn, lastColumn, spreadsheet);
    }

    public String showFunctions(String function){
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for (int i = 1; i <= _rows; i++) {
            for (int j = 1; j <= _columns; j++) {
                if(_cells[i-1][j-1].content() != null){
                    if(_cells[i-1][j-1].content().toString().contains(function)){
                        cells.add(_cells[i-1][j-1]);
                    }
                }
            }
        }
        sortCellsByContent(cells);
        String result = "";
        for (Cell cell : cells) {
            result += cell.toString()+"\n";
        }
        return result;
    }

    public void sortCellsByContent(ArrayList<Cell> cells) {
    Collections.sort(cells, new Comparator<Cell>() {
        @Override
        public int compare(Cell c1, Cell c2) {
            String s1 = c1.content() != null ? c1.content().toString() : "";
            String s2 = c2.content() != null ? c2.content().toString() : "";
            return s1.compareTo(s2);
        }
    });
  }
    
}
