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
        _cells[i][j] = new Cell(i,j);
      }
    }
    _cutBuffer = new CutBuffer();
  }

  public boolean isCell(int row, int column)throws UnrecognizedEntryException{
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

  public void copy(Range range) throws UnrecognizedEntryException {
      _cutBuffer.copy(range);
  }

  public void clear(Range range) throws UnrecognizedEntryException{
    if (range != null){
      ArrayList<Cell> cells = range.getCells();
      for (Cell cell : cells){
        cell.setContent();
      }
    }else{
      throw new UnrecognizedEntryException("");
    }
  }

  public void paste(Range range) throws UnrecognizedEntryException {
    ArrayList<Cell> cells = _cutBuffer.paste();
    if (range != null) {
        if (range.getCells().size() == 1) {
            for (Cell cell : cells) {
                if (isCell(cell.row(), cell.column())) {
                  insertContent(cell.row(), cell.column(), cell.content());
                }
            }
      } else if (range.getCells().size() == cells.size()) {
        int i = 0;
        for (Cell cell : range.getCells()) {
          insertContent(cell.row(), cell.column(), cells.get(i).content());
          i++;
        }
      } else {
        throw new UnrecognizedEntryException("");
      }
    }
  }


  public void cut(Range range) throws UnrecognizedEntryException{
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
      return _cells[row][column].value();
    }
    throw new UnrecognizedEntryException("");
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
  public void insertContent(int row, int column, Content content) throws UnrecognizedEntryException {
      try{
          if(isCell(row, column)) {
              _cells[row][column].setContent(content);
              _changed = true;
          }
      } catch(UnrecognizedEntryException e){
          throw new UnrecognizedEntryException(e.getMessage());
      }
    }

  public void insert(int row, int column, String contentSpecification) throws UnrecognizedEntryException{
    try{
      Content content = new Parser().parseContent(contentSpecification);
      insertContent(row, column, content);
    } catch(UnrecognizedEntryException e){
      throw new UnrecognizedEntryException("");
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
    if(firstRow != lastRow && firstColumn != lastColumn){
        throw new UnrecognizedEntryException("");
    }
    return new Range(firstRow, lastRow, firstColumn, lastColumn, this);
    }

    public String showFunctions(String function){
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for (int i = 1; i <= _rows; i++) {
            for (int j = 1; j <= _columns; j++) {
                if(_cells[i][j].content() != null){
                    if(_cells[i][j].content().toString().contains(function)){
                        cells.add(_cells[i][j]);
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
              String s1 = c1.content().value() != null ? c1.content().value().toString() : "";
              String s2 = c2.content().value() != null ? c2.content().value().toString() : "";
              int valueComparison = s1.compareTo(s2);
              if (valueComparison != 0) {
                  return valueComparison;
              } else {
                  int rowComparison = Integer.compare(c1.row(), c2.row());
                  if (rowComparison != 0) {
                      return rowComparison;
                  } else {
                      return Integer.compare(c1.column(), c2.column());
                  }
              }
          }
      });
  } 
}
