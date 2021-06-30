package paint.backend.button;

import paint.backend.model.Ellipse;
import paint.backend.model.Figure;
import paint.backend.model.Point;

import java.awt.*;

public class EllipseButton extends FigureButton{
    public EllipseButton(String text) {
        super(text);
    }
    @Override
    public Figure createFigure(Point start, Point end, Color innerColor, Color borderColor, double limitWidth) {
        return new Ellipse(start, end, innerColor, borderColor, limitWidth);
    }
}
