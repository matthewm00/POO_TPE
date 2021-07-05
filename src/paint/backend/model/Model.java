package paint.backend.model;

import java.util.List;

//Permite mover figuras y obtener informacion acerca de su posicion en el canvas

public interface Model {

    List<Point> getPoints();

    String source();

    default void move(double deltaX, double deltaY){
        for(Point point : getPoints()){
            point.movePoint(deltaX, deltaY);
        }
    }

    boolean containsPoint(Point p);

}
