package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.Cell;
import xxl.core.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.lang.*;

// FIXME import classes

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

  DoShowFunctions(Spreadsheet receiver) {
    super(Label.SEARCH_FUNCTIONS, receiver);
    addStringField("function",Message.searchFunction());
  }

  @Override
  protected final void execute() {
    String function = stringField("function");
    List<Cell> _cellsToShow = new ArrayList<>(_receiver.searchFunction(function));
    _cellsToShow.sort(new ComparatorCells());
    for (Cell cell: _cellsToShow) {
      _display.addLine(cell);
    }
    _display.display();
  }
}

class ComparatorCells implements Comparator<Cell>{
  public int compare(Cell c1, Cell c2){
    try {
      Function f1 = (Function) c1.content();
      Function f2 = (Function) c2.content();
      if(f1.name().equals(f2.name()) && c1.row() == c2.row()){
          return c1.column() - c2.column();
    }}catch (ClassCastException e) {
      continue;
    }
    return 0;
  }
}
