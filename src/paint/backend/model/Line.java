package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class Line extends Figure{

    public Line(Point startPoint, Point endPoint, Color fillColor, Color borderColor, double borderWidth) {
        super(startPoint, endPoint,fillColor, borderColor, borderWidth);
    }

    public Point getStartPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    public boolean containsPoint(Point p) {
        return Double.compare(start.distanceToPoint(p) + p.distanceToPoint(end), start.distanceToPoint(end)) == 1;
    }

    public List<Point> getPoints(){
        return Arrays.asList(start, end);
    }

    public void draw(GraphicsContext gc) {
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public boolean isComplex() {
        return false;
    }

    public String source() {
        return String.format("Linea [ Desde: %s , Hasta: %s ]", start, end);
    }

}
