package paint.backend;

import javafx.scene.paint.Color;
import paint.backend.model.Point;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class CanvasState {

    private final LinkedList<Drawable> list = new LinkedList<>();
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

    public void removeSelectedFigures(){
        list.removeAll(selectedFigures);
    }

    public void moveSelectedFigures(double deltaX, double deltaY){
        selectedFigures.forEach(figure -> figure.move(deltaX, deltaY));
    }

    public void setSelectedFillColor(Color color){
        selectedFigures.forEach(figure -> {
            if (figure.isFillable())
                figure.setFillColor(figure.getFillColor());
        });
    }

    public void setSelectedBorderColor(Color color){
        selectedFigures.forEach(figure -> {
            figure.setBorderColor(figure.getBorderColor());
        });
    }

    public void setSelectedBorderWidth(double width){
        selectedFigures.forEach(figure -> {
            figure.setBorderWidth(figure.getBorderWidth());
        });
    }


    // Seleccion multiple
    public Set<Drawable> getSelectedFigures(Point start, Point end){
        deselectAllFigures();
        ImaginaryRectangle area = new ImaginaryRectangle(start, end);
        boolean ok = true;
        for (Drawable figure : list){
            for (Point point : figure.getPoints()) {
                if (!area.containsPoint(point)){
                    ok = false;
                }
            }
            if (ok) addSelectedFigure(figure);
            ok = true;
        }
        return selectedFigures;
    }

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

    public boolean hasSelectedFigures(){
        return !selectedFigures.isEmpty();
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

    public void moveToFront() {
        removeSelectedFigures();
        for (Drawable figure : selectedFigures)
            list.addLast(figure); // para invertir el orden de la coleccion
    }

    public void moveToBack() {
        removeSelectedFigures();
        for (Drawable figure : selectedFigures)
            list.addFirst(figure);
    }


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
