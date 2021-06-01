package lekarz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import obiekty.Skierowanie;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.SkierowanieService;

import static lekarz.ControllerKartaPacjenta.pacjentID;

public class ControllerSkierowanie {
    public TextArea rozpoznanie, poradnia, info;
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    private SkierowanieService service;


    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }

    public void  setService(SkierowanieService service){this.service = service;}

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = getClass().getClassLoader().getResource(s1);
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        przejdz(actionEvent,"sample.fxml");
    }

    public void wizyty(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "lekarz/wizyty.fxml"); }

    public void pacjenci(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "lekarz/pacjenci.fxml"); }

    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {przejdz(actionEvent, "lekarz/kartaPacjenta.fxml");}

    public void kalendarz(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent, "lekarz/kalendarz.fxml");
    }

    public void wypiszSkierowanie(ActionEvent actionEvent) throws IOException {
        String s1 = poradnia.getText(), s2 = rozpoznanie.getText(), s3 = info.getText();
        if(s1.equals("") | s2.equals("") | s3.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd w programie");
            alert.setHeaderText("Nie podano wszystkich danych!");
            alert.setContentText("Proszę wypełnić wszystkie dane.");
            alert.show();
        } else {
            this.setService(new SkierowanieService());
            Skierowanie sk = new Skierowanie(s1, s2, s3, pacjentID);
            service.saveOrUpdate(sk);
            przejdz(actionEvent, "lekarz/skierowanie.fxml");
        }
    }
}
