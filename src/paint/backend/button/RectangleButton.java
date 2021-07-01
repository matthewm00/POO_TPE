package paint.backend.button;

import paint.backend.model.Figure;
import paint.backend.model.Point;
import paint.backend.model.Rectangle;

import javafx.scene.paint.Color;

public class RectangleButton extends FigureButton{
    public RectangleButton(String text) {
        super(text);
    }

    @Override
    public Figure createFigure(Point start, Point end, Color borderColor, double borderWidth, Color fillColor) {
        return new Rectangle(start, end, borderColor, borderWidth, fillColor);
    }
}
