package paint.backend.button;

import paint.backend.Drawable;
import paint.backend.model.Figure;
import paint.backend.model.Point;
import paint.backend.model.Rectangle;

import javafx.scene.paint.Color;

public class RectangleButton extends FigureButton{
    public RectangleButton(String text) {
        super(text);
    }

    @Override
    public Drawable createFigure(Point start, Point end, Color fillColor, Color borderColor, double borderWidth) {
        return new Rectangle(start, end, fillColor, borderColor, borderWidth);
    }
}
