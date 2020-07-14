/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (14-07-2020)
 */

import edu.duke.*;

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
	  StorageResource genes = getAllGenes("ATGCCGTAAGTCCCTAAGTAGATGTAAATGTAGATGTGA");
	  for (String gene : genes.data()) {
	    System.out.println(gene);
	  }
  }
    
  public StorageResource getAllGenes (String dna) {
    StorageResource genelist = new StorageResource();
    while (true) {
      String gene = findGene(dna);
      if(gene == "") {
        break;
      }
      else {
        genelist.add(gene);
        dna = dna.substring(dna.indexOf("ATG") + gene.length(), dna.length());
      }
    }
    return genelist;
  }
    
  public double cgRatio (String dna) {
    int cgs = 0;
    int len = 0;
    while (len < dna.length()) {
      if (dna.charAt(len) == 'C' || dna.charAt(len) == 'c' || dna.charAt(len) == 'G' || dna.charAt(len) == 'g') {
        cgs += 1;
      }
      len += 1;
    }
    return ((float)cgs / len);
  }
    
  public int countCTG (String dna) {
    int ctgs = dna.indexOf("CTG");
    int times = 0;
    while (ctgs != -1) {
      times += 1;
      ctgs = dna.indexOf("CTG", ctgs + 3);
    }
    return times;
  }
    
  public void processGenes(StorageResource sr) {
    int longer = 0;
    int highRatio = 0;
    int longestGene = 0;
    int total = 0;
    for (String gene : sr.data()) {
      if (gene.length() > 60) {
        System.out.println("Long gene: " + gene);
        longer += 1;
      }
      if (cgRatio(gene) > 0.35) {
        System.out.println("High CG-Ratio gene: " + gene);
        highRatio += 1;
      }
      if (gene.length() > longestGene) {
        longestGene = gene.length();
      }
      total += 1;
    }
    System.out.println("Number of strings longer than 60 characters: " + longer);
    System.out.println("Number of strings with high CG-ratio: " + highRatio);
    System.out.println("Length of longest gene: " + longestGene);
    System.out.println("Total: " + total);
  }
    
  public void testProcessGenes() {
    FileResource fr = new FileResource("GRch38dnapart.fa.txt");
    String dna = fr.asString();
    StorageResource sr = getAllGenes(dna);
    processGenes(sr);
  }
    
  public static void main (String[] args) {
    Part1 p = new Part1();
    p.testProcessGenes();
  }
}
