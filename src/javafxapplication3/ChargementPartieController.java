/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Code.AlerteBox;
import Code.Case;
import Code.CaseCh;
import Code.Commune;
import Code.Difficultés;
import Code.Domaine;
import Code.Individuelle;/// Random pour le choix de l'emplacement de la réponse
import Code.Joueur;
import Code.Operations_Fichiers;
import Code.Question;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author saiyen
 */
public class ChargementPartieController implements Initializable ,Serializable{ /// Controleur de l'affichage pour charger une partie
    
public static ArrayList<Case> parcours; /////////// Le parcours 
@FXML 
ScrollPane sc;
@FXML 
        
 AnchorPane part ;

 TreeSet<Domaine> dom;
 public static Joueur A,B ;
 public static Joueur Jcourant = new Joueur();
 public static TreeSet<Question> Questions;   // Les questions a poser //
 Operations_Fichiers f = new Operations_Fichiers();
 
 public static boolean answered = false;
 
 @FXML 
 HBox nomj;
 public static Label Namej; // Nom du joueur //
 @FXML
 Label JA,JB;   // Affichage des noms //
 @FXML
 GridPane gridPane ;
 @FXML
 StackPane stackPane;
 
 public static String ans;
  
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) /// Initialisateur
    {         
    JA.setText(A.getNom());
    JB.setText(B.getNom());
   //chargerQuestions();/// Charger les questions du fichier dans la structure
   chargerParcours();//// Charger le parcours sous forme de y dans la structure
   aleaDomaines();/// définir aléatoirement les domaines de chaque case avec un code couleur
  
    Namej = new Label();
    Namej.setId("label");
    Namej.setText("Le tour est à : "+Jcourant.getNom());
    nomj.getChildren().add(Namej);
     
           
   }
    /*public void chargerQuestions()
    {
    Questions = new ArrayList<>();
    Question q1 = new Question("Qui est aziz ?","Aziz","donnow","??","hi",Domaine.CultureGénerale);
    Question q2 = new Question("Qui est Adel ?","??","donnow","wow","Adel",Domaine.CultureGénerale);
    Question q3 = new Question("Qui est Mohamed ?","??","donnow","wow","Mohamed",Domaine.CultureGénerale);
    Questions.add(q1);
    Questions.add(q2);
    Questions.add(q3);
    }*/
    
    
    public void chargerParcours() /// Charger le parcours
    {
        Iterator<Node>  it = gridPane.getChildren().iterator();
    while(it.hasNext())
    {
        this.parcours.add((Case)it.next());  // Parcourer les cases et ajout au parcours //
    }
            Comparator<Case> comp = (Case o1, Case o2) -> {
            int m = 0;
            Case e1 = (Case) o1;
            Case s = (Case) o2;
            if (e1.getNuméro() == s.getNuméro()) {
                m=0;
            }
            if (e1.getNuméro() > s.getNuméro()) {
                m =1;
            }
            if (e1.getNuméro() < s.getNuméro()) {
                m =-1;
            }
            return m;
        };
       this.parcours.sort(comp);
    }
    public void aleaDomaines() /// Donner une couleur à chaque domaine
    {
           Iterator<Case>  itr = this.parcours.iterator();  // Parcourer les cases // 
   while(itr.hasNext())
   {
       Case e = (Case) itr.next();
           // Les cases des trancons individuels //
       {     
       if(e.getDomaine() == Domaine.Science)
       {
            e.setStyle("-fx-background-color: green;"
           
                + " -fx-background-radius: 50.0"); 
           e.setTooltip(new Tooltip("Science"));   
       }
       if(e.getDomaine() == Domaine.Cinéma)
       {
           
            e.setStyle("-fx-background-color: red;"
             
                + " -fx-background-radius: 50.0"); 
            e.setTooltip(new Tooltip("Cinéma"));
       }
       if(e.getDomaine()== Domaine.CultureGénerale)
       {
           ;
           e.setStyle("-fx-background-color: blue;"
               
                + " -fx-background-radius: 50.0"); 
           e.setTooltip(new Tooltip("Culture Génerale"));
       }
       if( e.getDomaine()== Domaine.Géographie )
       {
          
            e.setStyle("-fx-background-color: white;"
             
                + " -fx-background-radius: 50.0"); 
            e.setTooltip(new Tooltip("Géographie"));
       }
       if( e.getDomaine()== Domaine.Musique )
       {
           
            e.setStyle("-fx-background-color: yellow;"
            
                + " -fx-background-radius: 50.0"); 
            e.setTooltip(new Tooltip("Musique"));
       }
       if(e.getDomaine()== Domaine.Politiques )
       {
          
           e.setStyle("-fx-background-color: pink;"
                
                + " -fx-background-radius: 50.0"); 
           e.setTooltip(new Tooltip("Politiques"));
           
       }
       if(e.getDomaine()== Domaine.Sport )
       {
         e.setStyle("-fx-background-color: gray;"
            
                + " -fx-background-radius: 50.0"); 
           e.setTooltip(new Tooltip("Sport"));
       }
       
       }
        
    }  
    }

       
    public void Cliquer_Case(MouseEvent e) /// Gestion du clique sur les cases du  parcours
    {
        Case c = (Case)e.getSource();  // La case ou on a cliqué //
        System.out.println(c.getNuméro());
        c.setOccupant(null);

        if(((1<=c.getNuméro()&& c.getNuméro()<=21) || (43<=c.getNuméro()&& c.getNuméro()<=63)))
        {
                
             if(1<=c.getNuméro()&& c.getNuméro()<=21)
             {
                c.setOccupant(A);
             }
            else
             {
               c.setOccupant(B); 
             }
               
              if( c.getOccupant().equals(Jcourant) && c.getNuméro() == Jcourant.getAvancement())
               {
              
                    c.setQuestion(Questions.pollFirst());
                    DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.DODGERBLUE);
                    c.setEffect(shadow);
                    shadow.setWidth(2);
                    
                     FXMLLoader myLoader = new FXMLLoader(getClass().getResource("LevelCh.fxml"));
                     LevelChController controller = new LevelChController();
                     controller.setQuestionP(Questions.pollFirst());
                  
                     
                     
               try {
        Parent myPane = (Parent)myLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        
               } catch (IOException ex) {
        Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
              }
          
               
               }
               else 
              {
           
                /* Rien ne se passe */
                AlerteBox a = new AlerteBox();
                AlerteBox.infoBox("Vous n'avez pas le droit à accéder à cette case", "Erreur");
                
            
               }
        }
        else
        {
        if(c.getNuméro()<=42 && c.getNuméro()>=22)
        {
            
         if(c.getOccupant()==null)
          {
                 c.setOccupant(Jcourant);
 
                 if( c.getNuméro() == Jcourant.getAvancement())
                 {
                    c.setQuestion(Questions.pollFirst());
                    DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.DODGERBLUE);
                    c.setEffect(shadow);
                    shadow.setWidth(2);
                    System.out.println("hi");
                    
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Question.fxml"));
            NiveauController controller = new NiveauController();
            controller.setQuestionP(Questions.pollFirst());
     
               try {
        Parent myPane = (Parent)myLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        
               } catch (IOException ex) {
        Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
              }
          
               
               }
               else 
              {
           
                /* Rien ne se passe */
                AlerteBox a = new AlerteBox();
                AlerteBox.infoBox("Vous n'avez pas le droit à accéder à cette case", "Erreur");
                
            
               }            
                     
               }
            
            else
            {
                
                /// Lancer le challenge
                
                
                
            }
              
        }
            
        }
            
        }
      
     
    
    //// Calculer les points gagnés par chaque joueur 
    public int compterPoints(Question question,String reponse,Joueur player,Difficultés niveau)
    {
    int n = 0;
    if(reponse!=null && answered)
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
   
    
  /// Animation
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
  
    /// Sauvegarder une partie
     public void Sauvegarder_partie()
     {
      
       Code.Partie p = new Code.Partie();
       ArrayList<CaseCh> cases = new  ArrayList<CaseCh>();
        p.setA(A);
        p.setB(B);
        p.setFirst(Jcourant);
        p.setId("0");
        Iterator<Case> it =parcours.iterator();
        
        while(it.hasNext())
        {
           Case c = it.next();
           CaseCh e = new CaseCh();
           e.setColor(c.getStyle());
           e.setDomaine(c.getDomaine());
           e.setNuméro(c.getNuméro());
           e.setPlayed(c.isPlayed());
           e.setOccupant(c.getOccupant());
           e.setQuestion(c.getQuestion());
           cases.add(e);
        }
        p.setParcours(cases);
        p.setNuméro(0);
        p.setQuestions(Questions);
        f.Sauvegarder(p);
       
     }
     /// Charger une partie
     public void ChargerPartie()
    {
        Code.Partie p = new Code.Partie();
        Operations_Fichiers f = new Operations_Fichiers();
          p = f.Ouvrir(p);
          FXMLLoader myLoader = new FXMLLoader(getClass().getResource("ChargementPartie.fxml"));
          ChargementPartieController controller = (ChargementPartieController) myLoader.getController();
            controller.A = p.getA();
            controller.B = p.getB();
            controller.Jcourant = p.getFirst();
            controller.Questions = p.getQuestions();
          //  controller.parcours = p.getParcours();
          
          controller.parcours = new ArrayList<Case>();
          
      try {
          Parent myPane = (Parent)myLoader.load();
          Iterator<CaseCh> it = p.getParcours().iterator();
          Iterator<Case> itr = controller.parcours.iterator();
          while(it.hasNext() && itr.hasNext())
          {
              CaseCh c = it.next();
              Case e = itr.next();
              System.out.println(c.getColor());
              e.setDomaine(c.getDomaine());
              e.setStyle(c.getColor());
              e.setPlayed(c.isPlayed());
              if(e.isPlayed())
            {
                 DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.CRIMSON);
                    e.setEffect(shadow);
                    shadow.setWidth(5);
                
                
            }
           /* else
            {
                 DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.DODGERBLUE);
                    e.setEffect(shadow);
                    shadow.setWidth(5);   
            }*/    
          }
   
        Stage stage = new Stage();
        Scene scene = new Scene(myPane);
        scene.getStylesheets().add(ChargementPartieController.class.getResource("caspian.css").toExternalForm());
        stage.setScene(scene);
        //stage.initStyle(StageStyle.TRANSPARENT);
        System.out.println("HIIII");
        stage.setFullScreen(false);
        stage.show();
           }
      catch (IOException ex) {
        Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
              }   
    }
     
        public void Challenge(Joueur A , Joueur B)
    {
        
    }
        /// Fermer une partie 
        public void Fermer()
        {
            Sauvegarder_partie();
            exit(0);
        }
}
