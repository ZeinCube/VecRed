package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    public static Window stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage = primaryStage;
        primaryStage.setTitle("VecRed");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static Window getStage(){
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}