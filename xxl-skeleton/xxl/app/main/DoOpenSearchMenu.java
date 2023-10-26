package xxl.app.main;

import xxl.core.Calculator;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open menu.
 */
class DoOpenSearchMenu extends Command<Calculator> {
  DoOpenSearchMenu(Calculator receiver) {
    super(Label.MENU_SEARCH, receiver, r -> r.getSpreadsheet() != null);
  }
  
  @Override
  protected final void execute() throws CommandException {
    (new xxl.app.search.Menu(_receiver.getSpreadsheet())).open();
  }
}
