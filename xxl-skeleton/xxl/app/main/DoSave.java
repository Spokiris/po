package xxl.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import xxl.app.exception.FileOpenFailedException;
import xxl.app.exception.UnknownFunctionException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    addStringField("filename",Message.openFile());
  }
  
  @Override
  protected final void execute() throws CommandException{
      String filename = stringField("filename");
      try{
        _receiver.save();
        }catch (FileNotFoundException e){
        try{
          _receiver.saveAs(filename);
        }catch (IOException e1){
          throw new FileOpenFailedException(e1);
        } catch (MissingFileAssociationException e1) {
          throw new FileOpenFailedException(e1);
        }
      } catch (IOException e) {
        throw new FileOpenFailedException(e);
      }
  }
}