/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (12-07-2020)
 */

public class Part2 {
  public String findSimpleGene (String dna, String startCodon, String stopCodon) {
    if (Character.isUpperCase(dna.charAt(0))) {
      startCodon = startCodon.toUpperCase();
      stopCodon = stopCodon.toUpperCase();
    }
    else {
      startCodon = startCodon.toLowerCase();
      stopCodon = stopCodon.toLowerCase();
    }
    
    int startIndex = dna.indexOf(startCodon);
    int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
    if (startIndex == -1 || stopIndex == -1 || (stopIndex - startIndex) % 3 != 0) {
      return "";
    }
    else {
      return dna.substring(startIndex, stopIndex + 3);
    }
  }
  
  public void testSimpleGene() {
    String dna1 = "atcgatgatcatacagacataa";
    System.out.println("The DNA string is: " + dna1);
    System.out.println("The gene found is:" + findSimpleGene(dna1, "ATG", "TAA"));
    
    String dna2 = "atcgatgatcatacagactaa";
    System.out.println("The DNA string is: " + dna2);
    System.out.println("The gene found is:" + findSimpleGene(dna2, "ATG", "TAA"));
    
    String dna3 = "ATCGATATATAGCAGACTAA";
    System.out.println("The DNA string is: " + dna3);
    System.out.println("The gene found is:" + findSimpleGene(dna3, "atg", "taa"));
    
    String dna4 = "ATCGATGATATACAGACTA";
    System.out.println("The DNA string is: " + dna4);
    System.out.println("The gene found is:" + findSimpleGene(dna4, "ATG","TAA"));
    
    String dna5 = "ATCGATATACAGACTA";
    System.out.println("The DNA string is: " + dna5);
    System.out.println("The gene found is:" + findSimpleGene(dna5, "ATG", "TAA"));
  }
  
  public static void main (String[] args) {
    Part2 p = new Part2();
    p.testSimpleGene();
  }
}
