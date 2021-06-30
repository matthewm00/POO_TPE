package paint.backend.model;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Line extends Figure{

    public Line(Point startPoint, Point endPoint, Color borderColor, double limitWidth) {
        super(startPoint, endPoint, borderColor, limitWidth);

    }

    public Point getStartPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    @Override
    public boolean containsPoint(Point p) {
        return Double.compare(start.distanceToPoint(p) + p.distanceToPoint(end), start.distanceToPoint(end)) == 1;
    }

    @Override
    public List<Point> getPoints(){
        return Arrays.asList(start, end);
    }

    @Override
    public String source() {
        return String.format("Linea [ De: %s , Hasta: %s ]", start, end);
    }

}
