package lekarz;

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

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import obiekty.Pacjent;
import org.hibernate.Session;
import sample.HibernateUtil;

import org.hibernate.query.Query;

public class ControllerPacjenci implements Initializable {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, zobaczKarte;
    @FXML
    private TableView<ListaPacjentow> table;
    @FXML
    private TableColumn nazwisko_table, imie_table, pesel_table, ulica_table, miejscowosc_table;

    Session session = HibernateUtil.getSessionFactory().openSession();

    public void wybierz(MouseEvent mouseEvent) { zobaczKarte.setVisible(true); }

    public void pokaz(ActionEvent actionEvent) throws IOException{
        Parent pokazParent = FXMLLoader.load(getClass().getResource("kartaPacjenta.fxml"));
        Scene pokazScene = new Scene(pokazParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pokazScene);
        window.show();
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        //this line gets stage info
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void wizyty(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/wizyty.fxml").toUri().toURL();
        Parent wizytyParent = FXMLLoader.load(url);
        Scene wizytyScene = new Scene(wizytyParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(wizytyScene);
        window.show();
    }
    public void kalendarz(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/kalendarz.fxml").toUri().toURL();
        Parent kalendarzParent = FXMLLoader.load(url);
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }
    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/kartaPacjenta.fxml").toUri().toURL();
        Parent kartaPacjentaParent = FXMLLoader.load(url);
        Scene kartaPacjentaScene = new Scene(kartaPacjentaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query<Pacjent> q = session.createQuery("from Pacjent");
        List<Pacjent> list = q.list();

        ObservableList<ListaPacjentow> data1 = FXCollections.observableArrayList();

        int i = 0;
        for(Pacjent s : list){
            data1.add(i, new ListaPacjentow(s.getId_pacjenta(), s.getNazwisko_pacjenta(), s.getImie_pacjenta(), s.getPesel(), s.getAdres(), s.getMiejscowosc()));
            i++;
        }
        nazwisko_table.setCellValueFactory(new PropertyValueFactory("nazwisko_table"));
        imie_table.setCellValueFactory(new PropertyValueFactory("imie_table"));
        pesel_table.setCellValueFactory(new PropertyValueFactory("pesel_table"));
        ulica_table.setCellValueFactory(new PropertyValueFactory("ulica_table"));
        miejscowosc_table.setCellValueFactory(new PropertyValueFactory("miejscowosc_table"));

        table.getItems().setAll(data1);
    }

    public class ListaPacjentow{
        public int id;
        public SimpleStringProperty nazwisko_table, imie_table, pesel_table, ulica_table, miejscowosc_table;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getImie_table() {
            return imie_table.get();
        }

        public SimpleStringProperty imie_tableProperty() {
            return imie_table;
        }

        public void setImie_table(String imie_table) {
            this.imie_table.set(imie_table);
        }

        public String getPesel_table() {
            return pesel_table.get();
        }

        public SimpleStringProperty pesel_tableProperty() {
            return pesel_table;
        }

        public void setPesel_table(String pesel_table) {
            this.pesel_table.set(pesel_table);
        }

        public String getUlica_table() {
            return ulica_table.get();
        }

        public SimpleStringProperty ulica_tableProperty() {
            return ulica_table;
        }

        public void setUlica_table(String ulica_table) {
            this.ulica_table.set(ulica_table);
        }

        public String getMiejscowosc_table() {
            return miejscowosc_table.get();
        }

        public SimpleStringProperty miejscowosc_tableProperty() {
            return miejscowosc_table;
        }

        public void setMiejscowosc_table(String miejscowosc_table) {
            this.miejscowosc_table.set(miejscowosc_table);
        }

        public ListaPacjentow(int id, String nazwisko_table, String imie_table, String pesel_table, String ulica_table, String miejscowosc_table) {
            this.id = id;
            this.nazwisko_table = new SimpleStringProperty(nazwisko_table);
            this.imie_table = new SimpleStringProperty(imie_table);
            this.pesel_table = new SimpleStringProperty(pesel_table);
            this.ulica_table = new SimpleStringProperty(ulica_table);
            this.miejscowosc_table = new SimpleStringProperty(miejscowosc_table);
        }
    }

}
