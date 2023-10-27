package xxl.app.edit;


import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.*;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.app.exception.InvalidCellRangeException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

  DoCopy(Spreadsheet receiver) {
    super(Label.COPY, receiver);
    addStringField("range",Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String range_specification = stringField("range");
    try{
        Range range = _receiver.createRange(range_specification);
        _receiver.copy(range);
    } catch (UnrecognizedEntryException e) {
      throw new InvalidCellRangeException(range_specification);
    }
  }
}