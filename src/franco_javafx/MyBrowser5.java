package franco_javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Paso 5: Agregar un menú a la escena
 */
public class MyBrowser5 extends Application {

    private WebView webView = new WebView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();

        //MenuBar
        MenuBar mainMenu = new MenuBar();

        //Menu Archivo
        Menu file = new Menu("File");

        //MenuItem Actualizar
        MenuItem refreshMenuItem = new MenuItem("Refresh");
        //MenuItem Salir
        MenuItem exitMenuItem = new MenuItem("Exit");

        //Los MenuItem Actualizar y Salir tienen como padre al Menu Archivo
        file.getItems().addAll(refreshMenuItem, exitMenuItem);

        //Menu Ayuda
        Menu help = new Menu("Help");

        //MenuItem Acerca De
        MenuItem aboutMenuItem = new MenuItem("About");

        //El MenuItem Acerca De tiene como padre al Menu Ayuda
        help.getItems().add(aboutMenuItem);

        //Los Menu Archivo y Ayuda tienen como padre a la MenuBar Principal
        mainMenu.getMenus().addAll(file, help);

        TextField textField = new TextField("http://");
        textField.setOnAction(event -> webView.getEngine().load(textField.getText()));

        //Agregamos la MenuBar a la escena
        vBox.getChildren().addAll(mainMenu, textField, webView);

        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
