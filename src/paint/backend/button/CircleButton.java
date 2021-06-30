package paint.backend.button;

import paint.backend.model.Circle;
import paint.backend.model.Figure;
import paint.backend.model.Point;

public class CircleButton extends FigureButton{
    public CircleButton(String text) {
        super(text);
    }
    @Override
    public Figure createFigure(Point start, Point end) {
        return new Circle(start, end);
    }
}
