package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;

import xxl.core.exception.UnrecognizedEntryException;
import xxl.app.exception.InvalidCellRangeException;
// FIXME import classes

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

  DoPaste(Spreadsheet receiver) {
    super(Label.PASTE, receiver);
    addStringField("range",Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String range_specification = stringField("range");
    try{
    _receiver.paste(range_specification);
    } catch (UnrecognizedEntryException e) {
      throw new InvalidCellRangeException(range_specification);
    }
  }
}