/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (15-07-2020)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
  public void totalBirths (FileResource fr) {
    int totalBirths = 0;
    int boys = 0;
    int girls = 0;
    for (CSVRecord record : fr.getCSVParser(false)) {
      int numBorn = Integer.parseInt(record.get(2));
      totalBirths += numBorn;
      if (record.get(1).equals("F")) {
        girls += 1;
      }
      else {
        boys += 1;
      }
    }
    System.out.println("Totl Births: " + totalBirths);
    System.out.println("Total names: " + (boys + girls));
    System.out.println("Number of boy names: " + boys);
    System.out.println("Number of girl names: " + girls);
  }
    
  public void test() {
    FileResource fr = new FileResource();
    totalBirths(fr);
    System.out.println(getRank(2014, "Leonel", "M"));
    System.out.println(getName(2014, getRank(1974, "Owen", "M"), "M"));
    whatIsNameInYear("Owens", 1974, 2014, "M");
    System.out.println(yearOfHighestRank("Mich", "M"));
    System.out.println(getAverageRank("Robert", "M"));
    System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
  }
    
  public int getRank (int year, String name, String gender) {
    int rank = 0;
    FileResource fr = new FileResource("C:\\Users\\acer\\Desktop\\Programming\\Java\\Java Programming - Solving Problems with Software\\BabyNames\\us_babynames_by_year\\yob" + year + ".csv");
    CSVParser parser = fr.getCSVParser(false);
    for (CSVRecord record : parser) {
      if (record.get(1).equals(gender)) {
        rank += 1;
        if (record.get(0).equals(name)) {
          return rank;
        }
      }
    }
    return -1;
  }
    
  public String getName (int year, int rank, String gender) {
    FileResource fr = new FileResource("C:\\Users\\acer\\Desktop\\Programming\\Java\\Java Programming - Solving Problems with Software\\BabyNames\\us_babynames_by_year\\yob" + year + ".csv");
    CSVParser parser = fr.getCSVParser(false);
    int currentRank = 0;
    for (CSVRecord record : parser) {
      if (record.get(1).equals(gender)) {
        currentRank += 1;
        if (rank == currentRank) {
          return record.get(0);
        }
      }
    }
    return "NO NAME";
  }
    
  public void whatIsNameInYear (String name, int year, int newYear, String gender) {
    int rank = getRank(year, name, gender);
    if (rank != -1) {
      String newName = getName(newYear, rank, gender);
      System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
  }
    
  public int yearOfHighestRank (String name, String gender) {
    DirectoryResource dr = new DirectoryResource();
    int year = -1;
    int rank = 0;
    for (File f : dr.selectedFiles()) {
      String filename = f.getName();
      int currentYear = Integer.parseInt(filename.substring(filename.indexOf("yob") + 3, filename.indexOf("yob") + 7));
      int currentRank = getRank(currentYear, name, gender);
      if (currentRank == -1) {
        continue;
      }
      if (rank == 0) {
        rank = currentRank;
        year = currentYear;
      }
      else {
        if (rank > currentRank) {
          rank = currentRank;
          year = currentYear;
        }
      }
    }
    return year;
  }
    
  public double getAverageRank (String name, String gender) {
    DirectoryResource dr = new DirectoryResource();
    int rankTotal = 0;
    int occurences = 0;
    for (File f : dr.selectedFiles()) {
      String filename = f.getName();
      int currentYear = Integer.parseInt(filename.substring(filename.indexOf("yob") + 3, filename.indexOf("yob") + 7));
      int rank = getRank(currentYear, name, gender);
      if (rank != -1) {
        rankTotal += rank;
        occurences += 1;
      }
    }
    if (occurences == 0) {
      return -1.0;
    }
    else {
      return ((double)rankTotal) / occurences;
    }
  }
    
  public int getTotalBirthsRankedHigher (int year, String name, String gender) {
    FileResource fr = new FileResource("C:\\Users\\acer\\Desktop\\Programming\\Java\\Java Programming - Solving Problems with Software\\BabyNames\\us_babynames_by_year\\yob" + year + ".csv");
    CSVParser parser = fr.getCSVParser(false);
    int rank = getRank(year, name, gender);
    int total = 0;
    for (CSVRecord record : parser) {
      if (record.get(1).equals(gender)) {
        int currentRank = getRank(year, record.get(0), gender);
        if (currentRank < rank) {
          total += Integer.parseInt(record.get(2));
        }
        else {
          break;
        }
      }
    }
    return total;
  }
    
  public static void main (String[] args) {
    BabyNames b = new BabyNames();
    b.test();
  }
}
