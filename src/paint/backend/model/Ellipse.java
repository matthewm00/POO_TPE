package paint.backend.model;

import java.util.Arrays;
import java.util.List;

public class Ellipse extends Figure{

    protected Point centerPoint, bottomRight, topLeft;
    protected double radiusY, radiusX;

    public Ellipse(Point topLeft, Point bottomRight) {
        init(topLeft, bottomRight);
    }

    public void init(Point topLeft, Point bottomRight){
        validatePoints(topLeft, bottomRight);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.radiusX = topLeft.differenceX(bottomRight) / 2;
        this.radiusY = topLeft.differenceY(bottomRight) / 2;
        this.centerPoint = new Point(topLeft.getX() + radiusX, topLeft.getY() + radiusY);
    }

    @Override
    public String source() {
        return String.format("Elipse [Centro: %s , Radio X: %s , Radio Y: %s]", centerPoint, radiusX, radiusY);
    }

    @Override
    public List<Point> getPoints() {
        return Arrays.asList(centerPoint, bottomRight, topLeft);
    }

    @Override
    public boolean containsPoint(Point p) {
        return Math.pow((centerPoint.getX() - p.getX())/radiusX, 2) + Math.pow((centerPoint.getY() - p.getY())/radiusY, 2) <= 1;
    }
}
