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
import paint.backend.model.Point;

import java.util.Set;

public class PaintPane extends BorderPane {

	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.LIGHTBLUE;
	private static final Color SELECTED_COLOR = Color.RED;
	private	static final double MAX_VALUE_SLIDER = 50;
	private static final double MIN_VALUE_SLIDER = 1;
	private static final double INITIAL_VALUE_SLIDER = 10;

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

	// StatusBar
	private StatusPane statusPane;

	// Boton de figura seleccionada
	private FigureButton clickedFigureButton;


	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;

		selectionButton.setOnAction(event -> clickedFigureButton = null);

		backButton.setOnAction(event -> {
			if(canvasState.hasSelectedFigures()) {
				canvasState.moveToBack();
				redrawCanvas();
			}
		});

		frontButton.setOnAction(event -> {
			if(canvasState.hasSelectedFigures()) {
				canvasState.moveToFront();
				redrawCanvas();
			}
		});

		deleteButton.setOnAction(event-> {
			if(canvasState.hasSelectedFigures()) {
				canvasState.removeSelectedFigures();
				redrawCanvas();
			}
		});

		EventHandler<MouseEvent> sliderEvent = mouseEvent -> {
			if(canvasState.hasSelectedFigures()) {
				canvasState.setSelectedBorderWidth(borderWidthSlider.getValue());
				redrawCanvas();
			}
		};

		borderWidthSlider.setOnMouseDragged(sliderEvent);
		borderWidthSlider.setOnMouseClicked(sliderEvent);

		borderColorPicker.setOnAction(event -> canvasState.setSelectedBorderColor(borderColorPicker.getValue()));

		fillColorPicker.setOnAction(event -> {
			if(canvasState.hasSelectedFigures()) {
				canvasState.setSelectedFillColor(fillColorPicker.getValue());
				redrawCanvas();
			}
		});

		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton,
				ellipseButton, squareButton, lineButton, deleteButton, backButton, frontButton};
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

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if (startPoint == null) {
				return;
			}
			if (!selectionButton.isSelected() && !deleteButton.isSelected() && !backButton.isSelected() && !frontButton.isSelected()) {
				Drawable newFigure;
				clickedFigureButton = (FigureButton) tools.getSelectedToggle();
				if (clickedFigureButton != null) {
//					boolean error = false;
					try{
						newFigure = clickedFigureButton.createFigure(startPoint, endPoint, fillColorPicker.getValue(), borderColorPicker.getValue(), borderWidthSlider.getValue());
						canvasState.addFigure(newFigure);
					} catch (IllegalArgumentException e){
//						error = true;
						statusPane.updateStatus(e.getMessage());
//						statusPane.setStyle("-fx-background-color: #FF0000");
					}
//					if (error){
//						statusPane.setStyle("-fx-background-color: #4EBCF8");
//					}
					clickedFigureButton = null;
				}
			}
			else if(selectionButton.isSelected()) {
				if (!event.isStillSincePress()) {
					// en el boton "Seleccionar"
					StringBuilder description = new StringBuilder("Se seleccionó: ");
					canvasState.setSelectedFigures(startPoint, endPoint);
					if (canvasState.hasSelectedFigures()) {
						canvasState.getSelectedFigures().forEach(figure -> description.append(figure).append(" "));
						statusPane.updateStatus(description.toString());
					} else
						statusPane.updateStatus("Ninguna figura encontrada");
				}
				startPoint = null;
			}
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			StringBuilder label = new StringBuilder();
			boolean found = false;
			for (Drawable figure : canvasState.figures()) {
				if (figure.containsPoint(eventPoint)) {
					found = true;
					label.append(figure).append(" ");
				}
			}
			if (found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(event.isStillSincePress() && selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				canvasState.setTheSelectedFigure(eventPoint);
				if (canvasState.hasSelectedFigures()){
					label.append(canvasState.getTheSelectedFigure());
					statusPane.updateStatus(label.toString());
				} else {
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected() && canvasState.hasSelectedFigures()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX());
				double diffY = (eventPoint.getY() - startPoint.getY());
				canvasState.moveSelectedFigures(diffX, diffY);
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
				gc.setStroke(figure.getBorderColor());
			}
			gc.setLineWidth(figure.getBorderWidth());
			if(figure.isFillable()) {
				gc.setFill(figure.getFillColor());
			}
			figure.draw(gc);
		}
	}
}