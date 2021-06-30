package paint.backend.button;

import paint.backend.model.Figure;
import paint.backend.model.Point;

import java.awt.*;

@FunctionalInterface
public interface CreateFigure {
    Figure createFigure(Point start, Point end, Color innerColor, Color borderColor, double limitWidth);
}
