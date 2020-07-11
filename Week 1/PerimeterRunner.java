import edu.duke.*;

public class PerimeterRunner {
  public double getPerimeter (Shape s) {
    double totalPerimeter = 0.0;
    Point prevPoint = s.getLastPoint();
    for (Point currPoint : s.getPoints()) {
      double currDistance = prevPoint.distance(currPoint);
      totalPerimeter = totalPerimeter + currDistance;
      prevPoint = currPoint;
    }
    return totalPerimeter;
  }
  
  public int getNumPoints (Shape s) {
    int points = 0;
    for (Point p : s.getPoints()) {
      points += 1;
    }
    return points;
  }
  
  public double getAverageLength (Shape s) {
    return getPerimeter(s) / getNumPoints(s);
  }
  
  public double getLargestSide (Shape s) {
    Point prevPoint = s.getLastPoint();
    double largestSide = 0;
    for (Point currPoint : s.getPoints()) {
      double currDistance = prevPoint.distance(currPoint);
      if (currDistance > largestSide) {
        largestSide = currDistance;
      }
      prevPoint = currPoint;
    }
    return largestSide;
  }
  
  public double getLargestX (Shape s) {
    double largestX = 0;
    for (Point p : s.getPoints()) {
      double px = p.getX();
      if (px > largestX) {
        largestX = px;
      }
    }
    return largestX;
  }
  
  public void testPerimeter () {
    //using FileResource library, you can open .txt files containing the coordinates and run the program
    FileResource fr = new FileResource();
    System.out.println("No. of points = " + points);
    System.out.println("Average Length = " + getAverageLength(s));
    System.out.println("Largest Side = " + getLargestSide(s));
    System.out.println("Largest X = " + getLargestX(s));
    double length = getPerimeter(s);
    System.out.println("perimeter = " + length);
  }
  
  public static void main (String[] args) {
    PerimeterRunner pr = new PerimeterRunner();
    pr.testPerimeter();
  }
