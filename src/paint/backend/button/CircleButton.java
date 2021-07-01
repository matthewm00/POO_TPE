package paint.backend.button;

import paint.backend.model.Circle;
import paint.backend.model.Figure;
import paint.backend.model.Point;
import javafx.scene.paint.Color;

public class CircleButton extends FigureButton{
    public CircleButton(String text) {
        super(text);
    }

    @Override
    public Figure createFigure(Point start, Point end, Color borderColor, double borderWidth, Color fillColor) {
        return new Circle(start, end, borderColor, borderWidth, fillColor);
    }
}
