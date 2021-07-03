package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class Ellipse extends Figure{

    protected Point centerPoint;
    protected double radiusY, radiusX;

    public Ellipse(Point topLeft, Point bottomRight, Color fillColor, Color borderColor, double borderWidth) {
        super(topLeft, bottomRight,fillColor, borderColor, borderWidth);
    }

    @Override
    public boolean isFillable() {
        return true;
    }

    public double getHeight(){
        return 2 * radiusY;
    }

    public double getWidth(){
        return 2 * radiusX;
    }

    @Override
    protected void initPoints(Point topLeft, Point bottomRight){
        validatePoints(topLeft, bottomRight);
        this.start = topLeft;
        this.end = bottomRight;
        this.radiusX = bottomRight.differenceX(topLeft) / 2;
        this.radiusY = bottomRight.differenceY(topLeft) / 2;
        this.centerPoint = new Point(bottomRight.getX() - radiusX, bottomRight.getY() - radiusY);
    }

    @Override
    public String source() {
        return String.format("Elipse [Centro: %s , Radio X: %.2f , Radio Y: %.2f]", centerPoint, radiusX, radiusY);
    }

    @Override
    public List<Point> getPoints() {
        return Arrays.asList(centerPoint, start, end);
    }

    @Override
    public boolean containsPoint(Point p) {
        return (Math.pow(p.getX() - centerPoint.getX(), 2) / Math.pow(radiusX, 2)) + (Math.pow(p.getY() - centerPoint.getY(), 2) / Math.pow(radiusY,2)) <= 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillOval(start.getX(), start.getY(), getWidth(), getHeight());
        gc.strokeOval(start.getX(), start.getY(), getWidth(), getHeight());
    }
}
