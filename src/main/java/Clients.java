import javafx.beans.property.*;

public class Clients {

    private IntegerProperty idClient;
    private StringProperty ImieNazwisko;
    private StringProperty adres;
    private IntegerProperty mobile_number;
    private StringProperty email;

    public Clients() {
        idClient = new SimpleIntegerProperty();
        ImieNazwisko = new SimpleStringProperty();
        adres = new SimpleStringProperty();
        mobile_number = new SimpleIntegerProperty();
        email = new SimpleStringProperty();
    }

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

    public String getAdres() {
        return adres.get();
    }

    public StringProperty adresProperty() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres.set(adres);
    }

    public int getMobile_number() {
        return mobile_number.get();
    }

    public IntegerProperty mobile_numberProperty() {
        return mobile_number;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number.set(mobile_number);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
