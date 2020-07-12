/**
  * Write a description here.
  * 
  * @author (Aditya Kedia) 
  * @date (12-07-2020)
*/


public class Part1 {
  public String findSimpleGene (String dna) {
    int startIndex = dna.indexOf("ATG");
    int stopIndex = dna.indexOf("TAA", startIndex + 3);
    if (startIndex == -1 || stopIndex == -1 || (stopIndex - startIndex) % 3 != 0) {
      return "";
    }
    else {
      return dna.substring(startIndex, stopIndex + 3);
    }
  }
  
  public void testSimpleGene() {
    String dna1 = "ATCGATGATATACGAAGACTAA";
    System.out.println("The DNA string is: " + dna1);
    System.out.println("The gene found is:" + findSimpleGene(dna1));
    
    String dna2 = "ATCGATGATCATACAGACTAA";
    System.out.println("The DNA string is: " + dna2);
    System.out.println("The gene found is:" + findSimpleGene(dna2));
    
    String dna3 = "ATCGATATATAGCAGACTAA";
    System.out.println("The DNA string is: " + dna3);
    System.out.println("The gene found is:" + findSimpleGene(dna3));
        
    String dna4 = "ATCGATGATATACAGACTA";
    System.out.println("The DNA string is: " + dna4);
    System.out.println("The gene found is:" + findSimpleGene(dna4));
    
    String dna5 = "ATCGATATACAGACTA";
    System.out.println("The DNA string is: " + dna5);
    System.out.println("The gene found is:" + findSimpleGene(dna5));
  }
  
  public static void main (String[] args) {
    Part1 p = new Part1();
    p.testSimpleGene();
  }
