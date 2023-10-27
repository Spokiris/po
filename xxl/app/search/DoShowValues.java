package xxl.app.search;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.Cell;
import java.util.Comparator;
import java.util.ArrayList;
// FIXME import classes

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

  DoShowValues(Spreadsheet receiver) {
    super(Label.SEARCH_VALUES, receiver);
    addStringField("value",Message.searchValue());
  }
  
  @Override
  protected final void execute() {
    String value = stringField("value");
    List<Cell> _cellsToShow = new ArrayList<>(_receiver.searchValue(value));
    _cellsToShow.sort(new ComparatorValues());
    for (Cell cell: _cellsToShow) {
      _display.addLine(cell);
    }
    _display.display();
  }
}
class ComparatorValues implements Comparator<Cell>{
  public int compare(Cell c1, Cell c2){
      if(c1.row() == c2.row()){
          return c1.column() - c2.column();
    }
    return 0;
  }
}