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

            Scene scene = new Scene(root, 660, 362);
            scene.getStylesheets().add("MyStyle.css");
            primaryStage.setScene(scene);

            limitStageSize(primaryStage);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static void limitStageSize(Stage stage) {


        stage.setMinHeight(360);
        stage.setMinWidth(620);

        stage.setMaxHeight(500);
        stage.setMaxWidth(800);
    }

}