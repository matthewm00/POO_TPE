package paint.backend.model;

public class Square extends Rectangle{

    public Square(Point topLeft, Point rightPoint) {
        super(topLeft, rightPoint);
    }

    @Override
    public String source() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }
}