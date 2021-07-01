package paint.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Objects;

public abstract class Figure implements Model {

    private Color borderColor;
    private double borderWidth;
    protected Point start, end;
    protected DrawData drawData;


    public Figure(Point start, Point end, Color borderColor, double borderWidth) {
        this.start = start;
        this.end = end;
        this.drawData = new DrawData(fillColor, borderColor, borderWidth);
    }

    public abstract void draw(GraphicsContext gc);

    public abstract boolean isComplex();

    public abstract boolean containsPoint(Point p);

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setBorderWidth(double borderWidth) {
        this.borderWidth = borderWidth;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public double getBorderWidth() {
        return borderWidth;
    }

    @Override
    public String toString() {
        return source();
    }

    public void validatePoints(Point topLeft, Point bottomRight) {
        if (Double.compare(topLeft.getX(), bottomRight.getX()) > 0 || Double.compare(topLeft.getY(), bottomRight.getY()) > 0) {
            throw new IllegalArgumentException("Con el cursor se debe dibujar desde un punto arriba a la izquierda hasta otro abajo a la derecha.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Figure)) return false;
        Figure figure = (Figure) o;
        return this.getPoints().equals(figure.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints());
    }
}
