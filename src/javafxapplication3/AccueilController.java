package javafxapplication3;

import Code.Case;
import Code.CaseCh;
import Code.Domaine;
import Code.Joueur;
import Code.Operations_Fichiers;
import java.awt.PopupMenu;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;
import static javafxapplication3.JavaFXApplication3.collectionJoueur;
import javax.swing.JFrame;
import static javafxapplication3.JavaFXApplication3.collectionJoueur;



public class AccueilController implements Initializable{

//    ArrayList<Joueur> collectionJoueur = new ArrayList<Joueur> ();
    ObservableList<String> data;
    public void setJoueurList()
    {
        
        data = FXCollections.observableArrayList();
        
          for (Joueur j : collectionJoueur) 
            {
                System.out.println("modification "+j.getNom());
                 data.add(j.getNom());
            }
     
        joueur1.setItems(data);   // Mettre les joueur1 dans le ComboBox //
        joueur2.setItems(data);   // Mettre les joueur2 dans le ComboBox //
    }

    public AccueilController() { //// Constructeur de l'acceuil
    }
    
    @FXML
    VBox vbox;
    
    @FXML
    Button play;
    
    @FXML
    Button newGameBtn;
    
    @FXML
    StackPane stackPane;
    
    @FXML
    VBox creation;
    
    @FXML
    VBox selection;
    
    @FXML
    Button newPlayer;
    
    @FXML
    Button modifier1;
    
    @FXML
    Button modifier2;
    
    @FXML
    AnchorPane nouvellePartie;
        
    @FXML
    ListView ListViewJ1;
   
     @FXML
    ListView ListViewJ2;
     
    @FXML
    ComboBox joueur1;
    
    @FXML
    ComboBox joueur2;
    
    @FXML
    Label J1Label;
     
    @FXML
    Label J2Label;
    
    @FXML
    Button suppr;
    
    @FXML
    Button chargerBtn;
    
    @FXML
    Button Mscore;
    
    @FXML
    Button aideBtn;
    
