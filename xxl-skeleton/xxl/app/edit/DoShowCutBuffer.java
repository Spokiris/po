package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import xxl.core.*;
// FIXME import classes

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

  DoShowCutBuffer(Spreadsheet receiver) {
    super(Label.SHOW_CUT_BUFFER, receiver);
  }
  
  @Override
  protected final void execute(){
    Range range = _receiver.getCutBuffer().getRange();
    if (range == null) {
      _display.popup("Cut buffer is empty");
      return;
    }
    _display.popup(range.toString());
  }
}