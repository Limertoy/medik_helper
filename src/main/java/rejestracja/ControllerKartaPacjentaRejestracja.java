package rejestracja;

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

public class ControllerKartaPacjentaRejestracja {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;


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


}
