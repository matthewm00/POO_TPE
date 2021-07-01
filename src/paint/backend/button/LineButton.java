package paint.backend.button;

import javafx.scene.paint.Color;
import paint.backend.model.Figure;
import paint.backend.model.Line;
import paint.backend.model.Point;

public class LineButton extends FigureButton{
    public LineButton(String text) {
        super(text);
    }
    @Override
    public Figure createFigure(Point start, Point end, Color borderColor, double fillWidth, Color fillColor) {
        return new Line(start, end, borderColor, fillWidth);
    }

}
