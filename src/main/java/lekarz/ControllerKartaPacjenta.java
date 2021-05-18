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
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import obiekty.*;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.Lista_ChorobService;
import services.PacjentService;
import services.PracownikService;
import services.WyposazenieService;

import javax.swing.*;

public class ControllerKartaPacjenta extends ControllerPacjenci implements Initializable {
    public ChoiceBox chorobyChoice;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    @FXML
    private TextField nazwisko, imie, drugieImie, pesel, plec, narodziny, wojewodztwo, miejscowosc, adres, kodPocztowy, telefon, email;
    @FXML
    private TextArea obserwacjeTekst, przepisanie;

    public PacjentService service;
    public Lista_ChorobService listaChorobService;
    public PracownikService pracownik;

    private ObservableList<Pacjent> obsList;
    private ObservableList<Lista_chorob> obsList1;

    public Pacjent pacjent;


    int id = id_zmien;
    public static Pacjent pacjentID;

    Session session = HibernateUtil.getSessionFactory().openSession();

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }

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
        session.close();
        przejdz(actionEvent, "./src/main/java/sample/sample.fxml");
    }

    public void wizyty(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "./src/main/java/lekarz/wizyty.fxml"); }

    public void kalendarz(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/lekarz/kalendarz.fxml");
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException { przejdz(actionEvent,"./src/main/java/lekarz/pacjenci.fxml"); }

    public void skierowanie(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/lekarz/skierowanie.fxml");
    }

    public void choroby(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/lekarz/choroby.fxml");
    }

    public void kartaPacjentaEdycja(ActionEvent actionEvent) throws IOException {
        this.setService(new PacjentService());
        pacjent = service.findById(id_zmien);
        pacjentID = pacjent;

        przejdz(actionEvent,"./src/main/java/lekarz/kartaPacjentaEdycja.fxml");
    }

    public void setService(PacjentService service) {
        this.service = service;
    }

    public void setChorobyService(Lista_ChorobService listaChorobService){this.listaChorobService = listaChorobService; }

    public void setPracownik(PracownikService pracownikService){this.pracownik = pracownikService;}

    public void initializeTextFieldCell() {

        this.setService(new PacjentService());
        if(id_zmien == 0) {
            pacjent = service.findById(1);
        } else {
            pacjent = service.findById(id_zmien);
        }
        imie.setText(pacjent.getImie_pacjenta());
        drugieImie.setText(pacjent.getDrugie_imie());
        nazwisko.setText(pacjent.getNazwisko_pacjenta());
        pesel.setText(pacjent.getPesel());
        plec.setText(pacjent.getPlec());
        narodziny.setText(pacjent.getData_urodzenia().toString());
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
        setChorobyService(new Lista_ChorobService());
        obsList1 = FXCollections.observableArrayList(listaChorobService.findAll());
        chorobyChoice.setItems(obsList1);
    }

    public void obserwacje(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent, "./src/main/java/lekarz/obserwacje.fxml");
    }

    public void zapiszObserwacje(ActionEvent actionEvent) throws IOException {
        session.beginTransaction();
        String s1 = obserwacjeTekst.getText();
        Lista_chorob s2 = (Lista_chorob) chorobyChoice.getValue();
        setPracownik(new PracownikService());
        Pracownik pr = pracownik.findById(id_sesji);
        Obserwacja ob = new Obserwacja(s1, s2, pr, pacjent);
        session.save(ob);
        session.getTransaction().commit();
        przejdz(actionEvent, "./src/main/java/lekarz/kartaPacjenta.fxml");
    }
}
