package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Rectangle{

    public Square(Point topLeft, Point rightPoint, Color fillColor, Color borderColor, double borderWidth) {
        super(topLeft, rightPoint, fillColor, borderColor, borderWidth);
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

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(), getWidth(), getHeight());
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(), getWidth(), getHeight());
    }
}
