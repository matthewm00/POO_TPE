package paint.backend.model;

import javafx.scene.paint.Color;

/*
SimpleFigure: Borde(Color/Grosor)
 */

public abstract class SimpleFigure extends Figure{
    protected Point start, end;

    public SimpleFigure(Point start, Point end, Color borderColor, double borderWidth) {
        super(start, end, borderColor, borderWidth);
    }

    public boolean isComplex() {return false;}

//    metodo default de containsPoint
    public boolean containsPoint(Point p) {
        return p.getX() > start.getX() && p.getX() < end.getX() && p.getY() > start.getY() && p.getY() < end.getY();
    }
}
