/**
 * Write a description here.
 * 
 * @author (Aditya Kedia) 
 * @date (12-07-2020)
 */
 
 
import edu.duke.*;

public class Part4 {
  public void URLs (String url) {
    URLResource myurl = new URLResource(url);
    for (String word : myurl.words()) {
      if (word.toLowerCase().indexOf("youtube.com") != -1) {
        int startIndex = word.indexOf("\"");
        int stopIndex = word.indexOf("\"", startIndex + 1);
        System.out.println(word.substring(startIndex + 1, stopIndex));
      }
    }
  }

  public void testURLs () {
    URLs("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
  }

  public static void main (String[] args) {
    Part4 p = new Part4();
    p.testURLs();
  }
}
