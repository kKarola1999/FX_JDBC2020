import javafx.beans.property.*;

/**
 * Klasa przechowyująca dane dotycznace wysłąnych paczek określonej daty, powiązanej z automatem.
 * Wykorzystywan do przechowywania danych z bazy danych i tworzenia podglądów w aplikacji
 */
public class Statystyka {


        private IntegerProperty idAutomatNad;
        private StringProperty send_date;
        private DoubleProperty zysk_paczki;

        public Statystyka() {
            idAutomatNad = new SimpleIntegerProperty();
            send_date = new SimpleStringProperty();
            zysk_paczki=new SimpleDoubleProperty();
        }

    public int getIdAutomatNad() {
        return idAutomatNad.get();
    }

    public IntegerProperty idAutomatNadProperty() {
        return idAutomatNad;
    }

    public void setIdAutomatNad(int idAutomatNad) {
        this.idAutomatNad.set(idAutomatNad);
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

    public double getZysk_paczki() {
        return zysk_paczki.get();
    }

    public DoubleProperty zysk_paczkiProperty() {
        return zysk_paczki;
    }

    public void setZysk_paczki(double zysk_paczki) {
        this.zysk_paczki.set(zysk_paczki);
    }
}
