package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class Line extends Figure{

    private double gradient;
    private static final int EPSILON = 3;

    public Line(Point startPoint, Point endPoint, Color fillColor, Color borderColor, double borderWidth) {
        super(startPoint, endPoint,fillColor, borderColor, borderWidth);
        start = start.compareTo(endPoint) <= 0 ? start : end ;
        end = start.compareTo(endPoint) <= 0 ? end : start ;
        gradient = endPoint.differenceY(start) / endPoint.differenceX(start);
    }

    public Point getStartPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    @Override
    public boolean containsPoint(Point p) {
        if( (p.getX() > start.getX() && p.getX() < end.getX()) ) {
            double aux = start.getY() - gradient * start.getX();
            double cmp = p.getY() - aux - gradient * p.getX();
            return cmp > -EPSILON && cmp < EPSILON;
        }
        return false;
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
