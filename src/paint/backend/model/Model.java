package paint.backend.model;

import java.util.List;

public interface Model {

    List<Point> getPoints();

    String source();

    default void move(double deltaX, double deltaY){
        for(Point point : getPoints()){
            point.movePoint(deltaX, deltaY);
        }
    }

    boolean containsPoint(Point p);

    boolean isComplex();
}
