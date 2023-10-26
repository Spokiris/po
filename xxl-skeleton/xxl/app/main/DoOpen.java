package xxl.app.main;

import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.ImportFileException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("filename", "Introduza o nome do ficheiro:");
  }
  
  @Override
  protected final void execute() throws CommandException {
      try {
        String filename = stringField("filename");
        _receiver.importFile(filename);
      } catch (ImportFileException e) {
        throw new FileOpenFailedException(e);
        }
  }
}
