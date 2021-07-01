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
import paint.backend.button.*;
import paint.backend.model.Figure;
import paint.backend.model.Point;

public class PaintPane extends BorderPane {

	private static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	private static final Color DEFAULT_FILL_COLOR = Color.YELLOW;
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

	// Dibujar una figura
	private Point startPoint;

	// Seleccionar una figura
	private Figure selectedFigure;

	// StatusBar
	private StatusPane statusPane;

	// Boton clickeado
	private FigureButton clickedButton;


	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;

		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, ellipseButton, squareButton, lineButton, deleteButton};
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
		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

		Label borderWidthText = new Label("Borde");
		borderWidthSlider.setShowTickLabels(true);
		borderWidthSlider.setShowTickMarks(true);
		buttonsBox.getChildren().add(borderWidthText);
		buttonsBox.getChildren().add(borderWidthSlider);
		buttonsBox.getChildren().add(borderColorPicker);

		Label fillText = new Label("Relleno");
		buttonsBox.getChildren().add(fillText);
		buttonsBox.getChildren().add(fillColorPicker);




		EventHandler<MouseEvent> sliderEvent = mouseEvent -> {
			if(canvasState.containsSelectedFigure(selectedFigure)) {
				canvasState.setBorderWidth(borderWidthSlider.getValue());
				redrawCanvas();
			}
		};
		borderWidthSlider.setOnMouseDragged(sliderEvent);
		borderWidthSlider.setOnMouseClicked(sliderEvent);

		deleteButton.setOnAction(event -> {

		});


		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) return ;
			Figure newFigure = null;
			if(!selectionButton.isSelected()) { // boton de alguna figura
				clickedButton = (FigureButton) tools.getSelectedToggle();
				if (clickedButton != null) {
					newFigure = clickedButton.createFigure(startPoint, endPoint, (Color)fillColorPicker.getValue(), (Color)borderColorPicker.getValue(), borderWidthSlider.getValue());
					newFigure.draw(gc);
					if (newFigure != null)  canvasState.addFigure(newFigure);
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
			for(Figure figure : canvasState.figures()) {
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
				for (Figure figure : canvasState.figures()) {
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
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				if(selectedFigure.containsPoint(eventPoint)) {
					selectedFigure.move(diffX, diffY);
				}
				redrawCanvas();
			}
		});

		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {
			if(figure == selectedFigure) {
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(Color.BLACK);
			}
			gc.setFill(Color.YELLOW);
			figure.draw(gc);
//			if(figure instanceof Rectangle) {
//				Rectangle rectangle = (Rectangle) figure;
//				gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
//						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()),
//						Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
//				gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
//						Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()),
//						Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
//			} else if(figure instanceof Circle) {
//				Circle circle = (Circle) figure;
//				double diameter = circle.getRadius() * 2;
//				gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(),
//						circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
//				gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(),
//						circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
//			}
		}
	}


}
