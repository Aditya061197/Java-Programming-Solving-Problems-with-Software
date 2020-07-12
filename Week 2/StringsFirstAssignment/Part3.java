/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (12-07-2020)
 */
 

public class Part3 {
  public boolean twoOccurences (String stringa, String stringb) {
    int firstOccurence = stringb.indexOf(stringa);
    if (firstOccurence == -1) {
      return false;
    }
    else {
      int secondOccurence = stringb.indexOf(stringa, firstOccurence + stringa.length());
      if (secondOccurence == -1) {
        return false;
      }
    }
    return true;
  }
  
  public String lastPart (String stringa, String stringb) {
    int firstOccurence = stringb.indexOf(stringa);
    if (firstOccurence == -1) {
      return stringb;
    }
    else {
      return stringb.substring(firstOccurence + stringa.length(), stringb.length());
    }
  }
  
  public void testing() {
    System.out.println(twoOccurences("by", "A story by Abby Long"));
    System.out.println(twoOccurences("a", "banana"));
    System.out.println(twoOccurences("atg", "ctgtatgta"));
        
    System.out.println(lastPart("an", "banana"));
    System.out.println(lastPart("zoo", "forest"));
  }
  
  public static void main (String[] args) {
    Part3 p = new Part3();
    p.testing();
  }
}
