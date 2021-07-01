package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import java.util.Arrays;
import java.util.List;

public class Ellipse extends ComplexFigure{

    protected Point centerPoint;
    protected double radiusY, radiusX;

    public Ellipse(Point topLeft, Point bottomRight, Color borderColor, double borderWidth, Color fillColor) {
        super(topLeft, bottomRight, borderColor, borderWidth, fillColor);
        init(topLeft, bottomRight);
    }

    public double getHeight(){
        return 2 * radiusY;
    }

    public double getWidth(){
        return 2 * radiusX;
    }

    protected void init(Point topLeft, Point bottomRight){
        validatePoints(topLeft, bottomRight);
        this.start = topLeft;
        this.end = bottomRight;
        this.radiusX = bottomRight.differenceX(topLeft) / 2;
        this.radiusY = bottomRight.differenceY(topLeft) / 2;
        this.centerPoint = new Point(bottomRight.getX() - radiusX, bottomRight.getY() - radiusY);
    }

    @Override
    public String source() {
        return String.format("Elipse [Centro: %s , Radio X: %s , Radio Y: %s]", centerPoint, radiusX, radiusY);
    }

    @Override
    public List<Point> getPoints() {
        return Arrays.asList(centerPoint, start, end);
    }

    @Override
    public boolean containsPoint(Point p) {
        return Math.pow((centerPoint.getX() - p.getX())/radiusX, 2) + Math.pow((centerPoint.getY() - p.getY())/radiusY, 2) <= 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillOval(start.getX(), start.getY(), getWidth(), getHeight());
        gc.strokeOval(start.getX(), start.getY(), getWidth(), getHeight());
    }
}
