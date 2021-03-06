package dyrektor;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import obiekty.Pracownik;
import obiekty.Rola;
import obiekty.Sloty;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.HibernateUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class ControllerPracownicy implements Initializable {
    public Button buttonLogin4, buttonLogin6;
    public TableColumn imie_table, nazwisko_table, email_table, rola_table;
    public TableView<ListaPracownikow> pracownicy;
    public Stage stage;
    public static int id_zmien;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    Session session = HibernateUtil.getSessionFactory().openSession();



    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query<Pracownik> q = session.createQuery("from Pracownik");
        List<Pracownik> list = q.list();

        ObservableList<ListaPracownikow> data = FXCollections.observableArrayList();

        int i = 0;
        for(Pracownik s : list){
            data.add(i, new ListaPracownikow(s.getId_pracownika(), s.getImie_pracownika(), s.getNazwisko_pracownika(),s.getEmail(), s.getRola().getNazwa()));
            i++;
        }


        imie_table.setCellValueFactory(new PropertyValueFactory("imie_table"));
        nazwisko_table.setCellValueFactory(new PropertyValueFactory("nazwisko_table"));
        email_table.setCellValueFactory(new PropertyValueFactory("email_table"));
        rola_table.setCellValueFactory(new PropertyValueFactory("rola_table"));
        pracownicy.getItems().setAll(data);
    }


    public class ListaPracownikow{
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

        public ListaPracownikow(int id, String imie_table, String nazwisko_table, String email_table, String rola_table) {
            this.id = id;
            this.imie_table = new SimpleStringProperty(imie_table);
            this.nazwisko_table = new SimpleStringProperty(nazwisko_table);
            this.email_table = new SimpleStringProperty(email_table);
            this.rola_table = new SimpleStringProperty(rola_table);
        }
    }




    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = getClass().getClassLoader().getResource(s1);
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }


    public void wyloguj(ActionEvent actionEvent) throws IOException {
        session.close();
        przejdz(actionEvent,"sample.fxml");
    }

    public void zamowienie(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"dyrektor/dyrektorZamawianie.fxml");
    }

    public void wydruki(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"dyrektor/raporty.fxml");
    }

    public void wyposazenie(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"dyrektor/dyrektorZarzadzanie.fxml");
    }

    public void pokazButtony(MouseEvent mouseEvent) {
        buttonLogin4.setVisible(true);
        buttonLogin6.setVisible(true);
    }

    public void edytuj(ActionEvent actionEvent) throws IOException {
        ListaPracownikow selected = pracownicy.getSelectionModel().getSelectedItem();
        id_zmien = selected.id;
        przejdz(actionEvent,"dyrektor/edytuj.fxml");
    }

    public void usun(ActionEvent actionEvent) throws IOException {
        ListaPracownikow selected = pracownicy.getSelectionModel().getSelectedItem();
        try {
            session.beginTransaction();
            Pracownik pracownik = session.get(Pracownik.class, selected.id);
            session.delete(pracownik);
            session.getTransaction().commit();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wyst??pi?? b????d");
            alert.setHeaderText("Nie uda??o si?? usun???? tego pracownika!");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            alert.setContentText(sw.toString());
        }

        przejdz(actionEvent,"dyrektor/pracownicy.fxml");
    }

    public void dodaj(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"dyrektor/dodaj.fxml");
    }
}
