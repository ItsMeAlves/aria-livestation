package sample;

import com.home.module.InputDevice;
import com.home.module.OutputDevice;
import com.home.util.ViewLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = ViewLoader.load("application.home");
        primaryStage.setTitle("Aria Livestation");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        InputDevice input = new InputDevice();
        OutputDevice output = new OutputDevice();

        input.pipeTo(output);
        input.start();
        output.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
