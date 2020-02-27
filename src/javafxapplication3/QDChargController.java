/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Code.AlerteBox;
import Code.Difficultés;
import Code.Joueur;
import Code.Question;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javafxapplication3.ChargementPartieController.A;
import static javafxapplication3.ChargementPartieController.B;
import static javafxapplication3.ChargementPartieController.Jcourant;



/**
 * FXML Controller class
 *
 * @author saiyen
 */
public class QDChargController implements Initializable { /// Controleur de la question difficile pour le chargement

    @FXML
    private AnchorPane question;
    
    @FXML 
    public TextArea QuesD;
    
    public static Question questionP;

    public Question getQuestionP() {
        return questionP;
    }

    public void setQuestionP(Question questionP) {
        this.questionP = questionP;
    }
     public static int n=0;
    
    
    @FXML
    TextField reponse;
    @FXML
    Button Confirmer;
    public Pane ModalDimmer;
    
    public static String answer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         System.out.println("i'm : "+this.questionP.getQuestion());
         QuesD.setText(this.questionP.getQuestion());
        Confirmer.setDisable(true);
   ChangeListener<String> ConfirmerListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                  Confirmer.setDisable(reponse.getText()==null);
            }
   };
   reponse.textProperty().addListener(ConfirmerListener);
  
        
    }

public void confirmer() throws IOException
    {
          
                
         this.answer = reponse.getText();
         System.out.println("this is it : "+this.answer);
          
         
         Partie.answered = true;
         n= this.compterPoints(questionP, this.answer, Jcourant, Difficultés.Difficile);
         System.out.println("compatage ponints "+n);
         int advance=0;
         
         if(n!=0)
         {
             
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Dep2.fxml"));
        Dep2Controller controller = (Dep2Controller) myLoader.getController();
            controller.number = n;
           
        Parent myPane = (Parent)myLoader.load();
        
       
        Scene scene = new Scene(myPane);
 
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
   
         }
         else
         {
                AlerteBox a = new AlerteBox();
                AlerteBox.infoBox("Votre Réponse est fausse", "Erreur");
              if(Jcourant.getNom().equals("A"))
        {  
 
            A= Jcourant;
            Jcourant = B;
        }
        else
        {
        if(Jcourant.getNom().equals("B"))
        {    
             B= Jcourant;
            Jcourant = A;
        }
        }
         }
        }
    
     public static int compterPoints(Question question,String reponse,Joueur player,Difficultés niveau)
    {
    int n = 0;
    if(reponse!=null)
    {
    if(!question.getReponse().equalsIgnoreCase(reponse))
    {
        n=0;
    }
    else
    {
        if(niveau== Difficultés.Facile)
        {
            if(player.getDomaines().contains(question.getDomaine()))
            {
                n=1;
            }
            else
            {
                n=2;
            }
            
        }
        if(niveau== Difficultés.Moyen)
        {
            if(player.getDomaines().contains(question.getDomaine()))
            {
                n=2;
            }
            else
            {
                n=3;
            }
            
        }
        if(niveau== Difficultés.Difficile)
        {
            if(player.getDomaines().contains(question.getDomaine()))
            {
                n=4;
            }
            else
            {
                n=5;
            }
            
        }
   
    }
    }
    return n;
    }





    
    public void setModalDimmer( Pane ModalDimmer) 
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
