import controller.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KlientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button connectButton;

    @FXML
    private Button btnShowPack;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button disconnectButton;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private ImageView imageCilentView;

    @FXML
    private TableView nadwcaTabel;

    @FXML
    private TableColumn<SenderView, String> colNad1;

    @FXML
    private TableColumn<SenderView, String> colNad2;

    @FXML
    private TableColumn<SenderView, String> colNad3;

    @FXML
    private TableColumn<SenderView, String> colNad4;

    @FXML
    private TableColumn<SenderView, String> colNad5;

    @FXML
    private TableColumn<SenderView, String> colNad6;

    @FXML
    private TableColumn<SenderView, String> colNad7;

    @FXML
    private TableColumn<SenderView, String> colNad8;

    @FXML
    private TableView paczkiOdebraneTable;

    @FXML
    private TableColumn<ReceiverView, String> colOdb1;

    @FXML
    private TableColumn<ReceiverView, String> colOdb2;

    @FXML
    private TableColumn<ReceiverView, String> colOdb3;

    @FXML
    private TableColumn<ReceiverView, String> colOdb4;

    @FXML
    private TableColumn<ReceiverView, String> colOdb5;

    @FXML
    private TableColumn<ReceiverView, String> colOdb6;

    @FXML
    private TableColumn<ReceiverView, String> colOdb7;

    @FXML
    private TextField inputAdrOdb;

    @FXML
    private TextField inputOdbiorca;

    @FXML
    private Spinner<String> spinner;

    @FXML
    private TextField inputPaczNad;

    @FXML
    private Button btnNadajPaczke;

    public static DBUtil dbUtil;
    public static PackagesDAO packagesDAO;

    public DBUtil getDbUtil() {
        return dbUtil;
    }

    public PackagesDAO getRacketDAO() {
        return packagesDAO;

    }


    @FXML
    void connectButtonPressed(ActionEvent event) throws SQLException, ClassNotFoundException {

        dbUtil = new DBUtil(userTextField.getText(), passwordTextField.getText(), consoleTextArea);
        packagesDAO = new PackagesDAO(dbUtil, consoleTextArea);

        dbUtil.dbConnect();

        consoleTextArea.appendText("Access granted for user \"" + userTextField.getText() + "\"." + "\n");

        connectButton.setDisable(true);
        disconnectButton.setDisable(false);

    }



    @FXML
    void disconnectButtonPressed(ActionEvent event) throws SQLException {

        dbUtil.dbDisconnect();
        connectButton.setDisable(false);
        disconnectButton.setDisable(true);
        /*
        addRacketButton.setDisable(true);
        selectRacketButton.setDisable(true);
        showRacketsButton.setDisable(true);
        selectRacketNameTextField.setDisable(true);
        selectRacketNameTextField.setText("");
        selectRacketNameTextField.setPromptText("Podaj nazwę");
        racketNameToAddTextField.setDisable(true);
        racketNameToAddTextField.setText("");
        racketNameToAddTextField.setPromptText("Podaj nazwę");
        clientsTable.getItems().clear();
*/
    }

    @FXML
    void onBtnSignIn(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("sign_in.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("Rejestracja");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //spiner config:
    ObservableList<String> sizes = FXCollections.observableArrayList(
            "S","M","XL"
    ) ;
    SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(sizes);

    @FXML
    void onBtnShowPack(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {

            paczkiOdebraneTable.getItems().clear();
            nadwcaTabel.getItems().clear();
            ObservableList<ReceiverView> receiverViewData = packagesDAO.showAllReceiverView();
            populateReceiverView(receiverViewData);
            ObservableList<SenderView> senderData = packagesDAO.showAllSender();
            populateSenderView(senderData);


        } catch (SQLException | ClassNotFoundException e) {
            consoleTextArea.appendText("Error occurred while getting paczki from DB.\n");
            throw e;
        }

    }

    private void populateReceiverView(ObservableList<ReceiverView> receiverViewData) {
        paczkiOdebraneTable.setItems(receiverViewData);

    } private void populateSenderView(ObservableList<SenderView> senderViews) {
        nadwcaTabel.setItems(senderViews);
    }


    @FXML
    void onBtnNadajPaczkę(ActionEvent event) {
        spinner = new Spinner<String>();
        // wartość wyjsciowa spinnera
        valueFactory.setValue("M");
        spinner.setValueFactory(valueFactory);
    }

// todo inserty dla dodawania paczek
// todo inserty nowy użytkownik
// todo odbior paczki widok i update (podaj id paczki i w tedy dodaj date odbioru)

    @FXML
    void initialize() {
        assert userTextField != null : "fx:id=\"userTextField\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert btnShowPack != null : "fx:id=\"btnShowPack\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert btnSignIn != null : "fx:id=\"btnSignIn\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert nadwcaTabel != null : "fx:id=\"nadwcaTabel\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert imageCilentView != null : "fx:id=\"imageCilentView\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad1 != null : "fx:id=\"colNad1\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad2 != null : "fx:id=\"colNad2\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad3 != null : "fx:id=\"colNad3\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad4 != null : "fx:id=\"colNad4\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad5 != null : "fx:id=\"colNad5\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad6 != null : "fx:id=\"colNad6\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad7 != null : "fx:id=\"colNad7\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colNad8 != null : "fx:id=\"colNad8\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb1 != null : "fx:id=\"colOdb1\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb2 != null : "fx:id=\"colOdb2\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb3 != null : "fx:id=\"colOdb3\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb4 != null : "fx:id=\"colOdb4\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb5 != null : "fx:id=\"colOdb5\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb6 != null : "fx:id=\"colOdb6\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert colOdb7 != null : "fx:id=\"colOdb7\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert inputAdrOdb != null : "fx:id=\"inputAdrOdb\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert inputOdbiorca != null : "fx:id=\"inputOdbiorca\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert spinner != null : "fx:id=\"spinner\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert inputPaczNad != null : "fx:id=\"inputPaczNad\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert btnNadajPaczke != null : "fx:id=\"btnNadajPaczke\" was not injected: check your FXML file 'KlientView.fxml'.";
        assert paczkiOdebraneTable != null : "fx:id=\"paczkiOdebraneTable\" was not injected: check your FXML file 'KlientView.fxml'.";

    }
}