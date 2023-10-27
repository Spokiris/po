package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.core.Spreadsheet;

import xxl.core.exception.UnrecognizedEntryException;
import xxl.app.exception.InvalidCellRangeException;

import xxl.core.Cell;
import xxl.core.Range;
/**
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

  DoDelete(Spreadsheet receiver) {
    super(Label.DELETE, receiver);
    addStringField("range",Message.address());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String range_specification = stringField("range");
    try{
      Range range = _receiver.createRange(range_specification);
      for (Cell cell : range.getCells()){
        _receiver.insertContent(cell.row(),cell.column(),null); 
      }
    } catch (UnrecognizedEntryException e) {
      throw new InvalidCellRangeException(range_specification);
    }
  }
}
