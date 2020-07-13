/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (13-07-2020)
 */
 

public class Part1 {
  public int findStopCodon (String dna, int startIndex, String stopCodon) {
    int currIndex = dna.indexOf(stopCodon, startIndex);
      while (currIndex != -1) {
        if ((currIndex - startIndex) % 3 == 0) {
          return currIndex;
        }
        else {
          currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
      }
      return -1;
  }
    
  public void testFindStopCodon () {
    String dna = "xyzxyzxyzTAAxyzxyzTAGTAA";
    System.out.println(findStopCodon(dna, 0, "TAA"));
    System.out.println(findStopCodon(dna, 1, "TAA"));
    System.out.println(findStopCodon(dna, 0, "TAG"));
    System.out.println(findStopCodon(dna, 0, "TGA"));
  }
    
  public String findGene(String dna) {
    int startIndex = dna.indexOf("ATG");
    if (startIndex == -1) {
      return "";
    }
    int stopIndexTAA = findStopCodon(dna, startIndex + 3, "TAA");
    int stopIndexTGA = findStopCodon(dna, startIndex + 3, "TGA");
    int stopIndexTAG = findStopCodon(dna, startIndex + 3, "TAG");
    int minIndex = 0;
    if ((stopIndexTAA == -1) || (stopIndexTGA != -1 && stopIndexTGA < stopIndexTAA)) {
      minIndex = stopIndexTGA;
    }
    else {
      minIndex = stopIndexTAA;
    }
    if ((minIndex == -1) || (stopIndexTAG != -1 && stopIndexTAG < minIndex)) {
      minIndex = stopIndexTAG;
    }
    if (minIndex == -1) {
      return "";
    }
    return dna.substring(startIndex, minIndex + 3);
  }
    
  public void testFindGene() {
    String one = "ATFxyzxyzxyzTAAxyzTAGxyz";
    String two = "xyzATGxyzxyzxyTAGxTAAxyz";
	  String three = "xyzATGxyzxyzxyzTGAxyzTAGxyz";
	  String four = "xyzATGxyzxyzxyzxyzxyzTGAxyzTAGxyx";
	  String five = "xyzxyzATGxyzxyzxyzxTGAxyzxyzxyTGA";
	  System.out.println("DNA strand: " + one + "\nGene: " + findGene(one));
	  System.out.println("DNA strand: " + two + "\nGene: " + findGene(two));
	  System.out.println("DNA strand: " + three + "\nGene: " + findGene(three));
	  System.out.println("DNA strand: " + four + "\nGene: " + findGene(four));
	  System.out.println("DNA strand: " + five + "\nGene: " + findGene(five));
	  printAllGenes("xyzATGxyzxyzxyzxyzxyzTGAxyzTAGxyxATGxyzTAAATGCCCTAGATGTGA");
  }
    
  public void printAllGenes (String dna) {
    while (true) {
      String gene = findGene(dna);
      if(gene == "") {
        break;
      }
      else {
        System.out.println(gene);
        dna = dna.substring(dna.indexOf("ATG") + gene.length(), dna.length());
      }
    }
  }
    
  public static void main (String[] args) {
    Part1 p = new Part1();
    p.testFindGene();
  }
}
