/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Code.Question;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Node;
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
public class NiveauController implements Initializable {
    @FXML
    StackPane ModalDimmer;
     Stage prevStage = new Stage();

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }
    /**
     * Initializes the controller class.
     */
    
    @FXML
    JFXButton facile;
    
    @FXML
    JFXButton moye;
    
    @FXML
    JFXButton difficile;
    
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
        
     
      
    }    
    
    public void QFacile() throws IOException /// Charger une question facile
    {
     
        
        
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Quest1.fxml"));
        QuestFacileController controller = (QuestFacileController) myLoader.getController();
        QuestFacileController.setQuestionF(this.questionP);
        Stage stage = new Stage();
        Parent myPane = (Parent)myLoader.load();
        Scene scene = new Scene(myPane);
       
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        
       
    }
    
    public void QMoyenne() throws IOException /// Charger une question moyenne
    {
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Quest2.fxml"));
        QuestMoyenController controller = (QuestMoyenController) myLoader.getController();
        controller.questionP = questionP;
//        controller.setPrevStage(stage);
        Parent myPane = (Parent)myLoader.load();
        
        
        Scene scene = new Scene(myPane);
        prevStage.close();
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
    }
    
    public void QDifficile() throws IOException /// Charger une question Difficile
    {
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Quest3.fxml"));
        
        
        QuestDifficileController controller = (QuestDifficileController) myLoader.getController();
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
        
        
        
        public void showModalMessage(Node message) {
       ModalDimmer.getChildren().add(message);
       ModalDimmer.setOpacity(0);
       ModalDimmer.setVisible(true);
       ModalDimmer.setCache(true);
        TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.seconds(1), (javafx.event.ActionEvent t) -> {
                    ModalDimmer.setCache(false);
                },
                        new KeyValue(ModalDimmer.opacityProperty(), 1, Interpolator.EASE_BOTH)
                )).build().play();
    }
           public void hideModalMessage() {
        ModalDimmer.setCache(true);
        TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.seconds(1), (javafx.event.ActionEvent t) -> {
                    ModalDimmer.setCache(false);
                    ModalDimmer.setVisible(false);
                    ModalDimmer.getChildren().clear();
                },
                        new KeyValue(ModalDimmer.opacityProperty(), 0, Interpolator.EASE_BOTH)
                )).build().play();
    }
}
