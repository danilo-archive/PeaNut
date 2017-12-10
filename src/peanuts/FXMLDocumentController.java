/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peanuts;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

/**
 *
 * @author alrro
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    WebView webView;
    @FXML
    private Label label;
    @FXML
    private void startProgram(ActionEvent event) throws IOException{
        Stage stage = new Stage(StageStyle.UTILITY);
        Scene scene;
        stage.setTitle("PeaNut");
        scene = new Scene(new Browser(),700,400, Color.web("#666970"));
        PeaNuts.stage1.hide();
        
        stage.setScene(scene);
      //  stage.getIcons().add(new Image(FXMLDocumentController.class.getResourceAsStream("/peanutLogo.png")));   
        stage.show();
       // stage.setResizable(false);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

        
        public void handle(KeyEvent arg0) {
            try {
                // TODO Auto-generated method stub
                takeSnapShot(scene);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
        
        
      
    }
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     private void takeSnapShot(Scene scene) throws IOException{
        WritableImage writableImage = 
            new WritableImage((int)scene.getWidth(), (int)scene.getHeight());
        scene.snapshot(writableImage);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
        Date date = new Date();
        File file = new File(""+dateFormat.format(date)+".png");
        ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
        System.out.println("snapshot saved: " + file.getAbsolutePath());
    }
   
    }
class Browser extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Browser() {
        //apply the styles
        
        URL url = this.getClass().getResource("/index.html");
        webEngine.load(url.toString());
        // load the web page
        
        //add the web view to the scene
        getChildren().add(browser);
 
    }
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}