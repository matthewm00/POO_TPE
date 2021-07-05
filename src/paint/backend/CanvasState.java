package paint.backend;

import javafx.scene.paint.Color;
import paint.backend.model.Point;

import java.util.*;

public class CanvasState {

    //Colleccion con las figuras en el canvas donde las nuevas figuras se sobreponen a las antiguas
    private final LinkedList<Drawable> list = new LinkedList<>();
    //Colleccion con las figuras actualmente seleccionadas
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

    //Remueve de la lista de todas las figuras, las actualmente seleccionadas
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
        selectedFigures.forEach(figure -> figure.setBorderColor(color));
    }

    public void setSelectedBorderWidth(double width){
        selectedFigures.forEach(figure -> figure.setBorderWidth(width));
    }

    //Guarda en la lista selectedFigures las nuevas figuras seleccionas
    public void setSelectedFigures(Point start, Point end){
        // Seleccion unica
        if (start.equals(end)) {
            setTheSelectedFigure(start);
        }
        // Seleccion multiple
        else if (start.compareTo(end) < 0){
            for (Drawable figure : list) {
                if (InsideImaginaryRectangle(start, end, figure.getPoints())) {
                    addSelectedFigure(figure);
                }
            }
        }
    }

    //Guarda la figura seleccionada con un click
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
        return selectedFigures.getFirst();
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

    //Remueve las figuras seleccionadas de la lista general para luego agregarlas adelante en el canvas
    public void moveToFront() {
        removeSelectedFigures();
        for (Drawable figure : selectedFigures)
            list.addLast(figure);
    }

    //Remueve las figuras seleccionadas de la lista general para luego agregarlas atras en el canvas
    public void moveToBack() {
        removeSelectedFigures();
        for (Drawable figure : selectedFigures)
            list.addFirst(figure);
    }

    //Verifica que los puntos de una figura esten contenidos en el rectangulo imaginario creado
    private boolean InsideImaginaryRectangle(Point topLeft, Point bottomRight, Collection<Point> figurePoints) {
        for (Point point : figurePoints){
            if (!(point.getX() >= topLeft.getX() && point.getX() <= bottomRight.getX() && point.getY() >= topLeft.getY() && point.getY() <= bottomRight.getY())){
                return false;
            }
        }
        return true;
    }
}

