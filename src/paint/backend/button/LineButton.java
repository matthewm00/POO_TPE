package paint.backend.button;

import javafx.scene.paint.Color;
import paint.backend.Drawable;
import paint.backend.model.Figure;
import paint.backend.model.Line;
import paint.backend.model.Point;

public class LineButton extends FigureButton{
    public LineButton(String text) {
        super(text);
    }
    @Override
    public Drawable createFigure(Point start, Point end, Color fillColor, Color borderColor, double borderWidth) {
        return new Line(start, end, fillColor, borderColor, borderWidth);
    }

}
