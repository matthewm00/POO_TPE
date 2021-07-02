package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import java.util.Arrays;
import java.util.List;

public class Rectangle extends Figure {

    public Rectangle(Point topLeft, Point bottomRight, Color fillColor, Color borderColor, double borderWidth) {
        super(topLeft, bottomRight, fillColor, borderColor, borderWidth);
        validatePoints(topLeft, bottomRight);
        init(topLeft, bottomRight);
    }

    protected void init(Point topLeft, Point bottomRight) {
        this.start = topLeft;
        this.end = bottomRight;
    }

    public Point getTopLeft() {
        return start;
    }

    public Point getBottomRight() {
        return end;
    }

    public double getHeight()
    {
        return Math.abs(start.differenceY(end));
    }

    public double getWidth()
    {
        return Math.abs(start.differenceX(end));
    }

    @Override
    public String source() {
        return String.format("Rectángulo [ %s , %s ]", start, end);
    }

    @Override
    public List<Point> getPoints() {
            return Arrays.asList(start, end);
    }

    @Override
    public DrawData getDrawData() {
        return drawData;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(), getWidth(),getHeight());
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(), getWidth(), getHeight());
    }

    @Override
    public boolean containsPoint(Point p) {
        return p.getX() > getTopLeft().getX() && p.getX() < getBottomRight().getX() &&
                p.getY() > getTopLeft().getY() && p.getY() < getBottomRight().getY();
    }

    @Override
    public boolean isFillable() {
        return true;
    }
}
