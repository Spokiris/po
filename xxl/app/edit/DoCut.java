package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
// FIXME import classes
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.Range;
import xxl.core.Cell;

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

  DoCut(Spreadsheet receiver) {
    super(Label.CUT, receiver);
    addStringField("range",Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String range_specification = stringField("range");
    try{
      Range range = _receiver.createRange(range_specification);
      _receiver.cut(range);
    } catch (UnrecognizedEntryException e) {
      throw new InvalidCellRangeException(range_specification);
    }
  }
}
