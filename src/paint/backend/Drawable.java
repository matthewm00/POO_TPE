package paint.backend;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import paint.backend.model.DrawData;
import paint.backend.model.Model;

public interface Drawable extends Model {

    DrawData getDrawData();

    void draw(GraphicsContext gc);

    boolean isFillable();

    default void setBorderColor(Color borderColor) {
        getDrawData().setBorderColor(borderColor);
    }

    default void setBorderWidth(double borderWidth) {
        getDrawData().setBorderWidth(borderWidth);
    }

    default Color getBorderColor() {
        return getDrawData().getBorderColor();
    }

    default double getBorderWidth() {
        return getDrawData().getBorderWidth();
    }

    default void setFillColor(Color fillColor){
        getDrawData().setFillColor(fillColor);
    }

    default Color getFillColor(){
       return getDrawData().getFillColor();
    }
}
