package paint.backend.model;

public class Point implements Comparable<Point>{

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

    public double differenceX(Point point){
        return Math.abs(getX() - point.getX());
    }

    public double differenceY(Point point){
        return Math.abs(getY() - point.getY());
    }

    public void movePoint(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public double distanceToPoint(Point p) {
        return Math.sqrt(Math.pow(p.getX() - this.getX(), 2) + Math.pow(p.getY() - this.getY(),2));
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    @Override
    public int compareTo(Point o) {
        int comp = Double.compare(getX(), o.getX());
        if (comp == 0){
            return Double.compare(getY(), o.getY());
        }
        return comp;
    }

}
