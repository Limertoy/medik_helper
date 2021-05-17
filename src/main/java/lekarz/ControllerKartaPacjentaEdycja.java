package lekarz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import obiekty.Pacjent;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.PacjentService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerKartaPacjentaEdycja extends ControllerKartaPacjenta implements Initializable {
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    @FXML
    TextArea imiee, drugieImiee, nazwiskoe, pesele, plece, wojewodztwoe, miejscowosce, adrese, kodPocztowye, telefone, emaile;
    @FXML
    DatePicker narodzinye;
    Pacjent pacjentZPoprzedniegoEkranu = pacjentID;

    private PacjentService service2;

    public void setService2(PacjentService service2) {
        this.service2 = service2;
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

    public void kalendarz(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "./src/main/java/lekarz/kalendarz.fxml");}

    @Override
    public void setService(PacjentService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    imiee.setText(pacjentZPoprzedniegoEkranu.getImie_pacjenta());
    drugieImiee.setText(pacjentZPoprzedniegoEkranu.getDrugie_imie());
    nazwiskoe.setText(pacjentZPoprzedniegoEkranu.getNazwisko_pacjenta());
    pesele.setText(pacjentZPoprzedniegoEkranu.getPesel());
    plece.setText(pacjentZPoprzedniegoEkranu.getPlec());
    wojewodztwoe.setText(pacjentZPoprzedniegoEkranu.getWojewodztwo());
    miejscowosce.setText(pacjentZPoprzedniegoEkranu.getMiejscowosc());
    adrese.setText(pacjentZPoprzedniegoEkranu.getAdres());
    kodPocztowye.setText(pacjentZPoprzedniegoEkranu.getKod_pocztowy());
    telefone.setText(pacjentZPoprzedniegoEkranu.getTelefon());
    emaile.setText(pacjentZPoprzedniegoEkranu.getEmail());
    narodzinye.setValue(pacjentZPoprzedniegoEkranu.getData_urodzenia());

    }
//getData_urodzenia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    public void zapiszZmiany(ActionEvent actionEvent) {
    this.setService2(new PacjentService());
        pacjentZPoprzedniegoEkranu.setImie_pacjenta(imiee.getText());
        pacjentZPoprzedniegoEkranu.setDrugie_imie(drugieImiee.getText());
        pacjentZPoprzedniegoEkranu.setNazwisko_pacjenta(nazwiskoe.getText());
        pacjentZPoprzedniegoEkranu.setPesel(pesele.getText());
        pacjentZPoprzedniegoEkranu.setPlec(plece.getText());
        pacjentZPoprzedniegoEkranu.setData_urodzenia(narodzinye.getValue());
        pacjentZPoprzedniegoEkranu.setWojewodztwo(wojewodztwoe.getText());
        pacjentZPoprzedniegoEkranu.setMiejscowosc(miejscowosce.getText());
        pacjentZPoprzedniegoEkranu.setAdres(adrese.getText());
        pacjentZPoprzedniegoEkranu.setKod_pocztowy(kodPocztowye.getText());
        pacjentZPoprzedniegoEkranu.setTelefon(telefone.getText());
        pacjentZPoprzedniegoEkranu.setEmail(emaile.getText());

        System.out.println(pacjentZPoprzedniegoEkranu.toString());
        service2.saveOrUpdate(pacjentZPoprzedniegoEkranu);
    }


}
