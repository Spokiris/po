package xxl.app.main;

import java.io.IOException;

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
  }
  
  @Override
  protected final void execute() throws CommandException{
      try{
        if(_receiver.getFilename() == null){
            addStringField("filename",Message.newSaveAs());
        } else {
            addStringField("filename",Message.saveAs());
        }
        _receiver.saveAs(stringField("filename"));
      } catch (MissingFileAssociationException | IOException e){
        e.printStackTrace();
      }
  }
}