package lekarz;

import javafx.beans.Observable;
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
import java.util.ResourceBundle;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerWizyty implements Initializable {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    @FXML
    private TableView<ListaWizyt> table;

    @FXML
    private TableColumn nazwisko_table, imie_table, pesel_table, data_table,godzina_table;

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
        nazwisko_table.setCellValueFactory(new PropertyValueFactory("nazwisko"));
        imie_table.setCellValueFactory(new PropertyValueFactory("imie"));
        pesel_table.setCellValueFactory(new PropertyValueFactory("pesel"));
        data_table.setCellValueFactory(new PropertyValueFactory("data"));
        godzina_table.setCellValueFactory(new PropertyValueFactory("godzinatable"));
        table.getItems().setAll(this.dane);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().setCellSelectionEnabled(true);
    }

    public class ListaWizyt{
        public int id;

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

        public String getData_table() {
            return data_table.get();
        }

        public SimpleStringProperty data_tableProperty() {
            return data_table;
        }

        public void setData_table(String data_table) {
            this.data_table.set(data_table);
        }

        public String getGodzina_table() {
            return godzina_table.get();
        }

        public SimpleStringProperty godzina_tableProperty() {
            return godzina_table;
        }

        public void setGodzina_table(String godzina_table) {
            this.godzina_table.set(godzina_table);
        }

        public SimpleStringProperty nazwisko_table, imie_table, pesel_table, data_table,godzina_table;

        public ListaWizyt(int id, String nazwisko_table, String imie_table, String pesel_table, String data_table, String godzina_table) {
            this.id = id;
            this.nazwisko_table = new SimpleStringProperty(nazwisko_table);
            this.imie_table = new SimpleStringProperty(imie_table);
            this.pesel_table = new SimpleStringProperty(pesel_table);
            this.data_table = new SimpleStringProperty(data_table);
            this.godzina_table = new SimpleStringProperty(godzina_table);
        }
    }

    public final ObservableList<ListaWizyt> dane = FXCollections.observableArrayList(
            new ListaWizyt(1,"Adamovych", "Andriy", "482748274", "03.05.2021", "6:00"),
            new ListaWizyt(2,"Dukacz", "Maciej", "482654322", "03.05.2021", "6:30"),
            new ListaWizyt(3,"Filip", "Dominik", "953857394", "03.05.2021", "7:00"),
            new ListaWizyt(4,"Szkup", "Agata", "593759374", "03.05.2021", "7:30"),
            new ListaWizyt(5,"Kulpiński", "Paweł", "395785638", "03.05.2021", "8:00")

    );

    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }
    public void kalendarz(ActionEvent actionEvent) throws IOException {
        Parent kalendarzParent = FXMLLoader.load(getClass().getResource("kalendarz.fxml"));
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }
    public void pacjenci(ActionEvent actionEvent) throws IOException {
        Parent pacjenciParent = FXMLLoader.load(getClass().getResource("pacjenci.fxml"));
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }
    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {
        Parent kartaPacjentaParent = FXMLLoader.load(getClass().getResource("kartaPacjenta.fxml"));
        Scene kartaPacjentaScene = new Scene(kartaPacjentaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaScene);
        window.show();
    }
}
