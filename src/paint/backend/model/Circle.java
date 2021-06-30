package paint.backend.model;

import java.util.Arrays;
import java.util.List;

public class Circle extends Ellipse{

    public Circle(Point centerPoint, Point radiusPoint) {
        super(centerPoint, radiusPoint);
    }

    @Override
    public void init(Point centerPoint, Point radiusPoint){
        double radius = Math.sqrt(Math.pow(centerPoint.differenceX(radiusPoint),2) + Math.pow(centerPoint.differenceY(radiusPoint),2));
        this.radiusX = radius;
        this.radiusY = radius;
        this.topLeft = new Point(centerPoint.getX() - radiusX,centerPoint.getY() - radiusY);
        this.bottomRight = new Point(centerPoint.getX() + radiusX,centerPoint.getY() + radiusY);
        this.centerPoint = centerPoint;
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
