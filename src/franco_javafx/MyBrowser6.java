package franco_javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Paso 6: Implementar la acción de Exit con un EventHandler
 */
public class MyBrowser6 extends Application {

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
        //EventHandler para el menú Salir
        exitMenuItem.setOnAction(event -> Platform.exit());
        file.getItems().addAll(refreshMenuItem, exitMenuItem);
        Menu help = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
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
