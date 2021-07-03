package paint.backend;

import javafx.scene.paint.Color;
import paint.backend.model.Point;

import java.util.*;

public class CanvasState {

    private final LinkedList<Drawable> list = new LinkedList<>();
    private final LinkedList<Drawable> selectedFigures = new LinkedList<>();

    public void addFigure(Drawable figure) {
        list.add(figure);
    }

    public void addSelectedFigure(Drawable figure){
        selectedFigures.add(figure);
    }

    public Iterable<Drawable> figures() {
        return new LinkedList<>(list);
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
                figure.setFillColor(color);
        });
    }

    public void setSelectedBorderColor(Color color){
        selectedFigures.forEach(figure -> {
            figure.setBorderColor(color);
        });
    }

    public void setSelectedBorderWidth(double width){
        selectedFigures.forEach(figure -> {
            figure.setBorderWidth(width);
        });
    }


    public void setSelectedFigures(Point start, Point end){
        deselectAllFigures();
        // Una sola figura
        if (start.equals(end)) {
            setTheSelectedFigure(start);
        }
        // Seleccion multiple
        else {
            ImaginaryRectangle area = new ImaginaryRectangle(start, end);
            boolean ok = true;
            for (Drawable figure : list) {
                for (Point point : figure.getPoints()) {
                    if (!area.containsPoint(point)) {
                        ok = false;
                    }
                }
                if (ok) addSelectedFigure(figure);
                ok = true;
            }
        }
    }

    public void setTheSelectedFigure(Point point){
        for (Drawable figure : list){
            if (figure.containsPoint(point)) {
                addSelectedFigure(figure);
            }
        }
    }

    public List<Drawable> getSelectedFigures(){
        return new ArrayList<>(selectedFigures);
    }

    public Drawable getTheSelectedFigure(){
        return selectedFigures.getFirst();
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
