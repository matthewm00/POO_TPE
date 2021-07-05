package paint.backend.model;

import javafx.scene.paint.Color;
import paint.backend.Drawable;

import java.util.Objects;

public abstract class Figure implements Drawable {

    protected Point start, end;
    protected DrawData drawData;
    protected final int figureID;
    private static int idCount = 0;

    public Figure(Point start, Point end, Color fillColor, Color borderColor, double borderWidth) {
        initPoints(start, end);
        this.drawData = new DrawData(fillColor, borderColor, borderWidth);
        this.figureID = generateID();
    }

    protected void initPoints(Point start, Point end){
        this.start = start;
        this.end = end;
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
        return figureID == figure.figureID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(figureID);
    }

    @Override
    public DrawData getDrawData() {
        return drawData;
    }

    private int generateID() {
        return idCount++;
    }
}
