import java.io.IOException;
    import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.stage.Stage;

public class startController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button idKlient;

        @FXML
        private Button idFirma;

        @FXML
        void onFirmaButton(ActionEvent event) {
            Parent root;
            try {

                root = FXMLLoader.load(getClass().getClassLoader().getResource("FirmaView.fxml"), resources);
                Stage stage = new Stage();
                stage.setTitle("Firma");
                stage.setScene(new Scene(root, 1000, 600));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }



        @FXML
        void onKlientButton(ActionEvent event) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("dbFX.fxml"), resources);
                Stage stage = new Stage();
                stage.setTitle("Klient");
                stage.setScene(new Scene(root, 800, 600));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        @FXML
        void initialize() {
            assert idKlient != null : "fx:id=\"idKlient\" was not injected: check your FXML file 'start.fxml'.";
            assert idFirma != null : "fx:id=\"idFirma\" was not injected: check your FXML file 'start.fxml'.";

        }
    }

