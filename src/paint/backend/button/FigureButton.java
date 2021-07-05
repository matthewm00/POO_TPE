package paint.backend.button;

import javafx.scene.control.ToggleButton;


public abstract class FigureButton extends ToggleButton implements FigureCreator {
    public FigureButton(String text){
        super(text);
    }
}

