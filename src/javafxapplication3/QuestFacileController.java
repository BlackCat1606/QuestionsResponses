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
import static javafxapplication3.Partie.ans;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javafxapplication3.NiveauController.questionP;
import static javafxapplication3.Partie.A;
import static javafxapplication3.Partie.B;
import static javafxapplication3.Partie.Jcourant;
import static javafxapplication3.Partie.ans;

/**
 * FXML Controller class
 *
 * @author saiyen
 */
public class QuestFacileController implements Initializable { // Controleur de la question facile pour la partie

    @FXML
    private AnchorPane question;
    public static Question questionF ;

    public static Question getQuestionF() {
        return questionF;
    }

    public static void setQuestionF(Question questionF) {
        QuestFacileController.questionF = questionF;
    }
    
    
    
    Stage prevStage = new Stage();

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }
    @FXML
    CheckBox p1;
    
    @FXML
    CheckBox p2;
    
    @FXML
    Button Confirmer;
    
    @FXML
    TextArea QuestF ;
     public static String answer;
     public static int n=0;
    public static String getAnswer() {
        return answer;
    }

    public static void setAnswer(String answer) {
        QuestFacileController.answer = answer;
    }

  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
   QuestF.setText(this.questionF.getQuestion());
   Confirmer.setDisable(true);
   ///Listner
   ChangeListener<Boolean> ConfirmerListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  Confirmer.setDisable(p1.isSelected() ==false && p2.isSelected()==false);
            }
   };
   p1.selectedProperty().addListener(ConfirmerListener);
   p2.selectedProperty().addListener(ConfirmerListener);
   p1.setOnMouseClicked(k->
        {
        p2.setSelected(false);
        }       
        );
        p2.setOnMouseClicked(k->
        {
        p1.setSelected(false);
        }
                
        );
        
         int m =(int)(100* Math.random());
       if(m%2 ==0 )
       {
          p1.setText(questionF.getPropo1());
          p2.setText(questionF.getReponse());
      
       }
       if(m%2 ==1 )
       {
          p1.setText(questionF.getReponse());
          p2.setText(questionF.getPropo3());
  
       }
      
}
      
    public void confirmer() throws IOException /// Vérification aprés confirmation de la réponse
    {
            if(p1.isSelected()) this.answer = p1.getText();
            else
            {
                if(p2.isSelected()) this.answer = p2.getText();
                
            }
          System.out.println("this is it : "+this.answer);
          
          ans = QuestFacileController.answer;
          Partie.answered = true;
         n= this.compterPoints(questionF, this.answer, Jcourant, Difficultés.Facile);
         System.out.println("compatage ponints "+n);
         int advance=0;
         
         if(n!=0)
         {
             /// Déplacement si la réponse est juste
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("deplacement.fxml"));
        DeplacementController controller = (DeplacementController) myLoader.getController();
            
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
              
       if(Jcourant.getNom().equals(A.getNom()))
        {  
 
            A = Jcourant;
            Jcourant = B;
        }
        else
        {
        if(Jcourant.getNom().equals(B.getNom()))
        {    
            B = Jcourant;
            Jcourant = A;
        }
        }
       Partie.Namej.setText(Jcourant.getNom());
         }
        }
    /// Comptage des points 
     public static int compterPoints(Question question,String reponse,Joueur player,Difficultés niveau)
    {
    int n = 0;
    if(reponse!=null)
    {
    if(!question.getReponse().equals(reponse))
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
}
