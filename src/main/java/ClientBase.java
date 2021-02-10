import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasa przechowująca dane dotycvzące doaddnych odbiorców.
 */
public class ClientBase {

    private IntegerProperty idClient;
    private StringProperty ImieNazwisko;

    public int getIdClient() {
        return idClient.get();
    }

    public IntegerProperty idClientProperty() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient.set(idClient);
    }

    public String getImieNazwisko() {
        return ImieNazwisko.get();
    }

    public StringProperty imieNazwiskoProperty() {
        return ImieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.ImieNazwisko.set(imieNazwisko);
    }

    public ClientBase() {
        idClient = new SimpleIntegerProperty();
        ImieNazwisko = new SimpleStringProperty();

    }
}
