package dyrektor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ControllerPracownicy {
    public Button buttonLogin4, buttonLogin6;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        przejdz(actionEvent,"./src/main/java/sample/sample.fxml");
    }

    public void zamowienie(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/dyrektorZamawianie.fxml");
    }

    public void wydruki(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/raporty.fxml");
    }

    public void wyposazenie(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/dyrektorZarzadzanie.fxml");
    }

    public void pokazButtony(MouseEvent mouseEvent) {
        buttonLogin4.setVisible(true);
        buttonLogin6.setVisible(true);
    }

    public void edytuj(ActionEvent actionEvent) {
    }

    public void usun(ActionEvent actionEvent) {
    }

    public void dodaj(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/dodaj.fxml");
    }
}
