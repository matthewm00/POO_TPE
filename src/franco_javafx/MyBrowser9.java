package franco_javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Paso 9: Reutilizar el evento de carga de sitio web en el menú Actualizar
 * y como página de Inicio.
 */
public class MyBrowser9 extends Application {

    //Página de Inicio
    private static final String HOME = "www.google.com.ar";

    private WebView webView = new WebView();

    //El campo de texto empieza con la dirección de la página de inicio
    private TextField textField = new TextField("http://" + HOME);
    //Variable de instancia con el handler
    private EventHandler<ActionEvent> eventHandler = new AddressHandler();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();
        MenuBar mainMenu = new MenuBar();
        Menu file = new Menu("File");
        MenuItem refreshMenuItem = new MenuItem("Refresh");

        //Usamos el handler ante algún evento en el menú de Actualizar
        refreshMenuItem.setOnAction(eventHandler);

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    System.exit(0);
                }
            }
        });
        file.getItems().addAll(refreshMenuItem, exitMenuItem);
        Menu help = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);
        mainMenu.getMenus().addAll(file, help);

        //Usamos el handler ante algún evento en el campo de texto
        textField.setOnAction(eventHandler);

        vBox.getChildren().addAll(mainMenu, textField, webView);

        //Cargamos la página de Inicio al inicio de la escena.
        eventHandler.handle(new ActionEvent());

        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Clase del handler que carga el sitio web.
    private class AddressHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            webView.getEngine().load(textField.getText());
        }

    }

}
