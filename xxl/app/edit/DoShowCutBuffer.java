package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;

import xxl.core.Spreadsheet;
import xxl.core.CutBuffer;

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
  }
  
  @Override
  protected final void execute(){
    CutBuffer cutBuffer = _receiver.getCutBuffer();
    if (cutBuffer.paste() == null) {
      _display.addLine("Cut buffer is empty");
    }
    else{
      _display.addLine(cutBuffer);
    }
  }
}