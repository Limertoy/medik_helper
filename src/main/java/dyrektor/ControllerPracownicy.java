package dyrektor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import obiekty.ListaPracownikow;
import obiekty.Pracownik;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPracownicy implements Initializable {
    public Button buttonLogin4, buttonLogin6;
    public TableColumn imie_table, nazwisko_table, email_table, rola_table;
    public TableView<ListaPracownikow> pracownicy;
    public Stage stage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    Session session = HibernateUtil.getSessionFactory().openSession();



    public void initialize(URL url, ResourceBundle resourceBundle) {

        Query<Pracownik> q = session.createQuery("from Pracownik");
        List<Pracownik> list = q.list();

        ObservableList<ListaPracownikow> data = FXCollections.observableArrayList();


        for(Pracownik s : list){
            data.add(s.getId_pracownika()-1, new ListaPracownikow(s.getImie_pracownika(), s.getNazwisko_pracownika(),s.getEmail(), s.getRola().getNazwa()));
        }


        imie_table.setCellValueFactory(new PropertyValueFactory("imie_table"));
        nazwisko_table.setCellValueFactory(new PropertyValueFactory("nazwisko_table"));
        email_table.setCellValueFactory(new PropertyValueFactory("email_table"));
        rola_table.setCellValueFactory(new PropertyValueFactory("rola_table"));
        pracownicy.getItems().setAll(data);
    }




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
