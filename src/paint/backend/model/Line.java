package paint.backend.model;

import java.util.Arrays;
import java.util.List;

public class Line extends Figure{

    private Point startPoint, endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public boolean containsPoint(Point p) {
        return Double.compare(startPoint.distanceToPoint(p) + p.distanceToPoint(endPoint), startPoint.distanceToPoint(endPoint)) == 1;
    }

    @Override
    public List<Point> getPoints(){
        return Arrays.asList(startPoint, endPoint);
    }

    @Override
    public String source() {
        return String.format("Linea [ De: %s , Hasta: %s ]", startPoint, endPoint);
    }

}
