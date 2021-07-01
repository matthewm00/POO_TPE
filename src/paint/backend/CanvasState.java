package paint.backend;

import paint.backend.model.Figure;
import paint.backend.model.Point;

import java.util.*;

public class CanvasState {

    private final List<Figure> list = new ArrayList<>();
    private final Set<Figure> selectedFigures = new HashSet<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }
    public void addSelectedFigure(Figure figure){
        selectedFigures.add(figure);
    }
    public Iterable<Figure> figures() {
        return list;
    }

//    public void removeSelectedFigures(){
//        list.removeAll(selectedFigures);
//    }

    public void moveSelectedFigures(double deltaX, double deltaY){
        selectedFigures.forEach(figure -> figure.move(deltaX, deltaY));
    }

    public Set<Figure> getSelectedFigures(Point start, Point end){
        deselectAllFigures();
        ImaginaryRectangle area = new ImaginaryRectangle(start, end);// imaginario
        boolean ok = true;
        for (Figure figure : list){ // recorro todas las figuras
            for (Point point : figure.getPoints()) {
//                si en area no tengo todos los puntos de la figure
                if (!area.containsPoint(point)){ // paso al siguiente
                    ok = false;
                }
            }
            if (ok) addSelectedFigure(figure);
            ok = true;
        }
        return selectedFigures;
    }

    private void deselectAllFigures(){
        selectedFigures.clear();
    }

    public boolean containsSelectedFigure(Figure figure) {
        return selectedFigures.contains(figure);
    }

    public void setBorderWidth(double width) {
        for(Figure figure: selectedFigures) {
            figure.setBorderWidth(width);
        }
    }

    //VER
    private class ImaginaryRectangle {
        Point start;
        Point end;

        public ImaginaryRectangle(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        public boolean containsPoint(Point p) {
            return p.getX() > start.getX() && p.getX() < start.getX() &&
                    p.getY() > end.getY() && p.getY() < end.getY();
        }
    }

}
