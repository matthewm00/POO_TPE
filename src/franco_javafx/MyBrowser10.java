package franco_javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Paso 10: Barra de progreso que indique la carga del sitio web.
 */
public class MyBrowser10 extends Application {

    private WebView webView = new WebView();
    private TextField textField = new TextField("http://" + HOME);
    private EventHandler<ActionEvent> eventHandler = new AddressHandler();
    private static final String HOME = "www.google.com.ar";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();
        MenuBar mainMenu = new MenuBar();
        Menu file = new Menu("File");
        MenuItem refreshMenuItem = new MenuItem("Refresh");
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
                    Platform.exit();
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
        textField.setOnAction(eventHandler);

        //Creamos la barra de progreso
        ProgressBar progressBar = new ProgressBar();
        //Vinculamos el progreso de la barra con el progreso del Worker que carga el sitio web
        progressBar.progressProperty().bind(webView.getEngine().getLoadWorker().progressProperty());
        //Agregamos la barra de progreso a la escena
        vBox.getChildren().addAll(mainMenu, textField, webView, progressBar);

        eventHandler.handle(new ActionEvent());
        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class AddressHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            webView.getEngine().load(textField.getText());
        }

    }

}
