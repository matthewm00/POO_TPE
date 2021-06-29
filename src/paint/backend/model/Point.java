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

    public double incrementX(double amount) {
        return x += amount;
    }

    public double incrementY(double amount) {
        return y += amount;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

}
