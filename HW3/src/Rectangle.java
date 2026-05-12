package src;

class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

   public Rectangle(Point upperLeft, double width, double height){
    this.upperLeft = upperLeft;
    this.width = width;
    this.height = height;
   }

   public java.util.List<Point> intersectionPoints(Line line){

   }

   public double getWidth(){
    return this.width;
   }
   public double getHeight(){
    return this.height;
   }

   public Point getUpperLeft(){
    return this.upperLeft;

   }
}