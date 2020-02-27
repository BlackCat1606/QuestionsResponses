/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Code.AlerteBox;
import Code.Domaine;
import Code.Joueur;
import Code.Question;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javafxapplication3.DeplacementController.Nbr_QCh;
import static javafxapplication3.DeplacementController.Nbr_Qoc;
import static javafxapplication3.DeplacementController.pointCh;
import static javafxapplication3.DeplacementController.pointOc;
import static javafxapplication3.ChargementPartieController.A;
import static javafxapplication3.ChargementPartieController.B;
import static javafxapplication3.ChargementPartieController.Jcourant;
import static javafxapplication3.ChargementPartieController.Questions;

/**
 * FXML Controller class
 *
 * @author BLACKCAT
 */
public class Duel2Controller implements Initializable { /// Duel controleur pour la chargement de partie
    public static Question questionP;
   @FXML 
   StackPane stackPane;
    public Question getQuestionP() {
        return questionP;
    }

    public void setQuestionP(Question questionP) {
        this.questionP = questionP;
    }
    @FXML
    AnchorPane duel;
    
  public static Joueur Oc,Ch;

    public Joueur getOc() {
        return Oc;
    }

    public void setOc(Joueur Oc) {
        this.Oc = Oc;
    }

    public Joueur getCh() {
        return Ch;
    }

    public void setCh(Joueur Ch) {
        this.Ch = Ch;
    }
    
 public static Joueur now;   
    @FXML
    JFXTextField Reponse;
  
    @FXML
    JFXButton Confirmer;
    
    @FXML
    Label Nomj;
    
    @FXML
    Label ScoreJ;
    
    @FXML
    JFXTextArea Quest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        Quest.setText(this.questionP.getQuestion());
        Nomj.setText(Oc.getNom());
        ScoreJ.setText("0");
        now = Oc; 
        // TODO
    }


public void confirmer()
 {
     if(Math.abs(pointOc-pointCh)<2 && ((Nbr_QCh+ Nbr_Qoc)<6))
     {
    if(Reponse.getText().equalsIgnoreCase(questionP.getReponse()))
    {   if(now.getNom().equals(Oc.getNom()))
       {
         pointOc++;
         Nbr_Qoc++;
       } 
       else
       {
        pointCh++;
        Nbr_QCh++;
        
       }   
    }
    else
    {
        if(now.getNom().equals(Oc.getNom()))
       {
         
         Nbr_Qoc++;
       } 
       else
       {
    
        Nbr_QCh++;    
       }   
           
    }
    if(now.getNom().equals(Oc.getNom()))
            {
                now = Ch;
                Nomj.setText(now.getNom());
                ScoreJ.setText(""+pointCh);
            }
    else
     { 
        if(now.getNom().equals(Ch.getNom()))
     {
        now = Oc;
          Nomj.setText(now.getNom());
          ScoreJ.setText(""+pointOc);
     }
     }
    TreeSet<Domaine> tr = Oc.getDomaines();
    Domaine m = tr.pollFirst();
    Iterator<Question> it= Questions.iterator();
                 while(it.hasNext())
                 {
                     Question q = it.next();
                     if(q.getDomaine() == m)
                     {
                          this.questionP = q;
                          Quest.setText(q.getQuestion());
                          it.remove();
                     }
                 }
     }
     else
     {
         Joueur gagnant = new Joueur();
         boolean chal;
           if(pointOc>=pointCh)
           {
                AlerteBox a = new AlerteBox();
                AlerteBox.infoBox("Le joueur "+Oc.getNom()+" a Gagné le duel", "Félicitations");
                gagnant = Oc;
                chal = false;
                
           }
           else
           {
                AlerteBox a = new AlerteBox();
                AlerteBox.infoBox("Le joueur "+Ch.getNom()+" a Gagné le duel", "Félicitations");
                gagnant = Ch;
                chal = true;
           }
            if(!chal)
            {
                Jcourant.setAvancement(ChoiceController.newpos);
              
             if(Jcourant.getNom().equals(A.getNom()))
        {  
 
            B.setAvancement(ChoiceController.lastpos);
        }
        else
        {
        if(Jcourant.getNom().equals(B.getNom()))
        {    
           A.setAvancement(ChoiceController.lastpos);
        }
        }
        }
            else
            {
                    
          FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Choice2.fxml"));
          Choice2Controller controller = new Choice2Controller();
                     controller.winner = gagnant;
                     controller.challengeur = chal;
                     Stage stage = new Stage();
                     
               try {
        Parent myPane = (Parent)myLoader.load();
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
               } catch (IOException ex) {
        Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
              }
         
             }
     }
 
 }
 public void showModalMessage(Node message) {
        stackPane.getChildren().add(message);
        stackPane.setOpacity(0);
        stackPane.setVisible(true);
        stackPane.setCache(true);
        TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.seconds(1), (javafx.event.ActionEvent t) -> {
                    stackPane.setCache(false);
                },
                        new KeyValue(stackPane.opacityProperty(), 1, Interpolator.EASE_BOTH)
                )).build().play();
    }
    
    public void hideModalMessage() {
        stackPane.setCache(true);
        TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.seconds(1), (javafx.event.ActionEvent t) -> {
                    stackPane.setCache(false);
                    stackPane.setVisible(false);
                    stackPane.getChildren().clear();
                },
                        new KeyValue(stackPane.opacityProperty(), 0, Interpolator.EASE_BOTH)
                )).build().play();
    }
}
