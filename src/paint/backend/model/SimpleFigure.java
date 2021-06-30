package paint.backend.model;

public abstract class SimpleFigure {
    protected Point start, end;

    public SimpleFigure(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

//    metodo default de containsPoint
    public boolean containsPoint(Point p) {
        return p.getX() > start.getX() && p.getX() < end.getX() && p.getY() > start.getY() && p.getY() < end.getY();
    }
}
