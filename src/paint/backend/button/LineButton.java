package paint.backend.button;

import javafx.scene.paint.Color;
import paint.backend.model.Figure;
import paint.backend.model.Point;
import paint.backend.model.Square;

public class LineButton extends FigureButton{
    public LineButton(String text) {
        super(text);
    }

    @Override
    public Figure createFigure(Point start, Point end,Color innerColor, Color borderColor, double limitWidth) {
        return new Square(start, start, innerColor, borderColor, limitWidth);
        //return new Line(start, end);
    }

}
