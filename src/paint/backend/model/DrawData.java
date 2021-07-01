package paint.backend.model;

import javafx.scene.paint.Color;


public class DrawData {

    private static final Color SELECTION_STROKE_COLOR = Color.RED;
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
