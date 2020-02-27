/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Code.AlerteBox;
import Code.Case;
import Code.Domaine;
import Code.Joueur;
import Code.Operations_Fichiers;
import Code.Question;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javafxapplication3.DeplacementController.pointOc;
import static javafxapplication3.JavaFXApplication3.collectionJoueur;
import static javafxapplication3.NiveauController.questionP;
import static javafxapplication3.Partie.A;
import static javafxapplication3.Partie.B;
import static javafxapplication3.Partie.Jcourant;
import static javafxapplication3.Partie.Questions;
;
import static javafxapplication3.Partie.parcours;
import static javafxapplication3.QuestFacileController.n;
;

/**
 * FXML Controller class
 *
 * @author saiyen
 */
public class DeplacementController implements Initializable { /// Controleur du deplacement pour la partie

    /**
     * Initializes the controller class.
     */
   public static int pointOc = 0;
   public static  int pointCh = 0;
   public static  int Nbr_Qoc = 0;
   public static  int Nbr_QCh = 0;
    @FXML
    TextField deplacement = new TextField();
    int dep =0;
    
    @FXML
    Button conf = new Button();
    public static int number;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { // Initialisateur
        // TODO
           conf.setDisable(true);
                   /// Listner pour la confirmation 
      ChangeListener<String> textListener = new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                 conf.setDisable(
                       deplacement.getText() == null ||deplacement.getText().isEmpty() ||Integer.parseInt(deplacement.getText())<0||Integer.parseInt(deplacement.getText())>number 
                               || (Integer.parseInt(deplacement.getText())+Jcourant.getAvancement()>42 && Jcourant.getNom().equals(A.getNom()))
                               || (Integer.parseInt(deplacement.getText())+Jcourant.getAvancement()-42>42 && Jcourant.getNom().equals(B))
                      );
                }
            };
       deplacement.textProperty().addListener(textListener);
    }    
    public void get_dep(ActionEvent e) /// Se déplacer sur le trançon
    {
        ChoiceController.lastpos = Jcourant.getAvancement();
        dep =Integer.parseInt(deplacement.getText());
        int s =Jcourant.getAvancement()-1;
        Jcourant.setAvancement(Jcourant.getAvancement()+dep);
        if(Jcourant.getAvancement()>63)
        {
            
            int j = Jcourant.getAvancement() - 63;
            Jcourant.setAvancement(21+j);
        }
        ChoiceController.newpos = Jcourant.getAvancement();
        System.out.println("la nouvelle position"+ChoiceController.newpos);
        Case c =parcours.get(Jcourant.getAvancement()-1);
        DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.DODGERBLUE);
                    c.setEffect(shadow);
                    shadow.setWidth(5);
    if((c.getNuméro()<=21 && c.getNuméro()>=1) ||( c.getNuméro()>=43 && c.getNuméro()<=63) || (c.getNuméro()>=22 && c.getNuméro()<=42 && c.getOccupant() == null) )
    {
        c.setOccupant(Jcourant);
        c.setPlayed(true);
    }
    else
    {
       
    if(c.getNuméro()<=42 && c.getNuméro()>=22 && c.getOccupant()!=null)
          {
                System.out.println("L'occupant de cette case est"+c.getOccupant().getNom());
                /// Lancer le challenge
  
                     
                 
                 FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Duel.fxml"));
                 DuelController controller = (DuelController) myLoader.getController();
                 controller.Oc = c.getOccupant();
                 
                 if(c.getOccupant().getNom().equals(A.getNom()))
                 {
                     controller.Ch = B;
                 }
                 else
                 {
                     if(c.getOccupant().getNom().equals(B.getNom()))
                 {
                     controller.Ch = A;
                 }
                 }
                 pointOc = 0;
                 pointCh = 0;
                 Nbr_Qoc = 0;
                 Nbr_QCh = 0;
               
                    
                 TreeSet<Domaine> tr = c.getOccupant().getDomaines();
                 Domaine m = tr.pollFirst();
                 Iterator<Question> it= Questions.iterator();
                 while(it.hasNext())
                 {
                     Question q = it.next();
                     if(q.getDomaine() == m)
                     {
                          controller.questionP = q;
                          it.remove();
                     }
                 }
               /// Choisir une question du domaine de l'occpant
                           
               try {
               Parent myPane = (Parent)myLoader.load();
               Stage stage = new Stage();
               Scene scene = new Scene(myPane);
               stage.setScene(scene);
               stage.setFullScreen(false);
               stage.show();
        
               } catch (IOException ex) 
               {
             Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                }
    }
         /// Changer les roles     
       
       
       if(Jcourant.getNom().equals(A.getNom()))
        {  
 
            A= Jcourant;
        int nw = Jcourant.getAvancement()-2;
    
        while(nw>= 0)
        {
        Case m = parcours.get(nw);
        m.setOccupant(null);
        DropShadow shadows = new DropShadow();
                    shadows.setColor(Color.CRIMSON);
                    m.setEffect(shadows);
        
                    shadows.setWidth(5);
                    nw--;
        }
            Jcourant = B;
         
                    shadow.setColor(Color.DODGERBLUE);
                    shadow.setWidth(5);
        parcours.get(Jcourant.getAvancement()-1).setEffect(shadow);
      //  parcours.get(Jcourant.getAvancement()-1).setOccupant(A);
        }
        else
        {
        if(Jcourant.getNom().equals(B.getNom()))
        {    
             B= Jcourant;
        int nw = Jcourant.getAvancement()-2;
        if(Jcourant.getAvancement()<=63 && Jcourant.getAvancement()>=43)
        {
        while(nw>=42)
        {
        Case m = parcours.get(nw);
        m.setOccupant(null);
        DropShadow shadows = new DropShadow();
                    shadows.setColor(Color.CRIMSON);
                    m.setEffect(shadows);
        
                    shadows.setWidth(5);
                    nw--;
        }
        
        }
        else
        {
            
            while(nw>=22)
        {
        Case m = parcours.get(nw);
        m.setOccupant(null);
        DropShadow shadows = new DropShadow();
                    shadows.setColor(Color.CRIMSON);
                    m.setEffect(shadows);
        
                    shadows.setWidth(5);
                    nw--;
            
            
        }
        }
        
            Jcourant = A;
           
                    shadow.setColor(Color.DODGERBLUE);
                    shadow.setWidth(5);
        parcours.get(Jcourant.getAvancement()-1).setEffect(shadow);
        //parcours.get(Jcourant.getAvancement()-1).setOccupant(B);
        
        }
        }
        System.out.println("le joueur en cours "+Jcourant.getNom()+" Son avancement"+Jcourant.getAvancement());
        Partie.Namej.setText("Le tour est à : "+Jcourant.getNom());
 /// Déclarer un gagnat à la fin de la partie
           if(Jcourant.getAvancement()>=42)
           {
               if(Jcourant.getNom().equals(A.getNom()))
               {
                AlerteBox a = new AlerteBox();
                AlerteBox.infoBox(Jcourant.getNom()+" Vous Avez Gagné", "Félicitations");
                Jcourant.setScore(Jcourant.getScore()+1);
                Iterator<Joueur> it = collectionJoueur.iterator();
                while(it.hasNext())
                {
                    Joueur l = it.next();
                    if(l.getNom().equals(Jcourant.getNom()))
                    {
                        l.setScore(Jcourant.getScore());
                    }
                }
                Operations_Fichiers f = new Operations_Fichiers();
                f.EnregistrerJ(collectionJoueur);
               }
               else
               {
                   if(Jcourant.getNom().equals(B.getNom()))
               {
                   if(Jcourant.getAvancement()>=22 && Jcourant.getAvancement()<=42)
                   {
                       if(ChoiceController.lastpos+dep ==42)
                       {
                       AlerteBox a = new AlerteBox();
                       AlerteBox.infoBox(Jcourant.getNom()+" Vous Avez Gagné", "Félicitations");
                       Jcourant.setScore(Jcourant.getScore()+1);
                       Iterator<Joueur> it = collectionJoueur.iterator();
                        while(it.hasNext())
                        {
                    Joueur l = it.next();
                    if(l.getNom().equals(Jcourant.getNom()))
                    {
                        l.setScore(Jcourant.getScore());
                    }
                       }
                    Operations_Fichiers f = new Operations_Fichiers();
                    f.EnregistrerJ(collectionJoueur);
                       }
                   }
               }
                   
               }
           }
           
        
    }
    
}
