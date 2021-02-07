import javafx.beans.property.*;


public class Automat {
    private IntegerProperty idAutomat;
    private StringProperty adres;

    public Automat() {
        idAutomat = new SimpleIntegerProperty();
        adres = new SimpleStringProperty();
    }

    public int getIdAutomat() {
        return idAutomat.get();
    }

    public IntegerProperty idAutomatProperty() {
        return idAutomat;
    }

    public void setIdAutomat(int idAutomat) {
        this.idAutomat.set(idAutomat);
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
}
