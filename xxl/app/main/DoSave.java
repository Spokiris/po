package xxl.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
  }
  
  @Override
  protected final void execute() throws CommandException{
      try{
          _receiver.save();
          } catch (FileNotFoundException e1) {
              try{
                  String filename = Form.requestString(Message.newSaveAs());
                  _receiver.saveAs(filename);
              } catch (IOException | MissingFileAssociationException e){
                  throw new FileOpenFailedException(e);
              }
      } catch (IOException e1) {
          throw new FileOpenFailedException(e1);
      }
  }
}