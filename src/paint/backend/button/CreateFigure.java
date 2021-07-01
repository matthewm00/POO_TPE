package paint.backend.button;

import paint.backend.Drawable;
import paint.backend.model.Figure;
import paint.backend.model.Point;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface CreateFigure {
    Drawable createFigure(Point start, Point end, Color fillColor, Color borderColor, double borderWidth);
}
