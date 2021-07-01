package paint.backend.button;

import paint.backend.Drawable;
import paint.backend.model.Circle;
import paint.backend.model.Figure;
import paint.backend.model.Point;
import javafx.scene.paint.Color;

public class CircleButton extends FigureButton{
    public CircleButton(String text) {
        super(text);
    }

    @Override
    public Drawable createFigure(Point start, Point end, Color fillColor, Color borderColor, double borderWidth) {
        return new Circle(start, end, fillColor, borderColor, borderWidth);
    }
}
