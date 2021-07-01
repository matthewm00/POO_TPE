package paint.backend.button;

import paint.backend.Drawable;
import paint.backend.model.Figure;
import paint.backend.model.Point;
import paint.backend.model.Square;

import javafx.scene.paint.Color;

public class SquareButton extends FigureButton{
    public SquareButton(String text) {
        super(text);
    }


    @Override
    public Drawable createFigure(Point start, Point end, Color fillColor, Color borderColor, double borderWidth) {
        return new Square(start, end, fillColor, borderColor, borderWidth);
    }
}
