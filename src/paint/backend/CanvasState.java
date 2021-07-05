package paint.backend;

import javafx.scene.paint.Color;
import paint.backend.model.Point;

import java.util.*;

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
        // Seleccion unica
        if (start.equals(end)) {
            setTheSelectedFigure(start);
        }
        // Seleccion multiple
        else if (Double.compare(start.getX(), end.getX()) < 0 && Double.compare(start.getY(), end.getY()) < 0){
            for (Drawable figure : list) {
                if (InsideImaginaryRectangle(start, end, figure.getPoints())) {
                    addSelectedFigure(figure);
                }
            }
        }
    }

    public void setTheSelectedFigure(Point point){
        Iterator<Drawable> it = list.descendingIterator();
        while (it.hasNext()){
            Drawable figure = it.next();
            if (figure.containsPoint(point)) {
                addSelectedFigure(figure);
                return;
            }
        }
    }

    public List<Drawable> getSelectedFigures(){
        return new ArrayList<>(selectedFigures);
    }

    public Drawable getTheSelectedFigure(){
        return list.getFirst();
    }

    public boolean hasSelectedFigures(){
        return !selectedFigures.isEmpty();
    }

    public void deselectAllFigures(){
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

    private boolean InsideImaginaryRectangle(Point topLeft, Point bottomRight, Collection<Point> figurePoints) {
        for (Point point : figurePoints){
            if (!(point.getX() >= topLeft.getX() && point.getX() <= bottomRight.getX() && point.getY() >= topLeft.getY() && point.getY() <= bottomRight.getY())){
                return false;
            }
        }
        return true;
    }
}
