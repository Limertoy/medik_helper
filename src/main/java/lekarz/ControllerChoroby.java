package lekarz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ControllerChoroby {
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

    //PRZYCISKI PRZEŁĄCZENIA NA INNY EKRAN

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
}