    @FXML
    Button exitBtn;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { /// Initialisateur de l'interface
   
      setJoueurList();
      
        stackPane.setOnMouseClicked(e-> {
                hideModalMessage();
        });
        
        nouvellePartie.setVisible(false);
        vbox.setVisible(true);
        
        
        ChangeListener<String> joueurListener = new ChangeListener<String>() {/// Listner pour les domaines du joueur 1 
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ((verifierJoueur((joueur1.getValue()).toString()) != null) &&(verifierJoueur((joueur1.getValue()).toString()).size()>2))
                    {
                        J1Label.setText((joueur1.getValue()).toString());

                        ListViewJ1.setItems(verifierJoueur((joueur1.getValue()).toString()));
                        ListViewJ1.setOpacity(1);
                    }
                else {
                J1Label.setText("Joueur1");
                ListViewJ1.setItems(FXCollections.observableArrayList("domaine1","domaine2","domaine3"));
                ListViewJ1.setOpacity(0.5);
                }
            }
        };
        
        
        ChangeListener<String> joueurListener2 = new ChangeListener<String>() {// Listner pour les domaines du joueur 2
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ((verifierJoueur((joueur2.getValue()).toString()) != null) &&(verifierJoueur((joueur2.getValue()).toString()).size()>2))
                    {
                        J2Label.setText((joueur2.getValue()).toString());
//                        final Image image2 = new Image(JavaFXApplication3.class.getResourceAsStream("tp_2.images/j.png"));
                        ListViewJ2.setItems(verifierJoueur((joueur2.getValue()).toString()));
                        ListViewJ2.setOpacity(1);
                    }
                else {
          //      final Image image2 = new Image(JavaFXApplication3.class.getResourceAsStream("images/images.png"));
                J2Label.setText("Joueur2");
                ListViewJ2.setItems(FXCollections.observableArrayList("domaine1","domaine2","domaine3"));
                ListViewJ2.setOpacity(0.5);
                }
            }
        };
        joueur1.valueProperty().addListener(joueurListener);
        joueur2.valueProperty().addListener(joueurListener2);
        
        play.setDisable(true);
        ChangeListener<String> playListener = new ChangeListener<String>() {// Listner pour les zones de textes des joueurs
          @Override
          public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
          
          try
          {
          play.setDisable(joueur1.getValue().equals(joueur2.getValue().toString()));
          }
          catch (Exception e)
          { 
          }}
      };
        
      joueur1.valueProperty().addListener(playListener);
      joueur2.valueProperty().addListener(playListener);
      
      
    }
    
   
    
    
    public void transl(){/// petite animation
        
        TranslateTransition tr = new TranslateTransition(Duration.millis(1000), vbox);
        
        if (vbox.getTranslateX()== 0){
                
                tr.setFromX(0);
                tr.setToX(-200);
                tr.play();
               
        }
        
         nouvellePartie.setVisible(true);
         FadeTransition ft = new FadeTransition(Duration.millis(2000),nouvellePartie);
         ft.setFromValue(0);
         ft.setToValue(1);
         ft.play();
                
        
    }
    
    public void createNewPlayer(){ // Créer un nouveau joueur 
        
        showModalMessage(new NewPlayer());
    
    }
    
    public void showModalMessage(Node message) {/// Animation
        stackPane.getChildren().add(message);
        stackPane.setOpacity(0);
        stackPane.setVisible(true);
        stackPane.setCache(true);
        TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.seconds(1), (ActionEvent t) -> {
                    stackPane.setCache(false);
                },
                        new KeyValue(stackPane.opacityProperty(), 1, Interpolator.EASE_BOTH)
                )).build().play();
    }   
    
    public void hideModalMessage() {/// Animation
        stackPane.setCache(true);
        TimelineBuilder.create().keyFrames(
                new KeyFrame(Duration.seconds(1), (ActionEvent t) -> {
                    stackPane.setCache(false);
                    stackPane.setVisible(false);
                    stackPane.getChildren().clear();
                },
                        new KeyValue(stackPane.opacityProperty(), 0, Interpolator.EASE_BOTH)
                )).build().play();
    }

        public void lancerPartie() throws IOException{/// Lancer une nouvelle partie
       
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FenetreP.fxml"));

        Partie controller = (Partie) myLoader.getController();
//        controller.joueurA=Joueur1.getValue().toString();
        
        //menu m = new menu();
        for (Joueur j : collectionJoueur) 
        {
                 
            if (j.getNom().equalsIgnoreCase(joueur1.getValue().toString()))
                      {
                      Partie.A= new Joueur();
                      Partie.A.setNom(j.getNom());
                      Partie.A.setAvancement(0);
                      Partie.A.setScore(j.getScore()); 
                      Partie.A.setDomaines(j.getDomaines());
                      
                      }
                 if (j.getNom().equalsIgnoreCase(joueur2.getValue().toString()))
                 {
                      Partie.B= new Joueur(); 
                      Partie.B.setNom(j.getNom());
                      Partie.B.setAvancement(0);
                      Partie.B.setScore(j.getScore()); 
                      Partie.B.setDomaines(j.getDomaines());
                 }
         }
        
        Parent myPane = (Parent) myLoader.load();
        Scene scene = new Scene(myPane);
        scene.getStylesheets().add(Partie.class.getResource("caspian.css").toExternalForm());
        Stage primaryStage = new Stage();
        primaryStage.setTitle("TP 2");
        primaryStage.setScene(scene);
     //   primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    

    
    }
    
    
    
    public ObservableList<Domaine>  verifierJoueur(String joueur) //verifier si 'joueur' existe, retourne par consequence
                                                                  //une liste des domaines appropries 
    {
        ObservableList<Domaine> domaine =FXCollections.observableArrayList();
        
          for (Joueur j : collectionJoueur) 
          {
                 if (j.getNom() == joueur) {
                     for (Domaine s : j.getDomaines())
                     {
                     domaine.add(s);
                     }
                 }
          }
    return domaine;
    }
    
    
    
    
    //---------------------------------------------------classe interne
    class NewPlayer extends VBox { /// Fenétre pour nouveau joueur

        public NewPlayer() {
                setMaxSize(430, 270);
                setId("creation");
                
        // block mouse clicks
        setOnMouseClicked((MouseEvent t) -> {
            t.consume();
        });
         
            setSpacing(20);
            
            TextField nom = new TextField();
            nom.setPromptText("Donnez Le nom du joueur");
            nom.setLayoutX(26.0);
            nom.setLayoutY(20.0);
            
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setLayoutX(25.0);scrollPane.setLayoutY(103.0);scrollPane.setPrefSize(150.0,182.0);
            AnchorPane a = new AnchorPane();
            a.setMinSize(0.0, 0.0); a.setPrefSize(133.0, 175.0);
            
            CheckBox[] tab = new CheckBox[7];
            
            CheckBox d1 = new CheckBox("Science"); d1.setLayoutX(8.0); d1.setLayoutY(14.0);     tab[0]=d1;
            CheckBox d2 = new CheckBox("Politiques"); d2.setLayoutX(8.0); d2.setLayoutY(161.0);   tab[1]=d2;
            CheckBox d3 = new CheckBox("Géographie"); d3.setLayoutX(8.0); d3.setLayoutY(138.0); tab[2]=d3; 
            CheckBox d4= new CheckBox("Sport"); d4.setLayoutX(8.0); d4.setLayoutY(114.0);       tab[3]=d4;
            CheckBox d5 = new CheckBox("Musique"); d5.setLayoutX(8.0); d5.setLayoutY(88.0);     tab[4]=d5;
            CheckBox d6 = new CheckBox("Cinéma"); d6.setLayoutX(8.0); d6.setLayoutY(64.0);      tab[5]=d6;
            CheckBox d7 = new CheckBox("CultureGénerale"); d7.setLayoutX(8.0); d7.setLayoutY(41.0);     tab[6]=d7;
            
            
            a.getChildren().addAll(d1,d2,d3,d4,d5,d6,d7);
            
            scrollPane.setContent(a);
            
            Label label = new Label("Choisissez 3 domaines");
            label.setLayoutX(26.0);label.setLayoutY(86.0);
            
            Button okbutton = new Button("OK"); okbutton.setLayoutX(149.0);okbutton.setLayoutY(292.0);
            Button cancelbutton = new Button("cancel"); cancelbutton.setLayoutX(149.0);cancelbutton.setLayoutY(292.0);
            HBox hbox = new HBox(60);hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().addAll(okbutton,cancelbutton);
            
            cancelbutton.setOnMouseClicked(k->{hideModalMessage();});
           
            this.getChildren().addAll(nom,label,scrollPane,hbox);
            
            okbutton.setOnAction((ActionEvent e)->{
                
            Joueur J = new Joueur( );
            J.setNom(nom.getText());
            TreeSet<Domaine> tr = new TreeSet<>();
            Domaine[] d = new Domaine[7];
            int c=0;
            for(int i = 0;i<7;i++){
                String s = tab[i].getText();        
               
                if (tab[i].isSelected())
                {
                    d[c] = Domaine.valueOf(s);
                    tr.add(d[c]);
                    c++;
                }
            }
            
            
            hideModalMessage();
            
            
           J.setDomaines(tr);
         boolean trouve = false;
         for (Joueur j : collectionJoueur)
         {
             if (j.getNom().compareTo(J.getNom())==0) 
             {trouve = true; break;}
         }
         
         if (trouve == false)
         {
             collectionJoueur.add(J);
             Operations_Fichiers f = new Operations_Fichiers();
             f.EnregistrerJ(collectionJoueur);
             setJoueurList();
             hideModalMessage();
             
         }
            
         else
         {
             // Afficher l'erreur // User deja existant 
         }
        });
            ChangeListener<Boolean> textListener = new ChangeListener<Boolean>() 
            {
                   
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    int cpt=0;

                    for(int i = 0;i<7;i++){
                        if (tab[i].isSelected()) cpt++;
                      }
       
                        okbutton.setDisable(cpt!=3 || nom.getText().isEmpty());
                    }
            };
               
       
       d1.selectedProperty().addListener(textListener);
       d2.selectedProperty().addListener(textListener);
       d3.selectedProperty().addListener(textListener);
       d4.selectedProperty().addListener(textListener);
       d5.selectedProperty().addListener(textListener);
       d6.selectedProperty().addListener(textListener);
       d7.selectedProperty().addListener(textListener);       
       
           
    }
}
    public static class JoueurTemp /// Classe interne
    {
        private StringProperty Nomj,Score;

        private JoueurTemp(String Nom, String Score) {
            this.Nomj =new SimpleStringProperty(Nom);
            this.Score =new SimpleStringProperty(Score);
        }

        public StringProperty NomjProperty() {
            return Nomj;
        }

        public StringProperty ScoreProperty() {
            return Score;
        }
        
    }
    
    public void AfficherMscore() throws IOException/// Afficher le meilleur score
    {
        AnchorPane root = new AnchorPane();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Mscore.fxml"));
        root = (AnchorPane) myLoader.load();
        MscoreController controller = (MscoreController)myLoader.getController();
        
        TableColumn Player = new TableColumn("Nomj");
        Player.setCellValueFactory(new PropertyValueFactory("Nomj"));
     
        TableColumn Score = new TableColumn("Score");
        Score.setCellValueFactory(new PropertyValueFactory("Score"));

        ObservableList<JoueurTemp> donnees = FXCollections.observableArrayList();
            int i=0;
            for (Joueur j :collectionJoueur) 
            {
               donnees.add(new JoueurTemp(j.getNom(),String.valueOf(j.getScore())));
               System.out.println(donnees.get(i).Nomj);
               System.out.println(donnees.get(i).Score);i++;
            }


           controller.Scores.setItems(donnees);
           controller.Scores.getColumns().addAll(Player,Score);
           
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show(); 
        
        
        
    }
    
   public void modifier1() /// Modifier les domaines du joueur 1 
   {
        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        AnchorPane a = new AnchorPane();
            a.setMinSize(0.0, 0.0); a.setPrefSize(133.0, 175.0);
            
            CheckBox[] tab = new CheckBox[7];
            
            CheckBox d1 = new CheckBox("Science"); d1.setLayoutX(8.0); d1.setLayoutY(14.0);     tab[0]=d1;
            CheckBox d2 = new CheckBox("Politiques"); d2.setLayoutX(8.0); d2.setLayoutY(161.0);   tab[1]=d2;
            CheckBox d3 = new CheckBox("Géographie"); d3.setLayoutX(8.0); d3.setLayoutY(138.0); tab[2]=d3; 
            CheckBox d4= new CheckBox("Sport"); d4.setLayoutX(8.0); d4.setLayoutY(114.0);       tab[3]=d4;
            CheckBox d5 = new CheckBox("Musique"); d5.setLayoutX(8.0); d5.setLayoutY(88.0);     tab[4]=d5;
            CheckBox d6 = new CheckBox("Cinéma"); d6.setLayoutX(8.0); d6.setLayoutY(64.0);      tab[5]=d6;
            CheckBox d7 = new CheckBox("CultureGénerale"); d7.setLayoutX(8.0); d7.setLayoutY(41.0);     tab[6]=d7;
            
            
            a.getChildren().addAll(d1,d2,d3,d4,d5,d6,d7);
            
            scrollPane.setContent(a);
            Button okbutton = new Button("OK");okbutton.setDisable(true);
            Button cancelbutton = new Button("cancel");
            HBox hbox = new HBox(60);hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().addAll(okbutton,cancelbutton);
            
            vbox.getChildren().addAll(scrollPane,hbox);
            vbox.setMaxSize(430, 270);
            vbox.setId("creation");
            vbox.setOnMouseClicked((MouseEvent t) -> {
                t.consume();
            });
            
            showModalMessage(vbox);
            
            cancelbutton.setOnAction(v->{
                hideModalMessage();
            });
            
            okbutton.setOnAction((ActionEvent event)->{
                int c=0;
                Domaine[] d =new Domaine[7];
                TreeSet<Domaine> tr = new TreeSet<Domaine> ();
                for(int i = 0;i<7;i++){
                    String s = tab[i].getText();  
      
                    if (tab[i].isSelected()) 
                        {
                        d[c]= Domaine.valueOf(s);
                        tr.add(d[c]);
                        c++;
                        System.out.println(s);
                        }
                    
                }
                for (Joueur j: collectionJoueur){
                     if (j.getNom().compareTo((joueur1.getValue()).toString())==0) {
                         j.setDomaines(tr);
                         break;
                     }
                     
                    }
                hideModalMessage();
                ObservableList<Domaine> dO = FXCollections.observableArrayList();
        
                for (Domaine j : d) {
                 dO.add(j);
                }
     
                ListViewJ1.setItems(dO);
            });
            
                    ChangeListener<Boolean> textListener = new ChangeListener<Boolean>() {
                   
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    int cpt=0;
                    for(int i = 0;i<7;i++){
                        if (tab[i].isSelected()) cpt++;
                      }
                
                        okbutton.setDisable(cpt!=3);
                    }
            };
               
       
            d1.selectedProperty().addListener(textListener);
            d2.selectedProperty().addListener(textListener);
            d3.selectedProperty().addListener(textListener);
            d4.selectedProperty().addListener(textListener);
            d5.selectedProperty().addListener(textListener);
            d6.selectedProperty().addListener(textListener);
            d7.selectedProperty().addListener(textListener);
    
    } 
   
   public void modifier2() { /// Modifier les domaines du joueur 2 
        VBox vbox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        AnchorPane a = new AnchorPane();
            a.setMinSize(0.0, 0.0); a.setPrefSize(133.0, 175.0);
            
            CheckBox[] tab = new CheckBox[7];
            
            CheckBox d1 = new CheckBox("Science"); d1.setLayoutX(8.0); d1.setLayoutY(14.0);     tab[0]=d1;
            CheckBox d2 = new CheckBox("Politiques"); d2.setLayoutX(8.0); d2.setLayoutY(161.0);   tab[1]=d2;
            CheckBox d3 = new CheckBox("Géographie"); d3.setLayoutX(8.0); d3.setLayoutY(138.0); tab[2]=d3; 
            CheckBox d4= new CheckBox("Sport"); d4.setLayoutX(8.0); d4.setLayoutY(114.0);       tab[3]=d4;
            CheckBox d5 = new CheckBox("Musique"); d5.setLayoutX(8.0); d5.setLayoutY(88.0);     tab[4]=d5;
            CheckBox d6 = new CheckBox("Cinéma"); d6.setLayoutX(8.0); d6.setLayoutY(64.0);      tab[5]=d6;
            CheckBox d7 = new CheckBox("CultureGénerale"); d7.setLayoutX(8.0); d7.setLayoutY(41.0);     tab[6]=d7;
            
            a.getChildren().addAll(d1,d2,d3,d4,d5,d6,d7);
            
            scrollPane.setContent(a);
            Button okbutton = new Button("OK");
            Button cancelbutton = new Button("cancel");
            HBox hbox = new HBox(60);hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().addAll(okbutton,cancelbutton);
            
            vbox.getChildren().addAll(scrollPane,hbox);
            vbox.setMaxSize(430, 270);
            vbox.setId("creation");
            vbox.setOnMouseClicked((MouseEvent t) -> {
                t.consume();
            });
            showModalMessage(vbox);
            
            
            cancelbutton.setOnAction(v->{
                hideModalMessage();
            });
            
            okbutton.setOnAction((ActionEvent event)->{
                int c=0;
                Domaine[] d =new Domaine[7];
                TreeSet<Domaine> tr = new TreeSet<>();
                for(int i = 0;i<7;i++){
                    String s = tab[i].getText();  
      
                    if (tab[i].isSelected()) 
                        {
                        d[c]= Domaine.valueOf(s);
                        tr.add(d[c]);
                        c++;
                        System.out.println(s);
                        }
                    
                }
                for (Joueur j: collectionJoueur){
                     if (j.getNom().compareTo((joueur2.getValue()).toString())==0) {
                         j.setDomaines(tr);
                         break;
                     }
                     
                    }
                hideModalMessage();
                ObservableList<Domaine> dO = FXCollections.observableArrayList();
        
                for (Domaine j : d) {
                 dO.add(j);
                }
     
                ListViewJ2.setItems(dO);
            });
            
                    ChangeListener<Boolean> textListener = new ChangeListener<Boolean>() {
                   
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    int cpt=0;
                    for(int i = 0;i<7;i++){
                        if (tab[i].isSelected()) cpt++;
                      }
                
                        okbutton.setDisable(cpt!=3);
                    }
            };
               
       
       d1.selectedProperty().addListener(textListener);
       d2.selectedProperty().addListener(textListener);
       d3.selectedProperty().addListener(textListener);
       d4.selectedProperty().addListener(textListener);
       d5.selectedProperty().addListener(textListener);
       d6.selectedProperty().addListener(textListener);
       d7.selectedProperty().addListener(textListener);
       Operations_Fichiers f = new Operations_Fichiers();
       f.EnregistrerJ(collectionJoueur);
            
    } 
   
   class DelPlayer extends VBox { /// Supprimer un joueur

        public DelPlayer() {
                setMaxSize(200, 110);
                setId("Suppression de joueurs");
                
        // block mouse clicks
        setOnMouseClicked((MouseEvent t) -> {
            t.consume();
        });
         
            setSpacing(20);
            
            TextField nom = new TextField();
            nom.setPromptText("Donnez Le nom du joueur");
            nom.setLayoutX(16.0);
            nom.setLayoutY(10.0);

            
            Button okbutton = new Button("OK"); okbutton.setPrefWidth(55);
            Button cancelbutton = new Button("Cancel"); cancelbutton.setLayoutX(110.0);cancelbutton.setLayoutY(292.0);cancelbutton.setTranslateX(-30);cancelbutton.setPrefWidth(55);
            HBox hbox = new HBox(60);hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().addAll(okbutton,cancelbutton);
            
            cancelbutton.setOnMouseClicked(k->{hideModalMessage();});
           
            this.getChildren().addAll(nom,hbox);
            
            okbutton.setOnAction((ActionEvent e)->{
            int i=0;boolean trouve = false;
            for (Joueur j : collectionJoueur)
            {
                
                if (j.getNom().compareTo(nom.getText()) == 0)
                {
                trouve = true;
                break;
                }
                i++;
            }    
            if (trouve == true)
            {collectionJoueur.remove(i);
            setJoueurList();
             Operations_Fichiers f = new Operations_Fichiers();
             f.EnregistrerJ(collectionJoueur);
            }
            hideModalMessage();
       ;
 
        });
    }
   }
   public void Supprim() /// Affichage de la fenétre de suppression 
   {
       showModalMessage(new DelPlayer());
   }
          /// Animations pour les bouttons
   public void Agrandirplay()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), newGameBtn);
        f.setFromX(1);
        f.setFromY(1);
        f.setToX(1.1);
        f.setToY(1.1);
        f.setAutoReverse(true);
        f.play();
  
    }
    
    public void Reduireplay()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), newGameBtn);
        f.setFromX(1.1);
        f.setFromY(1.1);
        f.setToX(1);
        f.setToY(1);
        f.setAutoReverse(true);
        f.play();
       
    }
    public void Agrandircharger()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), chargerBtn);
        f.setFromX(1);
        f.setFromY(1);
        f.setToX(1.1);
        f.setToY(1.1);
        f.setAutoReverse(true);
        f.play();
  
    }
    
    public void Reduirecharger()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), chargerBtn);
        f.setFromX(1.1);
        f.setFromY(1.1);
        f.setToX(1);
        f.setToY(1);
        f.setAutoReverse(true);
        f.play();
       
    }
   
    public void Agrandirmscore()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), Mscore);
        f.setFromX(1);
        f.setFromY(1);
        f.setToX(1.1);
        f.setToY(1.1);
        f.setAutoReverse(true);
        f.play();
  
    }
    
    public void Reduiremscore()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), Mscore);
        f.setFromX(1.1);
        f.setFromY(1.1);
        f.setToX(1);
        f.setToY(1);
        f.setAutoReverse(true);
        f.play();
       
    }
   
    public void Agrandiraide()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), aideBtn);
        f.setFromX(1);
        f.setFromY(1);
        f.setToX(1.1);
        f.setToY(1.1);
        f.setAutoReverse(true);
        f.play();
  
    }
    
    public void Reduireaide()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), aideBtn);
        f.setFromX(1.1);
        f.setFromY(1.1);
        f.setToX(1);
        f.setToY(1);
        f.setAutoReverse(true);
        f.play();
       
    }
   
    public void Agrandirexit()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), exitBtn);
        f.setFromX(1);
        f.setFromY(1);
        f.setToX(1.1);
        f.setToY(1.1);
        f.setAutoReverse(true);
        f.play();
  
    }
    
    public void Reduireexit()
    {
        
        ScaleTransition f = new ScaleTransition(Duration.millis(10), exitBtn);
        f.setFromX(1.1);
        f.setFromY(1.1);
        f.setToX(1);
        f.setToY(1);
        f.setAutoReverse(true);
        f.play();
       
    }
   /// Afficher l'aide 
   public void AfficherAide() throws IOException
   {
       
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Aide.fxml"));
        Parent root =  myLoader.load(); 
        root.setId("NewAccount");
        showModalMessage(root);
      /*  Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();  */
       
   }
   ////Charger partie 
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
    /// Sortir du jeu 
    public void Sortir()
     {
        Operations_Fichiers f = new Operations_Fichiers();
        f.EnregistrerJ(collectionJoueur);
        exit(0);  
    }
     
    

}

