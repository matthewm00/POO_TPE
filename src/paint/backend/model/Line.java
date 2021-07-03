package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class Line extends Figure{

    private final double gradient;
    private static final int EPSILON = 2;

    public Line(Point startPoint, Point endPoint, Color fillColor, Color borderColor, double borderWidth) {
        super(startPoint, endPoint,fillColor, borderColor, borderWidth);
        gradient = endPoint.differenceY(startPoint) / endPoint.differenceX(startPoint);
    }

    @Override
    protected void initPoints(Point start, Point end) {
        this.start = start.compareTo(end) <= 0 ? start : end ;
        this.end = start.compareTo(end) <= 0 ? end : start ;
    }

    public Point getStartPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    @Override
    public boolean containsPoint(Point p) {
        double aux = p.getY() - end.getY() - gradient * (p.getX() - end.getX());
        return aux < EPSILON && aux > -EPSILON && Double.compare(start.getX(), p.getX()) <= 0 && Double.compare(p.getX(),end.getX()) <= 0;
    }

    public List<Point> getPoints(){
        return Arrays.asList(start, end);
    }

    public void draw(GraphicsContext gc) {
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public boolean isFillable() {
        return false;
    }

    public String source() {
        return String.format("Linea [ Desde: %s , Hasta: %s ]", start, end);
    }

}
