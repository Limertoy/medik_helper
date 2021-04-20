package dyrektor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ControllerDyrektorZamawianie {
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

    public void wyloguj(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        //this line gets stage info
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }


    public void personel(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/dyrektor/personel.fxml").toUri().toURL();
        Parent kalendarzParent = FXMLLoader.load(url);
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }

    public void wydruki(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/dyrektor/raporty.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }

    public void wyposazenie(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/dyrektor/dyrektorZarzadzanie.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }
}
