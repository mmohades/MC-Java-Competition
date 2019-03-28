package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/MainView.fxml"));

            primaryStage.setTitle("MC JAVA Competition");

            Scene scene = new Scene(root, 640, 340);
            scene.getStylesheets().add("MyStyle.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}