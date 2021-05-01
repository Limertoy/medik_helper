package dyrektor;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import obiekty.Pracownik;
import obiekty.Rola;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerDodaj implements Initializable {
    public TextField nazwiskoDodaj, imieDodaj, emailDodaj, hasloDodaj;
    public ChoiceBox rolaDodaj;
    @FXML
    Button exit_button, minimalize_button;

    Session session = HibernateUtil.getSessionFactory().openSession();

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
        Query rola = session.createQuery("from Rola");
        List<Rola> listRola = rola.list();
        for(Rola r : listRola){
            rolaDodaj.getItems().add(r.getNazwa());
        }
    }

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = Paths.get(s1).toUri().toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }


    public void wyloguj(ActionEvent actionEvent) throws IOException {
        session.close();
        przejdz(actionEvent,"./src/main/java/sample/sample.fxml");
    }


    public void wroc(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/pracownicy.fxml");
    }

    public void dodaj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String imie, nazwisko, email, haslo;
        Object rola;
        imie = imieDodaj.getText();
        nazwisko = nazwiskoDodaj.getText();
        email = emailDodaj.getText();
        haslo = hasloDodaj.getText();
        rola =  rolaDodaj.getValue();
        Query query = session.createQuery("from Rola where nazwa=:nazwa")
                .setParameter("nazwa", rola);
        List<Rola> list = query.list();
        System.out.println(list.get(0));

        Pracownik pracownik = new Pracownik(email, haslo, list.get(0), imie, nazwisko);

        session.save(pracownik);
        session.getTransaction().commit();
        przejdz(actionEvent,"./src/main/java/dyrektor/pracownicy.fxml");
    }
}
