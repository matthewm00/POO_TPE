package paint.backend.model;

public abstract class Figure implements Model{
    @Override
    public String toString(){
        return source();
    }

    public void validatePoints(Point topLeft, Point bottomRight){
        if(Double.compare(topLeft.getX(), bottomRight.getX()) > 0 || Double.compare(topLeft.getY(), bottomRight.getY()) > 0){
            throw new IllegalArgumentException("Con el cursor se debe dibujar desde un punto arriba a la izquierda hasta otro abajo a la derecha.");
        }
    }
}
