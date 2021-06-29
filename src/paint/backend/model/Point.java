package paint.backend.model;

public class Point {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double delta) {
        movePoint(delta, 0);
    }

    public void setY(double delta) {
        movePoint(0, delta);
    }

    public void movePoint(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

}
