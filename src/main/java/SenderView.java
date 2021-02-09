import javafx.beans.property.*;

import javafx.beans.property.*;

public class SenderView {
    private IntegerProperty idPaczki;
    private StringProperty dataNadania;
    private StringProperty dataOdebrania;
    private IntegerProperty idAutomatuNadawczego;
    private StringProperty adresNadania;
    private IntegerProperty odbiorca;
    private StringProperty adresOdbiorcy;
    private IntegerProperty IdAutomatuOdbiorczego;
    private StringProperty adresOdbioru;


    public SenderView() {
        idPaczki = new SimpleIntegerProperty();
        dataNadania = new SimpleStringProperty();
        dataOdebrania = new SimpleStringProperty();
        idAutomatuNadawczego = new SimpleIntegerProperty();
        adresNadania = new SimpleStringProperty();
        odbiorca = new SimpleIntegerProperty();
        adresOdbiorcy = new SimpleStringProperty();
        IdAutomatuOdbiorczego = new SimpleIntegerProperty();
        adresOdbioru = new SimpleStringProperty();
    }

    public void setIdPaczki(int idPaczki) {
        this.idPaczki.set(idPaczki);
    }

    public int getIdPaczki() {
        return idPaczki.get();
    }

    public IntegerProperty idPaczkiProperty() {
        return idPaczki;
    }

    public String getDataNadania() {
        return dataNadania.get();
    }

    public StringProperty dataNadaniaProperty() {
        return dataNadania;
    }

    public void setDataNadania(String dataNadania) {
        this.dataNadania.set(dataNadania);
    }

    public String getDataOdebrania() {
        return dataOdebrania.get();
    }

    public StringProperty dataOdebraniaProperty() {
        return dataOdebrania;
    }

    public void setDataOdebrania(String dataOdebrania) {
        this.dataOdebrania.set(dataOdebrania);
    }

    public int getIdAutomatuNadawczego() {
        return idAutomatuNadawczego.get();
    }

    public IntegerProperty idAutomatuNadawczegoProperty() {
        return idAutomatuNadawczego;
    }

    public void setIdAutomatuNadawczego(int idAutomatuNadawczego) {
        this.idAutomatuNadawczego.set(idAutomatuNadawczego);
    }

    public String getAdresNadania() {
        return adresNadania.get();
    }

    public StringProperty adresNadaniaProperty() {
        return adresNadania;
    }

    public void setAdresNadania(String adresNadania) {
        this.adresNadania.set(adresNadania);
    }

    public int getOdbiorca() {
        return odbiorca.get();
    }

    public IntegerProperty odbiorcaProperty() {
        return odbiorca;
    }

    public void setOdbiorca(int odbiorca) {
        this.odbiorca.set(odbiorca);
    }

    public String getAdresOdbiorcy() {
        return adresOdbiorcy.get();
    }

    public StringProperty adresOdbiorcyProperty() {
        return adresOdbiorcy;
    }

    public void setAdresOdbiorcy(String adresOdbiorcy) {
        this.adresOdbiorcy.set(adresOdbiorcy);
    }

    public Integer getIdAutomatuOdbiorczego() {
        return IdAutomatuOdbiorczego.get();
    }

    public IntegerProperty idAutomatuOdbiorczegoProperty() {
        return IdAutomatuOdbiorczego;
    }

    public void setIdAutomatuOdbiorczego(int idAutomatuOdbiorczego) {
        this.IdAutomatuOdbiorczego.set(idAutomatuOdbiorczego);
    }

    public String getAdresOdbioru() {
        return adresOdbioru.get();
    }

    public StringProperty adresOdbioruProperty() {
        return adresOdbioru;
    }

    public void setAdresOdbioru(String adresOdbioru) {
        this.adresOdbioru.set(adresOdbioru);
    }
}
