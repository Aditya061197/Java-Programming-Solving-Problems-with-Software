import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
  public double getPerimeter (Shape s) {
    double totalPerimeter = 0.0;
    Point prevPoint = s.getLastPoint();
    for (Point currPoint : s.getPoints()) {
      double currDistance = prevPoint.distance(currPoint);
      totalPerimeter += currDistance;
      prevPoint = currPoint;
    }
    return totalPerimeter;
  }
  
  public in getNumPoints (Shape s) {
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
    double largestSide = 0.0;
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
  
  public double getLargestPerimeterMultipleFiles () {
    //using DirectoryResource, you can check for multiple files at once
    DirectoryResource dr = new DirectoryResource();
    double largestPerimeter = 0;
    
    for (File f : dr.selectedFiles()) {
      FileResource file = new FileResource(f);
      Shape s = new Shape(file);
      double perimeter = getPerimeter(s);
      if (perimeter > largestPerimeter) {
        largestPerimeter = perimeter;
      }
    }
    return largestPerimeter;
  }
  
  public String getFileWithLargestPerimeter() {
    DirectoryResource dr = new DirectoryResource();
    double largestPerimeter = 0;
    File largestFile = null;
    
    for (File f : dr.selectedFiles()) {
      FileResource file = new FileResource(f);
      Shape s = new Shape(file);
      double perimeter = getPerimeter(s);
      if (perimeter > largestPerimeter) {
        largestPerimeter = perimeter;
        largestFile = f;
      }
    }
    return largestFile.getName();
  }
  
  public void testPerimeter () {
    FileResource fr = new FileResource();
    Shape s = new Shape(fr);
    int points = getNumPoints(s);
    System.out.println("No. of points = " + points);
    System.out.println("Average Length = " + getAverageLength(s));
    System.out.println("Largest Side = " + getLargestSide(s));
    System.out.println("Largest X = " + getLargestX(s));
    double length = getPerimeter(s);
    System.out.println("perimeter = " + length);
  }
  
  public void testPerimeterMultipleFiles () {
    System.out.println("Largest Perimeter = " + getLargestPerimeterMultipleFiles());
  }
  
  public void testFileWithLargestPerimeter() {
    System.out.println("Largest Perimeter File = " + getFileWithLargestPerimeter());
  }
  
  // This method creates a triangle that you can use to test your other methods
  public void triangle(){
    Shape triangle = new Shape();
    triangle.addPoint(new Point(0,0));
    triangle.addPoint(new Point(6,0));
    triangle.addPoint(new Point(3,6));
    for (Point p : triangle.getPoints()){
      System.out.println(p);
    }
    double peri = getPerimeter(triangle);
    System.out.println("perimeter = "+peri);
  }

   //This method prints names of all files in a chosen folder that you can use to test your other methods
   public void printFileNames() {
     DirectoryResource dr = new DirectoryResource();
     for (File f : dr.selectedFiles()) {
       System.out.println(f);
     }
   }

   public static void main (String[] args) {
     PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
     pr.testPerimeterMultipleFiles();
     pr.testFileWithLargestPerimeter();
   }
}
