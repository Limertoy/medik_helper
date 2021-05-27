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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import obiekty.Pacjent;
import obiekty.Sloty;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.ControllerLogin;
import sample.HibernateUtil;

public class ControllerWizyty extends ControllerLogin implements Initializable {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, zobaczKarte;
    @FXML
    private TableView<ListaWizyt> table;

    @FXML
    private TableColumn nazwiskoTable, imieTable, peselTable, dataTable, godzinaTable;

    @FXML
    DatePicker datePicker = new DatePicker(LocalDate.now());

    Session session = HibernateUtil.getSessionFactory().openSession();

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void reloadDate(){
        LocalDate localDate = datePicker.getValue();
        Query q = session.createQuery("from Sloty where data=:data")
                .setParameter("data", localDate);
        List<Sloty> list1 = q.list();

        ObservableList<ListaWizyt> data1 = FXCollections.observableArrayList();

        int i = 0;
        for(Sloty s : list1){
            if(s.getPracownik().getId_pracownika() == id_sesji && s.getPacjent() != null) {
                data1.add(i, new ListaWizyt(s.getId_slota(), s.getPacjent().getNazwisko_pacjenta(), s.getPacjent().getImie_pacjenta(), s.getPacjent().getPesel(), s.getGodzina(), s.getData()));
                i++;
            }
        }
        nazwiskoTable.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        imieTable.setCellValueFactory(new PropertyValueFactory("imie"));
        peselTable.setCellValueFactory(new PropertyValueFactory("pesel"));
        dataTable.setCellValueFactory(new PropertyValueFactory("date"));
        godzinaTable.setCellValueFactory(new PropertyValueFactory("godzina"));
        table.getItems().setAll(data1);
    }

    public class ListaWizyt{
        public int id;
        public SimpleStringProperty nazwisko, imie, pesel, godzina;
        public LocalDate date;

        public ListaWizyt(int id, String nazwisko, String imie, String pesel, String godzina, LocalDate date) {
            this.id = id;
            this.nazwisko = new SimpleStringProperty(nazwisko);
            this.imie = new SimpleStringProperty(imie);
            this.pesel = new SimpleStringProperty(pesel);
            this.godzina = new SimpleStringProperty(godzina);
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNazwisko() {
            return nazwisko.get();
        }

        public SimpleStringProperty nazwiskoProperty() {
            return nazwisko;
        }

        public void setNazwisko(String nazwisko) {
            this.nazwisko.set(nazwisko);
        }

        public String getImie() {
            return imie.get();
        }

        public SimpleStringProperty imieProperty() {
            return imie;
        }

        public void setImie(String imie) {
            this.imie.set(imie);
        }

        public String getPesel() {
            return pesel.get();
        }

        public SimpleStringProperty peselProperty() {
            return pesel;
        }

        public void setPesel(String pesel) {
            this.pesel.set(pesel);
        }

        public String getGodzina() {
            return godzina.get();
        }

        public SimpleStringProperty godzinaProperty() {
            return godzina;
        }

        public void setGodzina(String godzina) {
            this.godzina.set(godzina);
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }
    }


    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wybierz(MouseEvent mouseEvent) { zobaczKarte.setVisible(true); }

    public void pokaz(ActionEvent actionEvent) throws IOException{
        URL url = getClass().getClassLoader().getResource("lekarz/kartaPacjenta.fxml");
        Parent pokazParent = FXMLLoader.load(url);
        Scene pokazScene = new Scene(pokazParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pokazScene);
        window.show();
    }

    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        URL url = getClass().getClassLoader().getResource("sample.fxml");
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }
    public void kalendarz(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("lekarz/kalendarz.fxml");
        Parent kalendarzParent = FXMLLoader.load(url);
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }
    public void pacjenci(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("lekarz/pacjenci.fxml");
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }
    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("lekarz/kartaPacjenta.fxml");
        Parent kartaPacjentaParent = FXMLLoader.load(url);
        Scene kartaPacjentaScene = new Scene(kartaPacjentaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaScene);
        window.show();
    }

    public void zmianaDaty(ActionEvent actionEvent) {
        reloadDate();
    }
}
