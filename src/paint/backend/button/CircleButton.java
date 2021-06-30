package paint.backend.button;

import paint.backend.model.Circle;
import paint.backend.model.Figure;
import paint.backend.model.Point;

import java.awt.*;

public class CircleButton extends FigureButton{
    public CircleButton(String text) {
        super(text);
    }

    @Override
    public Figure createFigure(Point start, Point end, Color innerColor, Color borderColor, double limitWidth) {
        return new Circle(start, end, innerColor, borderColor, limitWidth);
    }
}
