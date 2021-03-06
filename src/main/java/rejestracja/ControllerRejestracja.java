package rejestracja;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import obiekty.Pacjent;
import org.hibernate.Session;
import sample.HibernateUtil;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerRejestracja implements Initializable {
    public ChoiceBox wybierzPlec, wybierzWojewodztwo;
    public TextField nazwisko,imie,pesel,miejscowosc,adres,kodPocztowy,telefon,email,drugieImie;
    public DatePicker dataUrodzenia;
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    DatePicker datePicker = new DatePicker(LocalDate.now());
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
    Session session = HibernateUtil.getSessionFactory().openSession();


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
        przejdz(actionEvent, "sample.fxml");
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent, "rejestracja/rejestracjaPacjenci.fxml");
    }

    public void zarzadzaj(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent, "rejestracja/zarzadzaj.fxml");
    }

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = getClass().getClassLoader().getResource(s1);
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void zarejestruj(ActionEvent actionEvent) throws SQLException, IOException {
        

        if (nazwisko.getText().isEmpty()) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? poda?? nazwisko");
            alert.showAndWait();
            return;
        }
        if (imie.getText().isEmpty()) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? poda?? imi??");
            alert.showAndWait();
            return;
        }
        if (pesel.getText().isEmpty()) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? poda?? pesel");
            alert.showAndWait();
            return;
        }
        if (wybierzPlec.getValue() == null) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? wybra?? p??e??");
            alert.showAndWait();
            return;
        }
        if (dataUrodzenia.getValue() == null) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? wybra?? dat?? urodzenia");
            alert.showAndWait();
            return;
        }
        if (wybierzWojewodztwo.getValue()==null) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? wybra?? wojew??dztwo");
            alert.showAndWait();
            return;
        }
        if (miejscowosc.getText().isEmpty()) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? poda?? miejscowo????");
            alert.showAndWait();
            return;
        }
        if (adres.getText().isEmpty()) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? poda?? adres");
            alert.showAndWait();
            return;
        }
        if (kodPocztowy.getText().isEmpty()) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? poda?? kod pocztowy");
            alert.showAndWait();
            return;
        }
        if (telefon.getText().isEmpty()) {
            alert.setTitle("Medik Helper");
            alert.setHeaderText("Wprowadzono nieprawid??owe dane");
            alert.setContentText("Prosz?? poda?? telefon");
            alert.showAndWait();
            return;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String imieDodaj, nazwiskoDodaj, peselDodaj,miejscowoscDodaj,adresDodaj,kodPocztowyDodaj,telefonDodaj,emailDodaj,drugieImieDodaj;
        String plecDodaj, wojewodztwoDodaj;
        LocalDate dataUrodzeniaDodaj;
        plecDodaj =(String) wybierzPlec.getValue();
        wojewodztwoDodaj = (String) wybierzWojewodztwo.getValue();
        dataUrodzeniaDodaj = dataUrodzenia.getValue();
        imieDodaj = imie.getText();
        nazwiskoDodaj = nazwisko.getText();
        peselDodaj = pesel.getText();
        miejscowoscDodaj = miejscowosc.getText();
        adresDodaj =  adres.getText();
        kodPocztowyDodaj = kodPocztowy.getText();
        telefonDodaj = telefon.getText();
        emailDodaj = email.getText();
        drugieImieDodaj = drugieImie.getText();


        Pacjent pacjent = new Pacjent(nazwiskoDodaj,imieDodaj,drugieImieDodaj,peselDodaj,plecDodaj,dataUrodzeniaDodaj,wojewodztwoDodaj,miejscowoscDodaj,adresDodaj,kodPocztowyDodaj,telefonDodaj,emailDodaj);

        session.save(pacjent);
        session.getTransaction().commit();

        alert2.setTitle("Medik Helper");
        alert2.setHeaderText("Pomy??lnie dodano nowego pacjenta");
        przejdz(actionEvent,"rejestracja/rejestracja.fxml");
        alert2.showAndWait();
    }

}

