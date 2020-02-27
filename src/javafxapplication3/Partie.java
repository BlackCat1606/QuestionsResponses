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
public class Partie implements Initializable ,Serializable{ /// Controleur de la nouvelle partie
    
public static ArrayList<Case> parcours; /////////// Le parcours 
@FXML 
ScrollPane sc;
@FXML 
     
 AnchorPane part ;
 

  Stage prevStage = new Stage();

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }


 public int m;
 public boolean nouvelle ;
 TreeSet<Domaine> dom;
 public static Joueur A,B; 
 public static Joueur Jcourant = new Joueur();
 public static TreeSet<Question> Questions;   // Les questions a poser //
 Operations_Fichiers f = new Operations_Fichiers();
 
 public static boolean answered = false;
 
 @FXML 
 HBox nomj;
 public static Label Namej; // Nom du joueur //
 @FXML
  Label JA ,JB ;  // Affichage des noms //
 @FXML
 GridPane gridPane ;
 @FXML
 StackPane stackPane;
 
 public static String ans;

    public static Joueur getA() {
        return A;
    }

    public static void setA(Joueur A) {
        Partie.A = A;
    }

    public static Joueur getB() {
        return B;
    }

    public static void setB(Joueur B) {
        Partie.B = B;
    }
  
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {      if(m ==0)
    {
        nouvelle = true;
    }
    else
    {
        nouvelle = false;
    }
        if(nouvelle)
        {
    parcours = new ArrayList<Case> () ;
//    dom = new TreeSet() ;    // TreeSet de domaines 
//    dom.add(Domaine.Sport);
//    dom.add(Domaine.Géographie);
//    dom.add(Domaine.Cinéma);
//    A = new Joueur("A",dom,0,0);
//    A.setDomaines(dom);
//    B = new Joueur("B",dom,0,0);
    Questions = new TreeSet<Question>();
   
    JA.setText(A.getNom());
    JB.setText(B.getNom());
    
        try {
            chargerQuestions();/// Charger les questions du fichier dans la structure
        } catch (IOException ex) {
            Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
        }
    chargerParcours();//// Charger le parcours sous forme de y dans la structure
    aleaDomaines();/// définir aléatoirement les domaines de chaque case avec un code couleur
    choisirJoueur();/// Choisir le joueur qui commence la partie
    Namej = new Label();
    Namej.setText("Le tour est à : "+Jcourant.getNom());
    Namej.setId("label");
   
    nomj.getChildren().add(Namej);
        }
        else
        {
        try {
            chargerQuestions();/// Charger les questions du fichier dans la structure        
        } catch (IOException ex) {
            Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
        }
    Namej = new Label();
    Namej.setText("Le tour est à : "+Jcourant.getNom());
    nomj.getChildren().add(Namej);
            
        }
     
           
   }
    public void chargerQuestions() throws IOException
    {
    Questions = new TreeSet<>();
    Questions = f.Ouvrir_Questions(Questions);
    }
    
    
    public void chargerParcours()
    {
        Iterator<Node>  it = gridPane.getChildren().iterator();
    while(it.hasNext())
    {
        parcours.add((Case)it.next());  // Parcourer les cases et ajout au parcours //
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
        parcours.sort(comp);
    }
    public void aleaDomaines()
    {
           Iterator<Case>  itr = parcours.iterator();  // Parcourer les cases // 
   while(itr.hasNext())
   {
       Case e = (Case) itr.next();
       
       int k = (int)(100*Math.random());
       if(( 0 < e.getNuméro()&& e.getNuméro() < 22) || (42<e.getNuméro() && e.getNuméro()<64))
           // Les cases des trancons individuels //
       {     
       if(k%7 == 0)
       {
           e.setDomaine(Domaine.Science);
            e.setStyle("-fx-background-color: green;"
           
                + " -fx-background-radius: 50.0"); 
           e.setTooltip(new Tooltip("Science"));
            e.setColor(e.getStyle());
           
       }
       if(k%7 == 1)
       {
           e.setDomaine(Domaine.Cinéma);
            e.setStyle("-fx-background-color: red;"
             
                + " -fx-background-radius: 50.0"); 
            e.setTooltip(new Tooltip("Cinéma"));
            e.setColor(e.getStyle());
       }
       if(k%7 == 2)
       {
           e.setDomaine(Domaine.CultureGénerale);
           e.setStyle("-fx-background-color: Crimson;"
               
                + " -fx-background-radius: 50.0"); 
           e.setTooltip(new Tooltip("Culture Génerale"));
           e.setColor(e.getStyle());
       }
       if(k%7 == 3)
       {
           e.setDomaine(Domaine.Géographie);
            e.setStyle("-fx-background-color: BURLYWOOD;"
             
                + " -fx-background-radius: 50.0"); 
            e.setTooltip(new Tooltip("Géographie"));
            e.setColor("-fx-background-color: white;"
             
                + " -fx-background-radius: 50.0");
       }
       if(k%7 == 4 )
       {
           e.setDomaine(Domaine.Musique);
            e.setStyle("-fx-background-color: yellow;"
            
                + " -fx-background-radius: 50.0"); 
            e.setTooltip(new Tooltip("Musique"));
             e.setColor(e.getStyle());
       }
       if(k%7 == 5)
       {
           e.setDomaine(Domaine.Politiques);
           e.setStyle("-fx-background-color: pink;"
                
                + " -fx-background-radius: 50.0"); 
           e.setTooltip(new Tooltip("Histoire"));
            e.setColor(e.getStyle());
           
       }
       if(k%7 == 6)
       {
           e.setDomaine(Domaine.Sport); e.setStyle("-fx-background-color: gray;"
            
                + " -fx-background-radius: 50.0"); 
           e.setTooltip(new Tooltip("Sport"));
            e.setColor(e.getStyle());
       }
       
       }
        
    }  
    }
    public void choisirJoueur()
    {
      int n =  (int) (100* Math.random());
      if(n%2 == 0) 
      {
          
        A.setAvancement(1);
        B.setAvancement(43);
        Jcourant= A;
        Jcourant.setNom(A.getNom());
        DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.DODGERBLUE);
                    shadow.setWidth(5);
        parcours.get(Jcourant.getAvancement()-1).setEffect(shadow);
      }
      
      else
      {
        B.setAvancement(43);
        A.setAvancement(1);
         Jcourant = B;
         Jcourant.setNom(B.getNom());
         DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.DODGERBLUE);
                    shadow.setWidth(5);
        parcours.get(Jcourant.getAvancement()-1).setEffect(shadow);
      }
    }
    
    
    public void Cliquer_Case(MouseEvent e)
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
              
                   
     
                    DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.DODGERBLUE);
                    c.setEffect(shadow);
                    shadow.setWidth(2);
                    
                     FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Question.fxml"));
                     NiveauController controller = new NiveauController();
                     Question m = new Question();
                     m.setDomaine(c.getDomaine());
                     Iterator <Question> itr = Questions.iterator();
                     while(itr.hasNext())
                     {
                         Question q =  itr.next();
                         if(q.getDomaine() == c.getDomaine())
                         {
                             controller.setQuestionP(q);
                             itr.remove();
                         }
                     }
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
           //System.out.println("L'occupant de cette case est"+c.getOccupant().getNom());
         if(c.getOccupant()==null)
          {
                   c.setOccupant(Jcourant);
                   System.out.println("Le joueur en cours est :"+Jcourant.getNom());
                   System.out.println("l'avancement du joueur en cours est :"+Jcourant.getAvancement());
                 if( c.getNuméro() == Jcourant.getAvancement())
                 {
                    c.setQuestion(Questions.pollFirst());
                    DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.DODGERBLUE);
                    c.setEffect(shadow);
                    shadow.setWidth(2);
                    System.out.println("Vous étes sur le parcours commun");
            
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Question.fxml"));
            NiveauController controller = new NiveauController();
            Iterator <Question> itr = Questions.iterator();
                     while(itr.hasNext())
                     {
                         Question q =  itr.next();
                         if(q.getDomaine() == c.getDomaine())
                         {
                             controller.setQuestionP(q);
                             itr.remove();
                         }
                     }
            
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
                
                
                System.out.println("L'occupant de cette case est"+c.getOccupant().getNom());
                /// Lancer le challenge
                int pointOc = 0;
                int pointCh = 0;
                int Nbr_Qoc = 0;
                int Nbr_QCh = 0;
                while(Math.abs(pointOc-pointCh)<2 ||  (Nbr_Qoc <3 &&Nbr_QCh<3) )
                {
                    
                  FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Duel.fxml"));
                  DuelController controller = (DuelController) myLoader.getController();
                  
                  Domaine m = c.getOccupant().getDomaines().first();
                  controller.setQuestionP(Questions.pollFirst());/// Choisir une question du domaine de l'occpant
                  
                 
                  
     
               try {
          Parent myPane = (Parent)myLoader.load();
           showModalMessage(myPane);
        
               } catch (IOException ex) {
             Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
              }
                                   
                }
               
                
                
                
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
   
    
    public void lancerPartie()
    {
       choisirJoueur();
      // chargerQuestions();
    }

   /* public void chargerQuestions()
    {
        f.Ouvrir_Questions(Questions);
    }*/
    
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
    public void ChargerPartie()
    {
        Code.Partie p = new Code.Partie();
        
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
        System.out.println("HIIII");
        stage.setFullScreen(false);
        stage.show();
           }
      catch (IOException ex) {
        Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
              }   
    }
     
    
    
    
   
       
        public void Fermer()
        {
            Sauvegarder_partie();
            exit(0);
        }
}
