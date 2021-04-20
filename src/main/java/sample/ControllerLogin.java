package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerLogin {
    public Stage primaryStage;
    @FXML
    public Button buttonLogin, exit_button, minimalize_button;
    public TextField textFieldLogin;

    @FXML
    public AnchorPane dynamicPane;


    public ControllerLogin(){}


    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    private void setDynamicPane(AnchorPane dynamicPane){
        this.dynamicPane.getChildren().clear();
        this.dynamicPane.getChildren().add(dynamicPane);
    }

    public void przejdz(ActionEvent a, String s1) throws IOException {
        URL url = Paths.get(s1).toUri().toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)a.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void zaloguj(ActionEvent actionEvent) throws IOException {

        String login;
        login = textFieldLogin.getText();
        System.out.println(login);


        if (login.equals("lekarz"))
        {
            przejdz(actionEvent, "./src/main/java/lekarz/wizyty.fxml");
        }
        else if (login.equals("p"))
        {
            przejdz(actionEvent, "./src/main/java/pielegniarka/wydawanieLekow.fxml");
        }
        else if (login.equals("d"))
        {
            przejdz(actionEvent, "./src/main/java/dyrektor/dyrektorZamawianie.fxml");
        }
        else {

            przejdz(actionEvent, "./src/main/java/rejestracja/rejestracja.fxml");
        }
    }

}
