package xxl.core.exception;

/**
 * Represents an error occurred during the serialization/desserialization
 * process of the apllication's state:
 *  - The specified file does not exist
 *  - There is an error while processing this file using the serialization mechanism
 *    of Java.
 */
public class UnavailableFileException extends Exception {
  /** The requested filename. */
  private String _filename;
  
  /**
   * @param filename 
   */
  public UnavailableFileException(String filename) {
    super("Erro a processar ficheiro " + filename);
    _filename = filename;
  }
  
  /**
   * @return the requested filename
   */
  public String getFilename() {
    return _filename;
  } 
}
