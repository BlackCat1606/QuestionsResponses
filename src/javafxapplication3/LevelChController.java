/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Code.Question;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author saiyen
 */
public class LevelChController implements Initializable {/// Controleur du niveau de la question pour le chargement de partie
    StackPane ModalDimmer;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    Button facile;
    
    @FXML
    Button moye;
    
    @FXML
    Button difficile;
    
    @FXML
    AnchorPane question;
    
    public static  Question questionP ;
    
    
    public static String answer;

    public Question getQuestionP() {
        return this.questionP;
    }

    public void setQuestionP(Question questionP) {
        this.questionP = questionP;
    }

    public static String getAnswer() {
        return answer;
    }

    public static void setAnswer(String answer) {
        NiveauController.answer = answer;
    }
    
    
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      System.out.println("i'm : "+questionP.getQuestion());
      
    }    
    
    public void QFacile() throws IOException
    {
      
        
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("QFCharg.fxml"));
        QFChargController controller = (QFChargController) myLoader.getController();
        controller.questionF = this.questionP ;
        Parent myPane = (Parent)myLoader.load();
        
        
        
        Scene scene = new Scene(myPane);
        
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
       
    }
    
    public void QMoyenne() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("QMCharg.fxml"));
        QMChargController controller = (QMChargController) myLoader.getController();
        controller.questionP = questionP;
        Parent myPane = (Parent)myLoader.load();
        
        
        Scene scene = new Scene(myPane);
        
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
    }
    
    public void QDifficile() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("QDCharg.fxml"));
        
        
        QDChargController controller = (QDChargController) myLoader.getController();
        controller.questionP = questionP;
        Parent myPane = (Parent)myLoader.load();
        Scene scene = new Scene(myPane);
        
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
    }         
 
        public void setModalDimmer(StackPane ModalDimmer) 
        {
        this.ModalDimmer = ModalDimmer;
    }
}
