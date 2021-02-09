import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientBaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView clientBase;

    @FXML
    private TableColumn<?, ?> idClient;

    @FXML
    private TableColumn<?, ?> Name;

    @FXML
    void initialize() {
        assert clientBase != null : "fx:id=\"clientBase\" was not injected: check your FXML file 'ClientBase.fxml'.";
        assert idClient != null : "fx:id=\"idClient\" was not injected: check your FXML file 'ClientBase.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'ClientBase.fxml'.";

    }
}

