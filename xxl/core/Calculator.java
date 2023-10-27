package xxl.core;

import java.util.HashSet;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnrecognizedEntryException;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator{
  /** The current spreadsheet. */
  private Spreadsheet _spreadsheet;
  private User _activeUser;
  private HashSet<User> _users;
  private String _filename;
  
  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be null.
   */
  
  public String getFilename() {
    return _filename;
  }

  public void setSpreadsheet(Spreadsheet spreadsheet) {
    _spreadsheet = spreadsheet;
  }

  public Spreadsheet getSpreadsheet(){
      return _spreadsheet;
  }

  public boolean createUser(String username) {
    _activeUser = new User(username);
    return _users.add(_activeUser);
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void save(String file) throws IOException {
    ObjectOutputStream obOut = null;
    try {
    obOut = new ObjectOutputStream(new FileOutputStream(file));
    obOut.writeObject(_spreadsheet);
    } finally {
    if (obOut != null)
    obOut.close();
    }
    }

  public Spreadsheet load(String inputFilename) throws IOException, ClassNotFoundException {
    ObjectInputStream objIn = null;
    try {
      objIn = new ObjectInputStream(new FileInputStream(inputFilename));
      Spreadsheet anObject = (Spreadsheet) objIn.readObject();
      return anObject;
    } finally {
      if (objIn != null)
        objIn.close();
    }
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _filename = filename;
    save(filename);
  }
    
  
  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException {
    try {
        Parser parser = new Parser(_spreadsheet);
        _spreadsheet = parser.parseFile(filename);
        return;
    } 
    catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  } 
}
