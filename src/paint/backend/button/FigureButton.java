package paint.backend.button;

import javafx.scene.control.ToggleButton;
import paint.backend.model.Figure;
import paint.backend.model.Point;


public abstract class FigureButton extends ToggleButton implements CreateFigure {
    public FigureButton(String text){
        super(text);
    }
}

