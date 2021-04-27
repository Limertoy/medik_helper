package obiekty;

import javafx.beans.property.SimpleStringProperty;

public class ListaPracownikow {
    public int id;
    public SimpleStringProperty imie_table, nazwisko_table, email_table, rola_table;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie_table() {
        return imie_table.get();
    }

    public SimpleStringProperty imie_tableProperty() {
        return imie_table;
    }

    public void setImie_table(String imie_table) {
        this.imie_table.set(imie_table);
    }

    public String getNazwisko_table() {
        return nazwisko_table.get();
    }

    public SimpleStringProperty nazwisko_tableProperty() {
        return nazwisko_table;
    }

    public void setNazwisko_table(String nazwisko_table) {
        this.nazwisko_table.set(nazwisko_table);
    }

    public String getEmail_table() {
        return email_table.get();
    }

    public SimpleStringProperty email_tableProperty() {
        return email_table;
    }

    public void setEmail_table(String email_table) {
        this.email_table.set(email_table);
    }

    public String getRola_table() {
        return rola_table.get();
    }

    public SimpleStringProperty rola_tableProperty() {
        return rola_table;
    }

    public void setRola_table(String rola_table) {
        this.rola_table.set(rola_table);
    }

    public ListaPracownikow(String imie_table, String nazwisko_table, String email_table, String rola_table) {
        this.imie_table = new SimpleStringProperty(imie_table);
        this.nazwisko_table = new SimpleStringProperty(nazwisko_table);
        this.email_table = new SimpleStringProperty(email_table);
        this.rola_table = new SimpleStringProperty(rola_table);
    }
}
