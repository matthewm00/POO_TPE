package paint.backend.button;

import paint.backend.model.Ellipse;
import paint.backend.model.Figure;
import paint.backend.model.Point;

public class EllipseButton extends FigureButton{
    public EllipseButton(String text) {
        super(text);
    }
    @Override
    public Figure createFigure(Point start, Point end) {
        return new Ellipse(start, end);
    }
}
