package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import xxl.core.exception.UnrecognizedEntryException;

class Parser {

  private Spreadsheet _spreadsheet;
  
  Parser(){
  }

  Parser(Spreadsheet spreadsheet) {
    _spreadsheet = spreadsheet;
  }

  Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException /* More Exceptions? */ {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      parseDimensions(reader);

      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
    return _spreadsheet;
  }

  private void parseDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException {
    int rows = -1;
    int columns = -1;
    
    for (int i = 0; i < 2; i++) {
      String[] dimension = reader.readLine().split("=");
      if (dimension[0].equals("linhas")){
        rows = Integer.parseInt(dimension[1]);
      }else if (dimension[0].equals("colunas")){
        columns = Integer.parseInt(dimension[1]);
      }else{
        throw new UnrecognizedEntryException("Dimenções inválidas para a folha");
      }
    }

    if (rows <= 0 || columns <= 0)
      throw new UnrecognizedEntryException("Dimenções inválidas para a folha");

    _spreadsheet = new Spreadsheet(rows, columns);
  }

  private void parseLine(String line) throws UnrecognizedEntryException /*, more exceptions? */{
    String[] components = line.split("\\|");

    if (components.length == 1) // do nothing
      return;
    
    if (components.length == 2) {
      String[] address = components[0].split(";");
      _spreadsheet.insertContent(Integer.parseInt(address[0]), Integer.parseInt(address[1]), components[1]);
    } else
      throw new UnrecognizedEntryException("Wrong format in line: " + line);
  }

  // parse the begining of an expression
  Content parseContent(String contentSpecification) throws UnrecognizedEntryException {
    char c = contentSpecification.charAt(0);
    if (c == '=')
      return parseContentExpression(contentSpecification.substring(1));
    else
      return parseLiteral(contentSpecification);
  }

  private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
    if (literalExpression.charAt(0) == '\'') {
      String value = literalExpression;
      return new LiteralString(value);
    } else {
      try {
        int val = Integer.parseInt(literalExpression);
        return new LiteralInteger(val);
      } catch (NumberFormatException nfe) {
        throw new UnrecognizedEntryException("Número inválido: " + literalExpression);
      }
    }
  }

  // contentSpecification is what comes after '='
  private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException /*, more exceptions */ {
    if (contentSpecification.contains("("))
      return parseFunction(contentSpecification);
    // It is a reference
    String[] address = contentSpecification.split(";");
    return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]),_spreadsheet);
  }

  private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException /*, more exceptions */ {
    String[] components = functionSpecification.split("[()]");
    if (components[1].contains(","))
    return parseBinaryFunction(components[0], components[1]);   
    return parseIntervalFunction(components[0], components[1]);
  }

  private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException /* , more Exceptions */ {
    String[] arguments = args.split(",");
    Content arg0 = parseArgumentExpression(arguments[0]);
    Content arg1 = parseArgumentExpression(arguments[1]);
    
    switch (functionName) {
      case "ADD":
        return new Add(arg0, arg1);
      case "SUB":
        return new Sub(arg0, arg1);
      case "MUL":
        return new Mul(arg0, arg1);
      case "DIV":
        return new Div(arg0, arg1);
      default:
        throw new UnrecognizedEntryException("Função inválida: " + functionName);
    }
  }

  private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
    if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
      String[] address = argExpression.split(";");
      return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]),_spreadsheet);
      // pode ser diferente do anterior em parseContentExpression
    } else
      return parseLiteral(argExpression);
  }

  private Content parseIntervalFunction(String functionName, String rangeDescription)
    throws UnrecognizedEntryException{
    Range range = _spreadsheet.createRange(rangeDescription);
    switch (functionName) {
      case "CONCAT":
        return new Concat(range);
      case "COASLECE":
        return new Coalesce(range);
      case "PRODUCT":
        return new Product(range);
      case "AVERAGE":
        return new Average(range);
      default:
        throw new UnrecognizedEntryException("Função inválida: " + functionName);
    }
  }
}