import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasa przechowująca dane z bazy dancych odbiorcy. Wykorzystywana do tworzenia podglądu paczek do odbioru.
 */
public class ReceiverView {

    private IntegerProperty idPackages;
    private StringProperty send_date;
    private StringProperty data_end;
    private IntegerProperty idClientNad;
    private StringProperty adresNadawcy;
    private IntegerProperty idAutomatOdb;
    private StringProperty adresAutomatuOdbiorczego;

    public ReceiverView() {
        idPackages = new SimpleIntegerProperty();
        send_date = new SimpleStringProperty();
        data_end = new SimpleStringProperty();
        idClientNad = new SimpleIntegerProperty();
        adresNadawcy = new SimpleStringProperty();
        idAutomatOdb = new SimpleIntegerProperty();
        adresAutomatuOdbiorczego = new SimpleStringProperty();

    }

    public int getIdPackages() {
        return idPackages.get();
    }

    public IntegerProperty idPackagesProperty() {
        return idPackages;
    }

    public void setIdPackages(int idPackages) {
        this.idPackages.set(idPackages);
    }

    public String getSend_date() {
        return send_date.get();
    }

    public StringProperty send_dateProperty() {
        return send_date;
    }

    public void setSend_date(String send_date) {
        this.send_date.set(send_date);
    }

    public String getData_end() {
        return data_end.get();
    }

    public StringProperty data_endProperty() {
        return data_end;
    }

    public void setData_end(String data_end) {
        this.data_end.set(data_end);
    }

    public int getIdClientNad() {
        return idClientNad.get();
    }

    public IntegerProperty idClientNadProperty() {
        return idClientNad;
    }

    public void setIdClientNad(int idClientNad) {
        this.idClientNad.set(idClientNad);
    }

    public String getAdresNadawcy() {
        return adresNadawcy.get();
    }

    public StringProperty adresNadawcyProperty() {
        return adresNadawcy;
    }

    public void setAdresNadawcy(String adresNadawcy) {
        this.adresNadawcy.set(adresNadawcy);
    }

    public int getIdAutomatOdb() {
        return idAutomatOdb.get();
    }

    public IntegerProperty idAutomatOdbProperty() {
        return idAutomatOdb;
    }

    public void setIdAutomatOdb(int idAutomatOdb) {
        this.idAutomatOdb.set(idAutomatOdb);
    }

    public String getAdresAutomatuOdbiorczego() {
        return adresAutomatuOdbiorczego.get();
    }

    public StringProperty adresAutomatuOdbiorczegoProperty() {
        return adresAutomatuOdbiorczego;
    }

    public void setAdresAutomatuOdbiorczego(String adresAutomatuOdbiorczego) {
        this.adresAutomatuOdbiorczego.set(adresAutomatuOdbiorczego);
    }
}
