package franco_javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Paso 8: Agregar un popup de confirmación a la acción de Exit.
 */
public class MyBrowser8 extends Application {

    private WebView webView = new WebView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();
        MenuBar mainMenu = new MenuBar();
        Menu file = new Menu("File");
        MenuItem refreshMenuItem = new MenuItem("Refresh");
        MenuItem exitMenuItem = new MenuItem("Exit");
        //Alerta de Confirmación
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
        TextField textField = new TextField("http://");
        textField.setOnAction(event -> webView.getEngine().load(textField.getText()));
        vBox.getChildren().addAll(mainMenu, textField, webView);
        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
