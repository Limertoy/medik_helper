package sample;

import java.awt.Color;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent loginRoot = loginLoader.load();
        Scene loginScene = new Scene(loginRoot, 1180, 820);

        FXMLLoader wizytyLoader = new FXMLLoader(getClass().getResource("wizyty.fxml"));
        Parent wizytyRoot = wizytyLoader.load();
        Scene wizytyScene = new Scene(wizytyRoot, 1180, 820);

        ControllerLogin controllerLogin = (ControllerLogin) loginLoader.getController();
        controllerLogin.setWizytyScene(wizytyScene);

        ControllerWizyty controllerWizyty = (ControllerWizyty) wizytyLoader.getController();
        controllerWizyty.setLoginScene(loginScene);



        //grab your root here
        loginRoot.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        loginRoot.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(wizytyScene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
