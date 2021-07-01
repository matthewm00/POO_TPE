package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Ellipse{

    public Circle(Point centerPoint, Point radiusPoint, Color innerColor, Color borderColor, double limitWidth) {
        super(centerPoint, radiusPoint, innerColor, borderColor, limitWidth);
    }

    @Override
    public void init(Point centerPoint, Point radiusPoint){
        double radius = centerPoint.distanceToPoint(radiusPoint);
        this.radiusX = radius;
        this.radiusY = radius;
        this.start = new Point(centerPoint.getX() - radiusX,centerPoint.getY() - radiusY);
        this.end = new Point(centerPoint.getX() + radiusX,centerPoint.getY() + radiusY);
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

    @Override
    public void draw(GraphicsContext gc){
        gc.fillOval(start.getX(), start.getY(), getWidth(), getHeight());
        gc.strokeOval(start.getX(), start.getY(), getWidth(), getHeight());
    }
}
