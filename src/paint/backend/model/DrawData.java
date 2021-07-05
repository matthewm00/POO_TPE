package paint.backend.model;

import javafx.scene.paint.Color;

//Guarda y permite la modificion de todas las priopedades relacionadas a la personalizacion de figuras (borde, color, relleno)

public class DrawData {

    private Color fillColor, borderColor;
    private double borderWidth;

    public DrawData(Color fillColor, Color borderColor, double borderWidth) {
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.fillColor = fillColor;
    }

    public void setFillColor(Color fc) {
        this.fillColor = fc;
    }

    public void setBorderColor(Color sc) {
        this.borderColor = sc;
    }

    public void setBorderWidth(double sw) {
        this.borderWidth = sw;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public double getBorderWidth() {
        return borderWidth;
    }
}
