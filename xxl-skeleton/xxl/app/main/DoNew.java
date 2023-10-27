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
    addIntegerField("rows", "Especifique o número de linhas da folha: ");
    addIntegerField("columns", "Especifique o número de colunas da folha: ");
    
  }
  
  @Override
  protected final void execute(){
    Integer rows = integerField("rows");
    Integer columns = integerField("columns");
    Spreadsheet sheet = new Spreadsheet(rows, columns);
    _receiver.setSpreadsheet(sheet);
  }
}
