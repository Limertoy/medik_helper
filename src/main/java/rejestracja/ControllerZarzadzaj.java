package rejestracja;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import obiekty.Pacjent;
import obiekty.Pracownik;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerZarzadzaj implements Initializable {
    public TableColumn godzinaTable, ponTable, wtoTable, sroTable, czwTable, piaTable, sobTable, niedTable;
    public TableView<ControllerZarzadzaj.Kalen> kalendarz = new TableView<ControllerZarzadzaj.Kalen>();
    public TableView<ListaPracownikow> pracownicy;
    public TableView<ListaPacjentow> pacjenci;
    public TableColumn imie_table, nazwisko_table,imieTable, nazwiskoTable, peselTable;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    Session session = HibernateUtil.getSessionFactory().openSession();

    public class ListaPracownikow{
        public int id;
        public SimpleStringProperty imie_table, nazwisko_table;

        public int getId() {
            return id;
        }
        public int id_rola;

        public ListaPracownikow(int id_rola) {
            this.id_rola = id_rola;
        }

        public int getId_rola() {
            return id_rola;
        }

        public void setId_rola(int id_rola) {
            this.id_rola = id_rola;
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

        public ListaPracownikow(int id, String imie_table, String nazwisko_table, int id_rola) {
            this.id = id;
            this.imie_table = new SimpleStringProperty(imie_table);
            this.nazwisko_table = new SimpleStringProperty(nazwisko_table);
            this.id_rola = id_rola;
        }
    }

    public class ListaPacjentow{
        public int id;
        public SimpleStringProperty imieTable;
        public SimpleStringProperty nazwiskoTable;
        public SimpleStringProperty peselTable;

        public int getId() { return id; }

        public void setId(int id) { this.id = id; }

        public String getImieTable() { return imieTable.get(); }

        public SimpleStringProperty imieTableProperty() { return imieTable; }

        public void setImieTable(String imieTable) { this.imieTable.set(imieTable); }

        public String getNazwiskoTable() { return nazwiskoTable.get(); }

        public SimpleStringProperty nazwiskoTableProperty() { return nazwiskoTable; }

        public void setNazwiskoTable(String nazwiskoTable) { this.nazwiskoTable.set(nazwiskoTable); }

        public String getPeselTable() { return peselTable.get(); }

        public SimpleStringProperty peselTableProperty() { return peselTable; }

        public void setPeselTable(String peselTable) { this.peselTable.set(peselTable); }



        public ListaPacjentow(int id, String imieTable, String nazwiskoTable, String peselTable) {
            this.id = id;
            this.imieTable = new SimpleStringProperty(imieTable);
            this.nazwiskoTable =new SimpleStringProperty(nazwiskoTable);
            this.peselTable =new SimpleStringProperty(peselTable);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query<Pracownik> q = session.createQuery("from Pracownik");
        List<Pracownik> list = q.list();

        ObservableList<ControllerZarzadzaj.ListaPracownikow> data1 = FXCollections.observableArrayList();

        int i = 0;
        for(Pracownik s : list){
            if(s.getRola().getId_rola()== 1){
            data1.add(i, new ControllerZarzadzaj.ListaPracownikow(s.getId_pracownika(), s.getImie_pracownika(), s.getNazwisko_pracownika(),s.getRola().getId_rola()));
            i++;
            }
        }

        imie_table.setCellValueFactory(new PropertyValueFactory("imie_table"));
        nazwisko_table.setCellValueFactory(new PropertyValueFactory("nazwisko_table"));
        pracownicy.getItems().setAll(data1);

        Query<Pacjent> q1 = session.createQuery("from Pacjent");
        List<Pacjent> list1 = q1.list();
        ObservableList<ControllerZarzadzaj.ListaPacjentow> data2 = FXCollections.observableArrayList();
        int j = 0;
        for(Pacjent s : list1){
                data2.add(j, new ControllerZarzadzaj.ListaPacjentow(s.getId_pacjenta(), s.getImie_pacjenta(), s.getNazwisko_pacjenta(), s.getPesel()));
                j++;
            }
        imieTable.setCellValueFactory(new PropertyValueFactory("imieTable"));
        nazwiskoTable.setCellValueFactory(new PropertyValueFactory("nazwiskoTable"));
        peselTable.setCellValueFactory(new PropertyValueFactory("peselTable"));
        pacjenci.getItems().setAll(data2);



        kalendarz.getItems().setAll(this.data);
        kalendarz.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        kalendarz.getSelectionModel().setCellSelectionEnabled(true);
    }

    public class Kalen {
        public String getGodzina() {
            return godzina.get();
        }

        public SimpleStringProperty godzinaProperty() {
            return godzina;
        }

        public void setGodzina(String godzina) {
            this.godzina.set(godzina);
        }


        public String getPon() {
            return pon.get();
        }


        public void setPon(String pon) {
            this.pon.set(pon);
        }

        public String getWto() {
            return wto.get();
        }

        public SimpleStringProperty wtoProperty() {
            return wto;
        }

        public void setWto(String wto) {
            this.wto.set(wto);
        }

        public String getSro() {
            return sro.get();
        }

        public SimpleStringProperty sroProperty() {
            return sro;
        }

        public void setSro(String sro) {
            this.sro.set(sro);
        }

        public String getCzw() {
            return czw.get();
        }

        public SimpleStringProperty czwProperty() {
            return czw;
        }

        public void setCzw(String czw) {
            this.czw.set(czw);
        }

        public String getPia() {
            return pia.get();
        }

        public SimpleStringProperty piaProperty() {
            return pia;
        }

        public void setPia(String pia) {
            this.pia.set(pia);
        }

        public String getSob() {
            return sob.get();
        }

        public SimpleStringProperty sobProperty() {
            return sob;
        }

        public void setSob(String sob) {
            this.sob.set(sob);
        }

        public String getNied() {
            return nied.get();
        }

        public SimpleStringProperty niedProperty() {
            return nied;
        }

        public void setNied(String nied) {
            this.nied.set(nied);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public SimpleStringProperty ponProperty() {
            return pon;
        }

        public int id;
        public SimpleStringProperty godzina;
        public SimpleStringProperty pon;
        public SimpleStringProperty wto;
        public SimpleStringProperty sro;
        public SimpleStringProperty czw;
        public SimpleStringProperty pia;
        public SimpleStringProperty sob;
        public SimpleStringProperty nied;

        public Kalen(int id, String godzina, String pon, String wto, String sro, String czw, String pia, String sob, String nied) {
            this.id = id;
            this.godzina = new SimpleStringProperty(godzina);
            this.pon = new SimpleStringProperty(pon);
            this.wto = new SimpleStringProperty(wto);
            this.sro = new SimpleStringProperty(sro);
            this.czw = new SimpleStringProperty(czw);
            this.pia = new SimpleStringProperty(pia);
            this.sob = new SimpleStringProperty(sob);
            this.nied = new SimpleStringProperty(nied);
        }
    }

    public final ObservableList<ControllerZarzadzaj.Kalen> data = FXCollections.observableArrayList(
            new ControllerZarzadzaj.Kalen(1,"8:00", " ", "Andriy Adamovych", " ", "Paweł Kulpiński", " ", " ", " "),
            new ControllerZarzadzaj.Kalen(2,"8:30", " ", "", "Dominik Filip", "", " ", " ", " "),
            new ControllerZarzadzaj.Kalen(3,"9:00", "Maciej Dukacz", "", "", "", " ", " ", "Agata Szkup")
    );

//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        kalendarz.getItems().setAll(this.data);
//
//        kalendarz.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        kalendarz.getSelectionModel().setCellSelectionEnabled(true);
//    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }


    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/rejestracja/rejestracjaPacjenci.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }


    public void rejestracja(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/rejestracja/rejestracja.fxml").toUri().toURL();
        Parent rejestracjaParent = FXMLLoader.load(url);
        Scene rejestracjaScene = new Scene(rejestracjaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(rejestracjaScene);
        window.show();
    }

}

