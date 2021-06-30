package paint.backend.model;

public class Circle extends Ellipse{

//    private Point centerPoint;
    //private double radius;
//    private Point radiusPoint; //x , y

    public Circle(Point centerPoint, Point radiusPoint) {
        super(centerPoint, radiusPoint);
//        this.centerPoint = centerPoint;
//        this.radiusPoint = radiusPoint;
    }

    @Override
    public void init(Point centerPoint, Point radiusPoint){
        this.centerPoint = centerPoint;
        double radius = Math.sqrt(Math.pow(centerPoint.differenceX(radiusPoint),2) + Math.pow(centerPoint.differenceY(radiusPoint),2));
        this.radiusY = this.radiusX = radius;
        this.topLeft = new Point(centerPoint.getX() - radiusX,centerPoint.getY() - radiusY);
        this.bottomRight = new Point(centerPoint.getX() + radiusX,centerPoint.getY() + radiusY);
    }

    public double getRadius() {
        return radiusX;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    @Override
    public String source() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getRadius());
    }
}
