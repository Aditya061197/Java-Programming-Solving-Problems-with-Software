/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (15-07-2020)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Weather {
  public CSVRecord coldestHourInFile (CSVParser parser) {
    CSVRecord coldestRecord = null;
    for (CSVRecord record : parser) {
      if (coldestRecord == null) {
        coldestRecord = record;
      }
      else {
        double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
        double Temp = Double.parseDouble(record.get("TemperatureF"));
        if ((Temp != -9999 || coldestTemp != -9999) && coldestTemp > Temp) {
          coldestRecord = record;
        }
      }
    }
    return coldestRecord;
  }
    
  public void testColdestHourInFile () {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord record = coldestHourInFile(parser);
    System.out.println("Coldest Temperature was " + record.get("TemperatureF") + " at " +
                       record.get("TimeEDT"));
  }
    
  public String fileWithColdestTemperature () {
    DirectoryResource dr = new DirectoryResource();
    File coldestFile = null;
    CSVRecord record = null;
        
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      CSVParser parser = fr.getCSVParser();
      CSVRecord coldest = coldestHourInFile(parser);
      if (record == null) {
        record = coldest;
        coldestFile = f;
      }
      else {
        double Temp = Double.parseDouble(coldest.get("TemperatureF"));
        double coldestTemp = Double.parseDouble(record.get("TemperatureF"));
        if ((Temp != -9999 || coldestTemp != -9999) && Temp < coldestTemp) {
          coldestTemp = Temp;
          coldestFile = f;
        }
      }
    }
    return coldestFile.getAbsolutePath();
  }
    
  public void testFileWithColdestTemperature () {
    String filename = fileWithColdestTemperature();
    System.out.println("Coldest day was in file " + filename.substring(filename.indexOf("weather"), filename.length()));
    FileResource fr = new FileResource(filename);
    CSVParser parser = fr.getCSVParser();
    CSVRecord record = coldestHourInFile(parser);
    System.out.println("Coldest Temperature that day was " + record.get("TemperatureF") + " at " +
                        record.get("TimeEST"));
    parser = fr.getCSVParser();
    System.out.println("All the temperatures on the coldest day were:");
    for (CSVRecord coldestRecord : parser) {
      System.out.println(coldestRecord.get("DateUTC") + " " + coldestRecord.get("TimeEST")
                         + " " + coldestRecord.get("TemperatureF"));
    }
  }
    
  public CSVRecord lowestHumidityInFile (CSVParser parser) {
    CSVRecord lowestHumidity = null;
    for (CSVRecord record : parser) {
      if (lowestHumidity == null) {
        lowestHumidity = record;
      }
      else {
        int lowest = Integer.parseInt(lowestHumidity.get("Humidity"));
        int current = Integer.parseInt(record.get("Humidity"));
        if (lowest > current) {
          lowestHumidity = record;
        }
      }
    }
    return lowestHumidity;
  }
    
  public void testLowestHumidityInFile () {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord lowestHumidity = lowestHumidityInFile(parser);
    System.out.println("Lowest humidity was " + lowestHumidity.get("Humidity") + " at "
                        + lowestHumidity.get("DateUTC"));
  }
    
  public CSVRecord lowestHumidityInManyFiles () {
    DirectoryResource dr = new DirectoryResource();
    CSVRecord lowestHumidityFile = null;
    
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      CSVParser parser = fr.getCSVParser();
      for (CSVRecord record : parser) {
        if (lowestHumidityFile == null) {
          lowestHumidityFile = record;
        }
        else {
          int lowest = Integer.parseInt(lowestHumidityFile.get("Humidity"));
          int current = Integer.parseInt(record.get("Humidity"));
          if (current < lowest) {
            lowestHumidityFile = record;
          }
        }
      }
    }
    return lowestHumidityFile;
  }
    
  public void testLowestHumidityInManyFiles () {
    CSVRecord record = lowestHumidityInManyFiles();
    System.out.println("Lowest humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
  }
    
  public double averageTemperatureInFile (CSVParser parser) {
    double total = 0.0;
    for (CSVRecord record : parser) {
      total += Double.parseDouble(record.get("TemperatureF"));
    }
    return total / 24;
  }
    
  public void testAverageTemperatureInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
  }
    
  public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
    double total = 0.0;
    int number = 0;
    for (CSVRecord record : parser) {
      if (Integer.parseInt(record.get("Humidity")) >= value) {
        total += Double.parseDouble(record.get("TemperatureF"));
        number += 1;
      }
    }    
    return total / number;
  }
    
  public void testAverageTemperatureWithHighHumidityInFile () {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    int value = 80;
    double average = averageTemperatureWithHighHumidityInFile(parser, value);
    if (average == 0) {
      System.out.println("No temperatures with that humidity");
    }
    else {
      System.out.println("Average temperature when high humidity is " + average);
    }
  }
    
  public static void main (String[] args) {
    Weather w = new Weather();
    w.testColdestHourInFile();
    w.testFileWithColdestTemperature();
    w.testLowestHumidityInFile();
    w.testLowestHumidityInManyFiles();
    w.testAverageTemperatureInFile();
    w.testAverageTemperatureWithHighHumidityInFile();
  }
}
