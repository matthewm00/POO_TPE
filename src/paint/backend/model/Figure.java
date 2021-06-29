package paint.backend.model;

public abstract class Figure implements Model{
    @Override
    public String toString(){
        return source();
    }
}
