package paint.backend.model;

import java.awt.*;
import java.util.Objects;

public abstract class Figure extends SimpleFigure implements Model{

    private Color borderColor;
    private double limitWidth;

    public Figure(Point start, Point end, Color borderColor, double limitWidth) {
        super(start, end);
        this.borderColor = borderColor;
        this.limitWidth = limitWidth;
    }

    public boolean isComplex(){return false;}

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setLimitWidth(double limitWidth) {
        this.limitWidth = limitWidth;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public double getLimitWidth() {
        return limitWidth;
    }

    @Override
    public String toString(){
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
