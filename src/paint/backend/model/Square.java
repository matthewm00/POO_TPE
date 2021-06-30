package paint.backend.model;

import java.awt.*;

public class Square extends Rectangle{

    public Square(Point topLeft, Point rightPoint, Color innerColor, Color borderColor, double limitWidth) {
        super(topLeft, rightPoint, innerColor, borderColor, limitWidth);
    }

    @Override
    protected void init(Point topLeft, Point bottomRight) {
        this.start = topLeft;
        this.end = new Point(bottomRight.getX(), topLeft.getY() + bottomRight.getX() - topLeft.getX());
    }

    @Override
    public String source() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }
}
