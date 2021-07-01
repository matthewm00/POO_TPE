package paint.backend.model;

import javafx.scene.paint.Color;

/*
ComplexFigure: Borde(Color/Grosor) + relleno
 */

public abstract class ComplexFigure extends Figure{
    private Color fillColor;

    public ComplexFigure(Point start, Point end, Color borderColor, double borderWidth, Color fillColor) {
        super(start, end, borderColor, borderWidth);
        this.fillColor = fillColor;
    }

    @Override
    public boolean isComplex(){ return true;}

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
