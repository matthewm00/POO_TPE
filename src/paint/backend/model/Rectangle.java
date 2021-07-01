package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;
import java.util.Arrays;
import java.util.List;

public class Rectangle extends ComplexFigure {

    public Rectangle(Point topLeft, Point bottomRight, Color innerColor, Color borderColor, double limitWidth) {
        super(topLeft, bottomRight, innerColor, borderColor, limitWidth);
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
    public void draw(GraphicsContext gc) {
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(), getWidth(),getHeight());
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(), getWidth(), getHeight());
    }
}
