package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.Calculator;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addStringField("save", Message.saveBeforeExit());
    addIntegerField("rows", Message.lines());
    addIntegerField("columns", Message.columns());
  }
  
  @Override
  protected final void execute() throws CommandException{
    if (_receiver.getSpreadsheet() != null && _receiver.getSpreadsheet().isChanged()){
      if(stringField("save") == "n"){
        new DoSave(_receiver).execute();
      }
      }
      Integer rows = integerField("rows");
      Integer columns = integerField("columns");
      Spreadsheet sheet = new Spreadsheet(rows, columns);
      _receiver.setSpreadsheet(sheet);
  }
}
