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
import java.util.ResourceBundle;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerWizyty implements Initializable {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, zobaczKarte;
    @FXML
    private TableView<ListaWizyt> table;

    @FXML
    private TableColumn nazwiskoTable, imieTable, peselTable, dataTable, godzinaTable;

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
        nazwiskoTable.setCellValueFactory(new PropertyValueFactory("nazwiskoTable"));
        imieTable.setCellValueFactory(new PropertyValueFactory("imieTable"));
        peselTable.setCellValueFactory(new PropertyValueFactory("peselTable"));
        dataTable.setCellValueFactory(new PropertyValueFactory("dataTable"));
        godzinaTable.setCellValueFactory(new PropertyValueFactory("godzinaTable"));
        table.getItems().setAll(this.dane);
    }

    public class ListaWizyt{
        public int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNazwiskoTable() {
            return nazwiskoTable.get();
        }

        public SimpleStringProperty nazwiskoTableProperty() {
            return nazwiskoTable;
        }

        public void setNazwiskoTable(String nazwisko_table) {
            this.nazwiskoTable.set(nazwisko_table);
        }

        public String getImieTable() {
            return imieTable.get();
        }

        public SimpleStringProperty imieTableProperty() {
            return imieTable;
        }

        public void setImieTable(String imie_table) {
            this.imieTable.set(imie_table);
        }

        public String getPeselTable() {
            return peselTable.get();
        }

        public SimpleStringProperty peselTableProperty() {
            return peselTable;
        }

        public void setPeselTable(String pesel_table) {
            this.peselTable.set(pesel_table);
        }

        public String getDataTable() {
            return dataTable.get();
        }

        public SimpleStringProperty dataTableProperty() {
            return dataTable;
        }

        public void setDataTable(String data_table) {
            this.dataTable.set(data_table);
        }

        public String getGodzinaTable() {
            return godzinaTable.get();
        }

        public SimpleStringProperty godzinaTableProperty() {
            return godzinaTable;
        }

        public void setGodzinaTable(String godzina_table) {
            this.godzinaTable.set(godzina_table);
        }

        public SimpleStringProperty nazwiskoTable, imieTable, peselTable, dataTable,godzinaTable;

        public ListaWizyt(int id, String nazwiskoTable, String imieTable, String peselTable, String dataTable, String godzinaTable) {
            this.id = id;
            this.nazwiskoTable = new SimpleStringProperty(nazwiskoTable);
            this.imieTable = new SimpleStringProperty(imieTable);
            this.peselTable = new SimpleStringProperty(peselTable);
            this.dataTable = new SimpleStringProperty(dataTable);
            this.godzinaTable = new SimpleStringProperty(godzinaTable);
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
    public void wybierz(MouseEvent mouseEvent) { zobaczKarte.setVisible(true); }

    public void pokaz(ActionEvent actionEvent) throws IOException{
        Parent pokazParent = FXMLLoader.load(getClass().getResource("kartaPacjenta.fxml"));
        Scene pokazScene = new Scene(pokazParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pokazScene);
        window.show();
    }

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
