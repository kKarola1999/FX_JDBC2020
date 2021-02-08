import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class UserViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> colNad1;

    @FXML
    private TableColumn<?, ?> colNad2;

    @FXML
    private TableColumn<?, ?> colNad3;

    @FXML
    private TableColumn<?, ?> colNad4;

    @FXML
    private TableColumn<?, ?> colNad5;

    @FXML
    private TableColumn<?, ?> colNad6;

    @FXML
    private TableColumn<?, ?> colNad7;

    @FXML
    private TableColumn<?, ?> colNad8;

    @FXML
    private TableColumn<?, ?> colOdb1;

    @FXML
    private TableColumn<?, ?> colOdb2;

    @FXML
    private TableColumn<?, ?> colOdb3;

    @FXML
    private TableColumn<?, ?> colOdb4;

    @FXML
    private TableColumn<?, ?> colOdb5;

    @FXML
    private TableColumn<?, ?> colOdb6;

    @FXML
    private TableColumn<?, ?> colOdb7;

    @FXML
    private TextField inputAdrOdb;

    @FXML
    private TextField inputOdbiorca;

    @FXML
    private Spinner spinner;

    @FXML
    private TextField inputPaczNad;

    @FXML
    private Button btnNadajPaczke;

    @FXML
    void onBtnNadajPaczkę(ActionEvent event) {
    spinner = new Spinner<String>();
    // wartość wyjsciowa spinnera
    valueFactory.setValue("M");
    spinner.setValueFactory(valueFactory);


    }
    //spiner config:
    ObservableList <String> sizes = FXCollections.observableArrayList(
       "S","M","XL"
    ) ;
    SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(sizes);

    @FXML
    void initialize() {
        assert colNad1 != null : "fx:id=\"colNad1\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colNad2 != null : "fx:id=\"colNad2\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colNad3 != null : "fx:id=\"colNad3\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colNad4 != null : "fx:id=\"colNad4\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colNad5 != null : "fx:id=\"colNad5\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colNad6 != null : "fx:id=\"colNad6\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colNad7 != null : "fx:id=\"colNad7\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colNad8 != null : "fx:id=\"colNad8\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colOdb1 != null : "fx:id=\"colOdb1\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colOdb2 != null : "fx:id=\"colOdb2\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colOdb3 != null : "fx:id=\"colOdb3\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colOdb4 != null : "fx:id=\"colOdb4\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colOdb5 != null : "fx:id=\"colOdb5\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colOdb6 != null : "fx:id=\"colOdb6\" was not injected: check your FXML file 'user_view.fxml'.";
        assert colOdb7 != null : "fx:id=\"colOdb7\" was not injected: check your FXML file 'user_view.fxml'.";
        assert inputAdrOdb != null : "fx:id=\"inputAdrOdb\" was not injected: check your FXML file 'user_view.fxml'.";
        assert inputOdbiorca != null : "fx:id=\"inputOdbiorca\" was not injected: check your FXML file 'user_view.fxml'.";
        assert spinner != null : "fx:id=\"spinner\" was not injected: check your FXML file 'user_view.fxml'.";
        assert inputPaczNad != null : "fx:id=\"inputPaczNad\" was not injected: check your FXML file 'user_view.fxml'.";
        assert btnNadajPaczke != null : "fx:id=\"btnNadajPaczke\" was not injected: check your FXML file 'user_view.fxml'.";

    }
}
