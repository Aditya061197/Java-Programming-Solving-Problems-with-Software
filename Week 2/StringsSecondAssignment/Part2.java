/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (13-07-2020)
 */
 
 
public class Part2 {
  public int howMany(String stringa, String stringb) {
    int times = 0;
    int position = stringb.indexOf(stringa);
    while (position >= 0) {
      times += 1;
      position = stringb.indexOf(stringa, position + stringa.length());
    }
    return times;
  }
    
  public void testHowMany () {
    System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
    System.out.println(howMany("AA", "ATAAAA"));
    System.out.println(howMany("A", "ATGAAATAAATAAA"));
  }
    
  public static void main (String[] args) {
    Part2 p = new Part2();
    p.testHowMany();
  }
}
