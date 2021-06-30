package paint.backend.button;

import paint.backend.model.Figure;
import paint.backend.model.Line;
import paint.backend.model.Point;

public class LineButton extends FigureButton{
    public LineButton(String text) {
        super(text);
    }
    @Override
    public Figure createFigure(Point start, Point end) {
        return new Line(start, end);
    }
}
