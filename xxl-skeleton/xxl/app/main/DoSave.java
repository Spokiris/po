package xxl.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import xxl.core.Calculator;
import xxl.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.menus.Command;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    addStringField("filename","Introduza o nome do ficheiro:");
  }
  
  @Override
  protected final void execute() {
    if (_receiver.getFilename() == null) {
      String filename = stringField("filename");
      try{
      _receiver.saveAs(filename);
      }catch (FileNotFoundException e){
      }catch (IOException e){
      }catch (MissingFileAssociationException e){
      }
    }
  }
}