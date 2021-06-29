package paint.backend.model;

import java.util.ArrayList;
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
    public List<Point> getPoints(){
        return Arrays.asList(startPoint, endPoint);
    }

}
