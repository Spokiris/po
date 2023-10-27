package xxl.app.main;

import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
    addStringField("filename", Message.openFile());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String filename = stringField("filename");
      try {
        _receiver.load(filename);
      }
      catch (FileNotFoundException e) {
        throw new FileOpenFailedException(e);
      }
      catch (ClassNotFoundException e) {
        throw new FileOpenFailedException(e);
      }
      catch (IOException e) {
        throw new FileOpenFailedException(e);
      }
  }
}
