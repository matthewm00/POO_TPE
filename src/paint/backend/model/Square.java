package paint.backend.model;

public class Square extends Rectangle{

    public Square(Point topLeft, Point rightPoint) {
        super(topLeft, rightPoint);
    }

    @Override
    protected void init(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = new Point(bottomRight.getX(), topLeft.getY() + bottomRight.getX() - topLeft.getX());
    }

    @Override
    public String source() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }
}
