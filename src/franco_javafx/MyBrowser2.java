package franco_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Paso 2: Agregar un campo de texto a la escena
 */
public class MyBrowser2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();

        //Creamos un campo de texto
        TextField textField = new TextField();

        //Agregamos el campo de texto al layout
        vBox.getChildren().add(textField);

        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
