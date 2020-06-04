/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       AnchorPane root = null;
       
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/NotePad.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root, 700, 600);
        
//        Screen screen = Screen.getPrimary();
//        Rectangle2D bounds = screen.getVisualBounds();

//        primaryStage.setX(bounds.getMinX());
//        primaryStage.setY(bounds.getMinY());
//        primaryStage.setWidth(bounds.getWidth());
//        primaryStage.setHeight(bounds.getHeight());
//        primaryStage.setTitle("NotePad");
//        root.prefHeightProperty().bind(scene.heightProperty());
//        root.prefWidthProperty().bind(scene.widthProperty());
        
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(true);
//        setMaximized(true) ;
//        setFullScreen(true);
//        primaryStage.setMaximized(true);
//        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
