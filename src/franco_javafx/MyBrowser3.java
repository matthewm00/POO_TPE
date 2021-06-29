package franco_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Paso 3: Interactuar con el campo de texto usando EventHandler
 */
public class MyBrowser3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();

        //El textfield empieza con un texto inicial.
        TextField textField = new TextField("http://");

        /**
         * Agregamos un EventHandler para que ante cualquier evento
         * del campo de texto se ejecute el siguiente código
         * Ante algún evento en el campo de texto
         * se imprime en salida estándar el texto del campo.
         */
        textField.setOnAction(event -> System.out.println(textField.getText()));

        vBox.getChildren().add(textField);
        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
