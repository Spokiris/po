package xxl.app.edit;

import xxl.core.Spreadsheet;
import xxl.core.Cell;
import xxl.core.Range;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.*;
import xxl.core.exception.*;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
    addStringField("range",Message.address());
    
  }
  
  @Override
  protected final void execute() throws CommandException {
    String rangeDescription = stringField("range");
    try {
      Range range = _receiver.createRange(rangeDescription);
      for (Cell cell : range.getCells()) {
        _display.addLine(cell.toString()+"\n");
      }
    } catch (ArrayIndexOutOfBoundsException | UnrecognizedEntryException e){
      throw new InvalidCellRangeException(rangeDescription);
    }
  }
}
