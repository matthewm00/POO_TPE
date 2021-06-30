package paint.backend.button;

import paint.backend.model.Figure;
import paint.backend.model.Point;

@FunctionalInterface
public interface CreateFigure {
    Figure createFigure(Point start, Point end);
}
