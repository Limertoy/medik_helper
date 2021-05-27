package dyrektor;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.Stage;
import obiekty.Wyposazenie;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerDyrektorZarzadzanie implements Initializable {
    public TableColumn dataWyposazenie, idWyposazenie, nazwaWyposazenie, typWyposazenie, iloscWyposazenie;
    public TableView<Wyposazenia> wyposazenia;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

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
        Query<Wyposazenie> q = session.createQuery("from Wyposazenie");
        List<Wyposazenie> list = q.list();

        ObservableList<Wyposazenia> data = FXCollections.observableArrayList();

        int i = 0;
        for(Wyposazenie s : list){
            data.add(i, new Wyposazenia(s.getId_wyposazenia(), s.getData_waznosci(), s.getNazwa_wyposazenia(), s.getTyp_wyposazenia(), s.getIlosc_pozostalych()));
            i++;
        }

        idWyposazenie.setCellValueFactory(new PropertyValueFactory("idWyposazenie"));
        nazwaWyposazenie.setCellValueFactory(new PropertyValueFactory("nazwaWyposazenie"));
        typWyposazenie.setCellValueFactory(new PropertyValueFactory("typWyposazenie"));
        dataWyposazenie.setCellValueFactory(new PropertyValueFactory("dataWyposazenie"));
        iloscWyposazenie.setCellValueFactory(new PropertyValueFactory("iloscWyposazenie"));
        wyposazenia.getItems().setAll(data);
    }

    public class Wyposazenia{
        public int idWyposazenie, iloscWyposazenie;
        public SimpleStringProperty  nazwaWyposazenie, typWyposazenie;
        public Date dataWyposazenie;

        public int getIdWyposazenie() {
            return idWyposazenie;
        }

        public void setIdWyposazenie(int idWyposazenie) {
            this.idWyposazenie = idWyposazenie;
        }



        public String getNazwaWyposazenie() {
            return nazwaWyposazenie.get();
        }

        public SimpleStringProperty nazwaWyposazenieProperty() {
            return nazwaWyposazenie;
        }

        public void setNazwaWyposazenie(String nazwaWyposazenie) {
            this.nazwaWyposazenie.set(nazwaWyposazenie);
        }

        public String getTypWyposazenie() {
            return typWyposazenie.get();
        }

        public SimpleStringProperty typWyposazenieProperty() {
            return typWyposazenie;
        }

        public void setTypWyposazenie(String typWyposazenie) {
            this.typWyposazenie.set(typWyposazenie);
        }

        public int getIloscWyposazenie() {
            return iloscWyposazenie;
        }

        public void setIloscWyposazenie(int iloscWyposazenie) {
            this.iloscWyposazenie = iloscWyposazenie;
        }

        public Date getDataWyposazenie() {
            return dataWyposazenie;
        }

        public void setDataWyposazenie(Date dataWyposazenie) {
            this.dataWyposazenie = dataWyposazenie;
        }

        public Wyposazenia(int idWyposazenie, Date dataWyposazenie, String nazwaWyposazenie, String typWyposazenie, int iloscWyposazenie) {
            this.idWyposazenie = idWyposazenie;
            this.dataWyposazenie = dataWyposazenie;
            this.nazwaWyposazenie = new SimpleStringProperty(nazwaWyposazenie);
            this.typWyposazenie = new SimpleStringProperty(typWyposazenie);
            this.iloscWyposazenie = iloscWyposazenie;
        }
    }




    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        URL url = getClass().getClassLoader().getResource("sample.fxml");
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        //this line gets stage info
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void personel(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("dyrektor/pracownicy.fxml");
        Parent wizytyParent = FXMLLoader.load(url);
        Scene wizytyScene = new Scene(wizytyParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(wizytyScene);
        window.show();
    }

    public void wydruki(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("dyrektor/raporty.fxml");
        Parent kalendarzParent = FXMLLoader.load(url);
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }

    public void zamowienie(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("dyrektor/dyrektorZamawianie.fxml");
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }
}
