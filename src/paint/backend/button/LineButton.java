package paint.backend.button;

import paint.backend.model.Figure;
import paint.backend.model.Line;
import paint.backend.model.Point;

import java.awt.*;

public class LineButton extends FigureButton{
    public LineButton(String text) {
        super(text);
    }
    @Override
    public Figure createFigure(Point start, Point end,Color innerColor, Color borderColor, double limitWidth) {
        return new Line(start, end, borderColor, limitWidth);
    }
}
