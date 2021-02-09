import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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
    private TableColumn<ClientBase, String> idClient;

    @FXML
    private TableColumn<ClientBase, String> Name;

    private void populateClientBase(ObservableList<ClientBase> clientBaseData) {
        clientBase.setItems(clientBaseData);
    }
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert clientBase != null : "fx:id=\"clientBase\" was not injected: check your FXML file 'ClientBase.fxml'.";
        assert idClient != null : "fx:id=\"idClient\" was not injected: check your FXML file 'ClientBase.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'ClientBase.fxml'.";
        try {

            clientBase.getItems().clear();
            ObservableList<ClientBase> clientBaseData = KlientController.packagesDAO.showClientBase();
            populateClientBase(clientBaseData);

        } catch (SQLException | ClassNotFoundException e) {
            //consoleTextArea.appendText("Error occurred while getting statystyka from DB.\n");
            throw e;
        }
    }
}

