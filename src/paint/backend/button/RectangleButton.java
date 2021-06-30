package paint.backend.button;

import paint.backend.model.Figure;
import paint.backend.model.Point;
import paint.backend.model.Rectangle;

import java.awt.*;

public class RectangleButton extends FigureButton{
    public RectangleButton(String text) {
        super(text);
    }

    @Override
    public Figure createFigure(Point start, Point end, Color innerColor, Color borderColor, double limitWidth) {
        return new Rectangle(start, end, innerColor, borderColor, limitWidth);
    }
}
