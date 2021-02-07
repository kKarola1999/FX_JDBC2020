import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RacketApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {

            Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
            Scene scene = new Scene(root, 500,300 );
            stage.setScene(scene);
            stage.show();
        }

    public static void main(String[] args) {
        launch();
    }
}
 // todo widok odbiorcy dla klienta-select*from widok odbiorcy where idklienta=idklienta, ktory sie zalogowal
//todo widok nadawcy dla klienta-select*from widok odbiorcy where idklienta=idklienta, ktory sie zalogowal
// todo podglad paczek odebranych, nadanych-widok paczki-select*from widok_paczek where status=send and pickup
