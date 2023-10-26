package xxl.app.edit;


import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.*;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

  DoCopy(Spreadsheet receiver) {
    super(Label.COPY, receiver);
    addStringField("range","Insira a gama a copiar: ");
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
    Range range = _receiver.createRange(stringField("range"));
    _receiver.copy(range);
    } catch (UnrecognizedEntryException e){
      throw new CommandException(e.getMessage()){};
    }
  }
}
