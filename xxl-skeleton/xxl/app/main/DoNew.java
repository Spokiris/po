package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.Calculator;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("rows", "linhas=");
    addIntegerField("columns", "colunas=");
    
  }
  
  @Override
  protected final void execute(){
    Integer rows = integerField("rows");
    Integer columns = integerField("columns");
    Spreadsheet sheet = new Spreadsheet(rows, columns);
    _receiver.setSpreadsheet(sheet);
  }
}
