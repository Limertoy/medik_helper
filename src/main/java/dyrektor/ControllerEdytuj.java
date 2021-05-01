package dyrektor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

public class ControllerEdytuj extends ControllerPracownicy implements Initializable {
    public TextField imieZmien, nazwiskoZmien, emailZmien, hasloZmien;
    public ChoiceBox rolaDodaj;
    @FXML
    Button exit_button, minimalize_button;
    int id = id_zmien; //z poprzedniego ekranu

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Query pr = session.createQuery("from Rola");
        List<Rola> prac = pr.list();
        for(Rola r : prac){
            rolaDodaj.getItems().add(r.getNazwa());
        }

        Query<Pracownik> q = session.createQuery("from Pracownik where id=:id")
                .setParameter("id", id);
        List<Pracownik> list = q.list();
        imieZmien.setText(list.get(0).getImie_pracownika());
        nazwiskoZmien.setText(list.get(0).getNazwisko_pracownika());
        emailZmien.setText(list.get(0).getEmail());
        hasloZmien.setText(list.get(0).getHaslo());
        rolaDodaj.setValue(list.get(0).getRola().getNazwa());
    }

    Session session = HibernateUtil.getSessionFactory().openSession();

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

    public void wyloguj(ActionEvent actionEvent) throws IOException {
        session.close();
        przejdz(actionEvent,"./src/main/java/sample/sample.fxml");
    }

    public void wroc(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/pracownicy.fxml");
    }

    public void zmien(ActionEvent actionEvent) throws IOException {
        session.beginTransaction();
        String imie, nazwisko, email, haslo;
        Object rola;
        imie = imieZmien.getText();
        nazwisko = nazwiskoZmien.getText();
        email = emailZmien.getText();
        haslo = hasloZmien.getText();
        rola =  rolaDodaj.getValue();
        Query query = session.createQuery("from Rola where nazwa=:nazwa")
                .setParameter("nazwa", rola);
        List<Rola> list = query.list();
        System.out.println(list.get(0));

        Pracownik pracownik = session.get(Pracownik.class, id);
        pracownik.setImie_pracownika(imie);
        pracownik.setNazwisko_pracownika(nazwisko);
        pracownik.setEmail(email);
        pracownik.setHaslo(haslo);
        pracownik.setRola(list.get(0));

        session.save(pracownik);
        session.getTransaction().commit();
        przejdz(actionEvent,"./src/main/java/dyrektor/pracownicy.fxml");
    }
}
