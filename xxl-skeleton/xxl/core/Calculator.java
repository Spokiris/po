package xxl.core;

import java.util.HashSet;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnrecognizedEntryException;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator implements Serializable{
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

  public void createNewSpreadsheet(int rows, int columns) {
    _spreadsheet = new Spreadsheet(rows, columns);
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
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
      try{
        FileOutputStream fileOut = new FileOutputStream(_filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        Serializable object = _spreadsheet;
        out.writeObject(object);
        out.close();
        fileOut.close();
      }
      catch (FileNotFoundException e) {
        throw new FileNotFoundException(_filename);
      }
      catch (IOException e) {
        throw new IOException();
      }
  }

  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
    try{
      FileInputStream fileIn = new FileInputStream(filename);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Spreadsheet spreadsheet = (Spreadsheet) in.readObject();
      in.close();
      fileIn.close();
      setSpreadsheet(spreadsheet);
    }
    catch (FileNotFoundException e) {
      throw new FileNotFoundException(filename);
    }
    catch (IOException e) {
      throw new IOException();
    }
    catch (ClassNotFoundException e) {
      throw new ClassNotFoundException();
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
    try{
      _filename = filename;
      save();
    }
    catch (FileNotFoundException e) {
      throw new FileNotFoundException(filename);
    }
    catch (IOException e) {
      throw new IOException();
    }
  }
  
  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException {
    try {
        Parser parser = new Parser();
        Spreadsheet spreadsheet = parser.parseFile(filename);
        _spreadsheet = spreadsheet;
    } 
    catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  } 
}
