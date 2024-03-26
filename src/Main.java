import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/DashboardForm.fxml")))));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Burger Shop");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/Hamburger.png"))));
        primaryStage.show();
    }
}