/*
@author CodeChamps0419

Demonstrates a simple JavaFX application. It's deprecated (below code will work on in pre java 8 version). 
Refer. example for knowledge purpose only.
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, JavaFX!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
Output:

H:\Passionate Buddies\Java>java JavaFXDemo

--- (Launches a graphical window with a 300x200 canvas displaying the text "Hello, JavaFX!") ---

*/