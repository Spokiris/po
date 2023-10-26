package xxl.core;

import java.util.HashSet;

public class User {
    private String _name;
    private HashSet<Spreadsheet> _spreadsheets;

    //Constructor 
    public User(String name){
        _name = name;
        _spreadsheets = new HashSet<Spreadsheet>();
    }
    //Getters
    public String getName(){
        return _name;
    }

    public int hashCode(){
        return _name.hashCode();
    }
    
    //Methods
    public boolean equals(Object o){
        return o.hashCode() == this.hashCode();
    }
    
    //ADD SHEET TO CURRENT USER SHEETS
    void addSheet(Spreadsheet spreadsheet){
        _spreadsheets.add(spreadsheet);
    }
}
