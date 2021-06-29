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

    public double getRadius() {
        return radiusPoint.getX();
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    @Override
    public String source() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radiusPoint.getX());
    }
}
