package paint.backend.model;

public class Circle extends Ellipse{

    private Point centerPoint;
    //private double radius;
    private Point radiusPoint; //x , y

    public Circle(Point centerPoint, Point radiusPoint) {
        super(centerPoint, radiusPoint, radiusPoint);
        this.centerPoint = centerPoint;
        this.radiusPoint = radiusPoint;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radiusPoint.getX());
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

}
