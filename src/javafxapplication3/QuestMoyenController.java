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
import static javafxapplication3.Partie.A;
import static javafxapplication3.Partie.B;
import static javafxapplication3.Partie.Jcourant;
import static javafxapplication3.Partie.ans;
import static javafxapplication3.QuestFacileController.questionF;

/**
 * FXML Controller class
 *
 * @author saiyen
 */
public class QuestMoyenController implements Initializable {/// Controleur de la question moyenne pour la partie

    @FXML
    private AnchorPane question;
    @FXML
    TextArea QuestM;
    
    @FXML
    CheckBox p1;
    
    @FXML
    CheckBox p2;
    
    @FXML
    CheckBox p3;
    Stage prevStage = new Stage();

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }
    
    public static Question questionP;
    @FXML
    CheckBox p4;
    @FXML
    Button Confirmer;
    
    public static String answer;
     public static int n=0;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        QuestM.setText(questionP.getQuestion());
         Confirmer.setDisable(true);
   ChangeListener<Boolean> ConfirmerListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  Confirmer.setDisable(p1.isSelected() ==false && p2.isSelected()==false && p3.isSelected() ==false && p4.isSelected()==false);
            }
   };
   p1.selectedProperty().addListener(ConfirmerListener);
   p2.selectedProperty().addListener(ConfirmerListener);
   p3.selectedProperty().addListener(ConfirmerListener);
   p4.selectedProperty().addListener(ConfirmerListener);
   p1.setOnMouseClicked(k->
        {
        p2.setSelected(false);
        p3.setSelected(false);
        p4.setSelected(false);
        }       
        );
    p2.setOnMouseClicked(k->
        {
        p1.setSelected(false);
        p3.setSelected(false);
        p4.setSelected(false);
        }       
        );
     p3.setOnMouseClicked(k->
        {
        p2.setSelected(false);
        p1.setSelected(false);
        p4.setSelected(false);
        }       
        );
      p4.setOnMouseClicked(k->
        {
        p2.setSelected(false);
        p3.setSelected(false);
        p1.setSelected(false);
        }       
        );
        

        
        
       int m =(int)(100* Math.random());
       if(m%4 ==0 )
       {
          p1.setText(questionP.getPropo1());
          p2.setText(questionP.getPropo2());
          p3.setText(questionP.getPropo3());
          p4.setText(questionP.getReponse());
       }
       if(m%4 ==1 )
       {
          p1.setText(questionP.getPropo2());
          p2.setText(questionP.getPropo1());
          p4.setText(questionP.getPropo3());
          p3.setText(questionP.getReponse());
       }
       if(m%4 ==2 )
       {
          p1.setText(questionP.getPropo3());
          p2.setText(questionP.getReponse());
          p3.setText(questionP.getPropo2());
          p4.setText(questionP.getPropo1());
       }
       if(m%4 ==3 )
       {
          p4.setText(questionP.getPropo3());
          p1.setText(questionP.getReponse());
          p3.setText(questionP.getPropo2());
          p3.setText(questionP.getPropo1());
       }
    }    
    
     public void confirmer() throws IOException
    {
            if(p1.isSelected()) this.answer = p1.getText();
            else
            {
                if(p2.isSelected()) this.answer = p2.getText();
                else
                {
                    if(p3.isSelected()) this.answer = p3.getText();
                    else
                    {
                        if(p4.isSelected()) this.answer = p4.getText();
                    }
                }
                
            }
          System.out.println("this is it : "+this.answer);
          
         ans = QuestMoyenController.answer;
         Partie.answered = true;
         n= this.compterPoints(questionP, this.answer, Jcourant, Difficultés.Moyen);
         System.out.println("compatage ponints "+n);
         int advance=0;
         
         if(n!=0)
         {
             
        Stage stage = new Stage();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("deplacement.fxml"));
        DeplacementController controller = (DeplacementController) myLoader.getController();
            controller.number =n ;
           
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
 
            A= Jcourant;
            Jcourant = B;
        }
        else
        {
        if(Jcourant.getNom().equals(B.getNom()))
        {    
             B= Jcourant;
            Jcourant = A;
        }
        }
          Partie.Namej.setText(Jcourant.getNom());    
         }
        }
    
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
