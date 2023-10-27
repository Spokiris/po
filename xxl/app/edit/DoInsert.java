package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.InvalidCellRangeException;
import xxl.core.Spreadsheet;
import xxl.core.Range;
import xxl.core.Cell;
// FIXME import classes
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

  DoInsert(Spreadsheet receiver) {
    super(Label.INSERT, receiver);
    addStringField("range",Message.address());
    addStringField("content",Message.contents());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String range_specification = stringField("range");
    String content_specification = stringField("content");
  try{
    Range range = createRange(range_specification);
    for (Cell cell : range.getCells()){
      insertContent(cell.row(),cell.column(),content_specification); 
    }
  } catch (UnrecognizedEntryException e) {
    throw new InvalidCellRangeException(range_specification);
  }
  }
}
