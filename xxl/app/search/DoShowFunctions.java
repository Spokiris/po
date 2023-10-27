package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
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
    String output = _receiver.showFunctions(function);
    _display.addLine(output);
    _display.display(); 
  }
}
