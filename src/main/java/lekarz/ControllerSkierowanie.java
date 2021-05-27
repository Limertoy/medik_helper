package lekarz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.stage.Stage;
import org.hibernate.Session;
import sample.HibernateUtil;

public class ControllerSkierowanie {
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


    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        URL url = getClass().getClassLoader().getResource("sample.fxml");
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void wizyty(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("lekarz/wizyty.fxml");
        Parent wizytyParent = FXMLLoader.load(url);
        Scene wizytyScene = new Scene(wizytyParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(wizytyScene);
        window.show();
    }

    public void kalendarz(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("lekarz/kalendarz.fxml");
        Parent kalendarzParent = FXMLLoader.load(url);
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("lekarz/pacjenci.fxml");
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }
    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("lekarz/kartaPacjenta.fxml");
        Parent kartaPacjentaParent = FXMLLoader.load(url);
        Scene kartaPacjentaScene = new Scene(kartaPacjentaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaScene);
        window.show();
    }

}
