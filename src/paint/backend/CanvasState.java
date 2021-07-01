package paint.backend;

import paint.backend.model.Figure;
import paint.backend.model.Point;

import java.util.*;

public class CanvasState {

    private final List<Drawable> list = new ArrayList<>();
    private final Set<Drawable> selectedFigures = new HashSet<>();

    public void addFigure(Drawable figure) {
        list.add(figure);
    }
    public void addSelectedFigure(Drawable figure){
        selectedFigures.add(figure);
    }
    public Iterable<Drawable> figures() {
        return list;
    }

//    public void removeSelectedFigures(){
//        list.removeAll(selectedFigures);
//    }

    public void moveSelectedFigures(double deltaX, double deltaY){
        selectedFigures.forEach(figure -> figure.move(deltaX, deltaY));
    }

    public Set<Drawable> getSelectedFigures(Point start, Point end){
        deselectAllFigures();
        ImaginaryRectangle area = new ImaginaryRectangle(start, end);// imaginario
        boolean ok = true;
        for (Drawable figure : list){ // recorro todas las figuras
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

//    NO REPITO CODIGO O PODRIA USAR EL OTRO GET??
//    lo tengo que agregar seguramente al set para mas adelante pero como
//    hago para darme cuenta si o si que el set es de un solo elemento?
//    creo que asi, si queda de un solo elem pero chequear
    public Drawable getTheSelectedFigure(Point point){
        deselectAllFigures();
        for (Drawable figure : list){
            if (figure.containsPoint(point)) {
                addSelectedFigure(figure);
                return figure;
            }
        }
        return null;
    }

    private void deselectAllFigures(){
        selectedFigures.clear();
    }

    public boolean containsSelectedFigure(Drawable figure) {
        return selectedFigures.contains(figure);
    }

    public void setBorderWidth(double width) {
        for(Drawable figure: selectedFigures) {
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
