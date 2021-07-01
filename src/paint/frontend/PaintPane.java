package paint.frontend;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import paint.backend.CanvasState;
import paint.backend.Drawable;
import paint.backend.button.*;
import paint.backend.model.Figure;
import paint.backend.model.Point;

import java.util.Set;

public class PaintPane extends BorderPane {

	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.YELLOW;
	private static final Color SELECTED_COLOR = Color.RED;
	private	static final double MAX_VALUE_SLIDER = 50;
	private static final double MIN_VALUE_SLIDER = 1;
	private static final double INITIAL_VALUE_SLIDER = 0;

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");

	private final FigureButton rectangleButton = new RectangleButton("Rectángulo");
	private final FigureButton circleButton = new CircleButton("Círculo");
	private final FigureButton ellipseButton = new EllipseButton("Elipse");
	private final FigureButton squareButton = new SquareButton("Cuadrado");
	private final FigureButton lineButton = new LineButton("Linea");

	private final Slider borderWidthSlider = new Slider(MIN_VALUE_SLIDER, MAX_VALUE_SLIDER, INITIAL_VALUE_SLIDER);
	private final ColorPicker borderColorPicker = new ColorPicker(DEFAULT_BORDER_COLOR);
	private final ColorPicker fillColorPicker = new ColorPicker(DEFAULT_FILL_COLOR);

	private final ToggleButton deleteButton = new ToggleButton("Borrar");
	private final ToggleButton backButton = new ToggleButton("Al Fondo");
	private final ToggleButton frontButton = new ToggleButton("Al Frente");

	// Dibujar una figura
	private Point startPoint;

	// Seleccionar una figura
	private Drawable selectedFigure;

	// StatusBar
	private StatusPane statusPane;

	// Boton clickeado
	private FigureButton clickedButton;


	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, ellipseButton, squareButton, lineButton, deleteButton, backButton, frontButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}

		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);

		Label borderWidthText = new Label("Borde");
		borderWidthSlider.setShowTickLabels(true);
		borderWidthSlider.setShowTickMarks(true);
		buttonsBox.getChildren().add(borderWidthText);
		buttonsBox.getChildren().add(borderWidthSlider);
		buttonsBox.getChildren().add(borderColorPicker);

		Label fillText = new Label("Relleno");
		buttonsBox.getChildren().add(fillText);
		buttonsBox.getChildren().add(fillColorPicker);

		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

		EventHandler<MouseEvent> sliderEvent = mouseEvent -> {
			if(canvasState.containsSelectedFigure(selectedFigure)) {
				canvasState.setBorderWidth(borderWidthSlider.getValue());
				redrawCanvas();
			}
		};
		borderWidthSlider.setOnMouseDragged(sliderEvent);
		borderWidthSlider.setOnMouseClicked(sliderEvent);
		borderColorPicker.setOnAction(event -> selectedFigure.setBorderColor(borderColorPicker.getValue()));
		fillColorPicker.setOnAction(event -> {
			if(canvasState.containsSelectedFigure(selectedFigure)) {
				selectedFigure.setFillColor(fillColorPicker.getValue());
				redrawCanvas();
			}
		});

		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			Drawable newFigure;
			if(startPoint == null){return;}
			if(!selectionButton.isSelected()) { // boton de alguna figura
				clickedButton = (FigureButton) tools.getSelectedToggle();
				if (clickedButton != null) {
					newFigure = clickedButton.createFigure(startPoint, endPoint, fillColorPicker.getValue(), borderColorPicker.getValue(), borderWidthSlider.getValue());
					newFigure.draw(gc);
					canvasState.addFigure(newFigure);
				}
			}else{ // estoy con el boton de seleccionar
				StringBuilder description = new StringBuilder("Se seleccionó: ");
				if (startPoint.equals(endPoint)){ // un solo click
					Drawable last = canvasState.getTheSelectedFigure(endPoint);
					if (last != null){ // si se selecciono una figura
						description.append(last);
					}
				}else { // seleccion multiple
					Set<Drawable> allSelectedFigures = canvasState.getSelectedFigures(startPoint, endPoint);
	//				es probable que este tipo de for-each lo tengamos en
	//				algun metodo private por aca
					for (Drawable figure : allSelectedFigures){
						description.append(figure);
						description.append(", ");
					}
					description.deleteCharAt(description.length() - 1);
				}
			}
			startPoint = null;
			redrawCanvas();
		});
		//
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Drawable figure : canvasState.figures()) {
				if(figure.containsPoint(eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});
		//
		// repite codigo entre mouseMoved y mouseClicked
		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Drawable figure : canvasState.figures()) {
					if(figure.containsPoint(eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected() && selectedFigure != null) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX());
				double diffY = (eventPoint.getY() - startPoint.getY());
				selectedFigure.move(diffX, diffY);
				startPoint = eventPoint;
				redrawCanvas();
			}
		});

		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Drawable figure : canvasState.figures()) {
			if(canvasState.containsSelectedFigure(figure)) {
				gc.setStroke(SELECTED_COLOR);

			} else {
				gc.setStroke(DEFAULT_BORDER_COLOR);
//				figure.setBorderColor(DEFAULT_BORDER_COLOR);
			}
			gc.setLineWidth(figure.getBorderWidth());
//			figure.setBorderWidth(figure.getBorderWidth());
			if(figure.isComplex()) {
				gc.setFill(DEFAULT_FILL_COLOR); //ARREGLAR -> hay que ver como acceder al fillColor
//				figure.setFillColor(DEFAULT_FILL_COLOR);
			}
			figure.draw(gc);
		}
	}


}
