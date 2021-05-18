package sample;

import static com.mycompany.PDFGenerator.PDF.generateChoroby;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import obiekty.*;
import org.hibernate.Session;

import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Rola lekarz = new Rola();
        Rola dyrektor = new Rola();
        Rola pielegniarka = new Rola();
        Rola rejestracja = new Rola();

        lekarz.setNazwa("Lekarz");
        dyrektor.setNazwa("Dyrektor");
        pielegniarka.setNazwa("Pielęgniarka");
        rejestracja.setNazwa("Rejestracja");

        Pracownik pracownik1 = new Pracownik("lekarz@email.com", "12345", lekarz, "Jan", "Kowalski");
        Pracownik pracownik5 = new Pracownik("lekarz1@email.com", "12345", lekarz, "Janusz", "Nowak");
        Pracownik pracownik2 = new Pracownik("dyrektor@email.com", "12345", dyrektor, "Andriy", "Adamovych");
        Pracownik pracownik3 = new Pracownik("pielegniarka@email.com", "12345", pielegniarka, "Paweł", "Kulpiński");
        Pracownik pracownik4 = new Pracownik("rejestracja@email.com", "12345", rejestracja, "Dominik", "Filip");

        LocalDate date1 = LocalDate.parse("1998-10-10");
        Pacjent pacjent1 = new Pacjent("Nowak", "Janusz", "Jan", "98101013413", "mezczyzna", date1, "Podkarpackie", "Rzeszów", "Pigonia 12", "35-600", "123123123", "janusz@gmail.com");
        Pacjent pacjent2 = new Pacjent("Nowakowski", "Jan", "Janusz", "98101013414", "mezczyzna", date1, "Podkarpackie", "Rzeszów", "Pigonia 12", "35-600", "999999999", "jan@gmail.com");

        Date date = new SimpleDateFormat("y-M-d").parse("2021-12-30");


        Wyposazenie wyposazenie = new Wyposazenie("Spirytus 10ml", "Antyseptyk", date, 52);
        Wyposazenie wyposazenie1 = new Wyposazenie("Szafraceum", "Lek", date, 25);

        LocalDate date2 = LocalDate.parse("2021-05-04");
        LocalDate date3 = LocalDate.parse("2021-05-05");

        Sloty slot1 = new Sloty(date2, "8:00", " ", pracownik1);
        Sloty slot2 = new Sloty(date2, "8:30", " ", pracownik1);
        Sloty slot3 = new Sloty(date2, "9:00", " ", pracownik1);
        Sloty slot4 = new Sloty(date2, "9:30", " ", pracownik1);
        Sloty slot5 = new Sloty(date2, "10:00", " ", pracownik1);
        Sloty slot6 = new Sloty(date2, "10:30", " ", pracownik1);
        Sloty slot7 = new Sloty(date3, "10:30", " ", pracownik5);

        session.save(slot1);
        session.save(slot2);
        session.save(slot3);
        session.save(slot4);
        session.save(slot5);
        session.save(slot6);
        session.save(slot7);
        session.save(pracownik5);
        session.save(wyposazenie);
        session.save(wyposazenie1);
        session.save(lekarz);
        session.save(pracownik3);
        session.save(pracownik4);
        session.save(pielegniarka);
        session.save(dyrektor);
        session.save(rejestracja);
        session.save(pracownik1);
        session.save(pracownik2);
        session.save(pacjent1);
        session.save(pacjent2);
        session.getTransaction().commit();

        generateChoroby("Przykładowy pdf");
        

        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        //grab your root here
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}