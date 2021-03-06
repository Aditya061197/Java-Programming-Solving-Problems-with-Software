/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (13-07-2020)
 */
 
 
public class Part3 {
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
    
  public int countGenes (String dna) {
    int count = 0;
    while (true) {
      String gene = findGene(dna);
      if(gene == "") {
        break;
      }
      else {
        count += 1;
        dna = dna.substring(dna.indexOf("ATG") + gene.length(), dna.length());
      }
    }
    return count;
  }
    
  public void testCountGenes () {
    System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    System.out.println(countGenes("ATAGCCTAA"));
    System.out.println(countGenes("ATGTAAGATGCCCATCTGAATGTAG"));
  }
    
  public static void main (String[] args) {
    Part3 p = new Part3();
    p.testCountGenes();
  }
}
