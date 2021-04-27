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
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.HibernateUtil;


public class ControllerKalendarz implements Initializable {
    public TableColumn godzinaTable, ponTable, wtoTable, sroTable, czwTable, piaTable, sobTable, niedTable;
    public TableView<Kalen> kalendarz = new TableView<Kalen>();
    @FXML
    Button buttonLogin, exit_button, minimalize_button, edytujButton, pracaDzienButton;
    @FXML
    DatePicker datePicker = new DatePicker(LocalDate.now());

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }

    public void selectOne(MouseEvent mouseEvent) {
        edytujButton.setVisible(true);
    }

    public void pracaGodzina(ActionEvent actionEvent) {
    }

    public void pracaDzien(ActionEvent actionEvent) {
    }

    public void zmianaDaty(ActionEvent actionEvent) {
        pracaDzienButton.setVisible(true);
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

    public final ObservableList<Kalen> data = FXCollections.observableArrayList(
            new Kalen(1,"8:00", " ", "Andriy Adamovych", " ", "Paweł Kulpiński", " ", " ", " "),
            new Kalen(2,"8:30", " ", "", "Dominik Filip", "", " ", " ", " "),
            new Kalen(3,"9:00", "Maciej Dukacz", "", "", "", " ", " ", "Agata Szkup")
    );



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        kalendarz.getItems().setAll(this.data);

        kalendarz.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        kalendarz.getSelectionModel().setCellSelectionEnabled(true);
    }


    //PRZYCISKI PRZEŁĄCZENIA NA INNY EKRAN

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = Paths.get(s1).toUri().toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        przejdz(actionEvent,"./src/main/java/sample/sample.fxml");
    }

    public void wizyty(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "./src/main/java/lekarz/wizyty.fxml"); }

    public void pacjenci(ActionEvent actionEvent) throws IOException { przejdz(actionEvent,"./src/main/java/lekarz/pacjenci.fxml"); }

    public void kartaPacjenta(ActionEvent actionEvent) throws IOException { przejdz(actionEvent,"./src/main/java/lekarz/kartaPacjenta.fxml"); }
}
