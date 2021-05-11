package lekarz;

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
import java.util.ResourceBundle;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import obiekty.Pacjent;
import obiekty.Wyposazenie;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.PacjentService;
import services.WyposazenieService;

import javax.swing.*;

public class ControllerKartaPacjenta extends ControllerPacjenci implements Initializable {
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    @FXML
    private TextField nazwisko, imie, drugieImie, pesel, plec, narodziny, wojewodztwo, miejscowosc, adres, kodPocztowy, telefon, email;
    @FXML
    private TextArea obserwacje, przepisanie;

    private PacjentService service;

    private ObservableList<Pacjent> obsList;

    private Pacjent pacjent;

    int id = id_zmien;
    public static Pacjent pacjentID;



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

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/pacjenci.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }
    public void skierowanie(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/skierowanie.fxml").toUri().toURL();
        Parent skierowanieParent = FXMLLoader.load(url);
        Scene skierowanieScene = new Scene(skierowanieParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(skierowanieScene);
        window.show();
    }

    public void choroby(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/choroby.fxml").toUri().toURL();
        Parent chorobyParent = FXMLLoader.load(url);
        Scene chorobyScene = new Scene(chorobyParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(chorobyScene);
        window.show();
    }
    public void kartaPacjentaEdycja(ActionEvent actionEvent) throws IOException {
        this.setService(new PacjentService());
        pacjent = service.findById(1);   // 1 nalezy zmienic na id kiedy widok wizyt zostanie podloczaony do bazy danych
        pacjentID = pacjent;

        URL url = Paths.get("./src/main/java/lekarz/kartaPacjentaEdycja.fxml").toUri().toURL();
        Parent kartaPacjentaEdycjaParent = FXMLLoader.load(url);
        Scene kartaPacjentaEdycjaScene = new Scene(kartaPacjentaEdycjaParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaEdycjaScene);
        window.show();
    }

    public void setService(PacjentService service) {
        this.service = service;
    }

    public void initializeTextFieldCell() {

        this.setService(new PacjentService());
        pacjent = service.findById(1);   // 1 nalezy zmienic na id kiedy widok wizyt zostanie podloczaony do bazy danych

        imie.setText(pacjent.getImie_pacjenta());
        drugieImie.setText(pacjent.getDrugie_imie());
        nazwisko.setText(pacjent.getNazwisko_pacjenta());
        pesel.setText(pacjent.getPesel());
        plec.setText(pacjent.getPlec());
        //narodziny.setText(pacjent.getData_urodzenia()).toString();
        wojewodztwo.setText(pacjent.getWojewodztwo());
        miejscowosc.setText(pacjent.getMiejscowosc());
        adres.setText(pacjent.getAdres());
        kodPocztowy.setText(pacjent.getKod_pocztowy());
        telefon.setText(pacjent.getTelefon());
        email.setText(pacjent.getEmail());



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTextFieldCell();

    }
}
