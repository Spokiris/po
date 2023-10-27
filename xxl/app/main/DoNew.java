package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Spreadsheet;
import xxl.core.Calculator;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
    addIntegerField("rows", Message.lines());
    addIntegerField("columns", Message.columns());
  
  }
  
  @Override
  protected final void execute() throws CommandException{
    if (_receiver.getSpreadsheet() != null && !_receiver.getSpreadsheet().isChanged()){
      if (!Form.confirm(Message.saveBeforeExit())){
        return;
      }
    }
    Integer rows = integerField("rows");
    Integer columns = integerField("columns");
    Spreadsheet sheet = new Spreadsheet(rows, columns);
    _receiver.setSpreadsheet(sheet);
  }
}
