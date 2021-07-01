package paint.backend.button;

import paint.backend.model.Figure;
import paint.backend.model.Point;
import paint.backend.model.Square;

import javafx.scene.paint.Color;

public class SquareButton extends FigureButton{
    public SquareButton(String text) {
        super(text);
    }


    @Override
    public Figure createFigure(Point start, Point end, Color borderColor, double borderWidth, Color fillColor) {
        return new Square(start, end, borderColor, borderWidth, fillColor);
    }
}
