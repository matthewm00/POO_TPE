package paint.backend.button;

import paint.backend.Drawable;
import paint.backend.model.Ellipse;
import paint.backend.model.Figure;
import paint.backend.model.Point;

import javafx.scene.paint.Color;

public class EllipseButton extends FigureButton{
    public EllipseButton(String text) {
        super(text);
    }

    @Override
    public Drawable createFigure(Point start, Point end, Color fillColor, Color borderColor, double borderWidth) {
        return new Ellipse(start, end, fillColor, borderColor, borderWidth);
    }
}
