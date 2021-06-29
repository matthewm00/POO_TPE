package franco_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Paso 4: Agregar un campo WebView y cargar el sitio deseado.
 */
public class MyBrowser4 extends Application {

    //Componente WebView para mostrar sitios web
    private WebView webView = new WebView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();
        TextField textField = new TextField("http://");
        /**
         * Cambiamos el EventHandler para que ante un evento
         * en el campo de texto, se cargue el sitio web ingresado
         */
        textField.setOnAction(event -> webView.getEngine().load(textField.getText()));
        vBox.getChildren().addAll(textField, webView);
        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
