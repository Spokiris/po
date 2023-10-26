package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.core.Calculator;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("columns", "Introduza o número de colunas: ");
    addIntegerField("rows", "Introduza o número de linhas: ");
  }
  
  @Override
  protected final void execute() throws CommandException {
    Integer rows = integerField("rows");
    Integer columns = integerField("columns");
    Spreadsheet sheet = new Spreadsheet(rows, columns);
    _receiver.setSpreadsheet(sheet);
  }
}
