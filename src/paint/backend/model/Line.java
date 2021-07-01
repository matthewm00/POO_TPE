package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class Line extends SimpleFigure{

    private Point startPoint, endPoint;

    public Line(Point startPoint, Point endPoint, Color borderColor, double borderWidth) {
        super(startPoint, endPoint, borderColor, borderWidth);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public boolean containsPoint(Point p) {
        return Double.compare(startPoint.distanceToPoint(p) + p.distanceToPoint(endPoint), startPoint.distanceToPoint(endPoint)) == 1;
    }

    public List<Point> getPoints(){
        return Arrays.asList(startPoint, endPoint);
    }

    public void draw(GraphicsContext gc) {
        gc.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    }

    public String source() {
        return String.format("Linea [ Desde: %s , Hasta: %s ]", startPoint, endPoint);
    }

}
