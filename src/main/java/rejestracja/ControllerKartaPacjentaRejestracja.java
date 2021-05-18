package rejestracja;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import obiekty.Pacjent;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.PacjentService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static rejestracja.ControllerRejestracjaPacjenci.id_zmien;


public class ControllerKartaPacjentaRejestracja implements Initializable {
    public ChoiceBox wybierzPlec, wybierzWojewodztwo;
    public TextField nazwisko,imie,pesel,miejscowosc,adres,kodPocztowy,telefon,email,drugieImie;
    public DatePicker dataUrodzenia;
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    public PacjentService service;

    private ObservableList<Pacjent> obsList;

    public Pacjent pacjent;

    int id = id_zmien;
    public static Pacjent pacjentID;
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);


    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }

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

    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/rejestracja/rejestracjaPacjenci.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = Paths.get(s1).toUri().toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void setService(PacjentService service) {
        this.service = service;
    }

    public void initializeTextFieldCell() {

        this.setService(new PacjentService());
        pacjent = service.findById(id_zmien);

        imie.setText(pacjent.getImie_pacjenta());
        drugieImie.setText(pacjent.getDrugie_imie());
        nazwisko.setText(pacjent.getNazwisko_pacjenta());
        pesel.setText(pacjent.getPesel());
        miejscowosc.setText(pacjent.getMiejscowosc());
        adres.setText(pacjent.getAdres());
        kodPocztowy.setText(pacjent.getKod_pocztowy());
        telefon.setText(pacjent.getTelefon());
        email.setText(pacjent.getEmail());
        dataUrodzenia.setValue(pacjent.getData_urodzenia());
        wybierzPlec.setValue(pacjent.getPlec());
        wybierzWojewodztwo.setValue(pacjent.getWojewodztwo());
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTextFieldCell();
    }

    public void zapisz(ActionEvent actionEvent) throws IOException {
        this.setService(new PacjentService());
        pacjent.setNazwisko_pacjenta(nazwisko.getText());
        pacjent.setAdres(adres.getText());
        pacjent.setData_urodzenia(dataUrodzenia.getValue());
        pacjent.setDrugie_imie(drugieImie.getText());
        pacjent.setImie_pacjenta(imie.getText());
        pacjent.setEmail(email.getText());
        pacjent.setKod_pocztowy(kodPocztowy.getText());
        pacjent.setMiejscowosc(miejscowosc.getText());
        pacjent.setPesel(pesel.getText());
        pacjent.setTelefon(telefon.getText());
        pacjent.setPlec(wybierzPlec.getValue().toString());
        pacjent.setWojewodztwo(wybierzWojewodztwo.getValue().toString());
        service.saveOrUpdate(pacjent);
        alert2.setTitle("Medik Helper");
        alert2.setHeaderText("Pomyślnie edytowano pacjenta");
        przejdz(actionEvent,"./src/main/java/rejestracja/rejestracjaPacjenci.fxml");
        alert2.showAndWait();
    }
}
