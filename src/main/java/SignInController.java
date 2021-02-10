import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idInput;

    @FXML
    private TextField ImieInput;

    @FXML
    private TextField AdresInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField nrKontInput;

    @FXML
    private Button signInBtn;

    @FXML
    private ImageView imageSignInView;


    /**
     * metoda służaca do dodawania nowego odbiorcy. Pobiera teskt z pól tekstowych i dodaje do bazydanych.
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @FXML
    void onBtnSingIn(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            KlientController.packagesDAO.insertClient(idInput.getText(),ImieInput.getText(),AdresInput.getText(),emailInput.getText(),nrKontInput.getText());


        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }

    }

    @FXML
    void initialize() {
        assert idInput != null : "fx:id=\"idInput\" was not injected: check your FXML file 'sign_in.fxml'.";
        assert ImieInput != null : "fx:id=\"ImieInput\" was not injected: check your FXML file 'sign_in.fxml'.";
        assert AdresInput != null : "fx:id=\"AdresInput\" was not injected: check your FXML file 'sign_in.fxml'.";
        assert emailInput != null : "fx:id=\"emailInput\" was not injected: check your FXML file 'sign_in.fxml'.";
        assert nrKontInput != null : "fx:id=\"nrKontInput\" was not injected: check your FXML file 'sign_in.fxml'.";
        assert signInBtn != null : "fx:id=\"signInBtn\" was not injected: check your FXML file 'sign_in.fxml'.";
        assert imageSignInView != null : "fx:id=\"imageSignInView\" was not injected: check your FXML file 'sign_in.fxml'.";

    }
}

