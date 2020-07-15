/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (15-07-2020)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ExportData {
  public String countryInfo (CSVParser parser, String country) {
    String info = "";
    for (CSVRecord record : parser) {
      String countryName = record.get("Country");
      if (countryName.contains(country)) {
        info = (country + ": " + record.get("Exports")
                + ": " + record.get("Value (dollars)"));
        return info;
      }
    }
    return "NOT FOUND";     
  }
    
  public void tester() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    System.out.println(countryInfo(parser, "Nauru"));
    parser = fr.getCSVParser();
    listExportersTwoProducts(parser, "cotton", "flowers");
    parser = fr.getCSVParser();
    System.out.println(numberOfExporters(parser, "cocoa"));
    parser = fr.getCSVParser();
    bigExporters(parser, "$999,999,999,999");
  }
    
  public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
    for (CSVRecord record : parser) {
      String exportItems = record.get("Exports");
      if ((exportItems.contains(exportItem1)) && (exportItems.contains(exportItem2))) {
        System.out.println(record.get("Country"));
      }
    }
  }
    
  public int numberOfExporters (CSVParser parser, String exportItem) {
    int count = 0;
    for (CSVRecord record : parser) {
      String exportItems = record.get("Exports");
      if (exportItems.contains(exportItem)) {
        count += 1;
      }
    }
    return count;
  }
    
  public void bigExporters (CSVParser parser, String amount) {
    for (CSVRecord record : parser) {
      String value = record.get("Value (dollars)");
      if (value.length() > amount.length()) {
        System.out.println(record.get("Country") + " " + value);
      }
    }
  }
    
  public static void main (String[] args) {
    ExportData ed = new ExportData();
    ed.tester();
  }
}
