package paint.backend.model;

import javafx.scene.paint.Color;

public abstract class ComplexFigure extends Figure{
    private Color innerColor;

    public ComplexFigure(Point start, Point end, Color innerColor, Color borderColor, double limitWidth){ // relleno, borde, suAncho
        super(start, end, borderColor, limitWidth);
        this.innerColor = innerColor;
    }

    @Override
    public boolean isComplex(){return true;}

    public Color getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(Color innerColor) {
        this.innerColor = innerColor;
    }
}
