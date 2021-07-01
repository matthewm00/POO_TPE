package paint.backend.button;

import paint.backend.model.Figure;
import paint.backend.model.Point;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface CreateFigure {
    Figure createFigure(Point start, Point end, Color borderColor, double borderWidth, Color fillColor);
}
