package paint.backend.model;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Ellipse extends ComplexFigure{

    protected Point centerPoint;
    protected double radiusY, radiusX;

    public Ellipse(Point topLeft, Point bottomRight, Color innerColor, Color borderColor, double limitWidth) {
        super(topLeft, bottomRight, innerColor, borderColor, limitWidth);
        init(topLeft, bottomRight);
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
}
