package paint.backend.button;

import paint.backend.model.Ellipse;
import paint.backend.model.Figure;
import paint.backend.model.Point;

import javafx.scene.paint.Color;

public class EllipseButton extends FigureButton{
    public EllipseButton(String text) {
        super(text);
    }

    @Override
    public Figure createFigure(Point start, Point end, Color borderColor, double borderWidth, Color fillColor) {
        return new Ellipse(start, end, borderColor, borderWidth, fillColor);
    }
}
