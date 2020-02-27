/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Code.Domaine;
import Code.Joueur;
import Code.Operations_Fichiers;
import Code.Question;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author saiyen
 */
public class JavaFXApplication3 extends Application {
    ArrayList<Partie> collectionPartie;/// Liste des Parties 
    static ArrayList<Joueur> collectionJoueur; /// Liste des joueurs 
     public static java.nio.file.Path chemin;/// Chemin pour enregistrer les parties du jeu
    @Override
    public void start(Stage  primaryStage) throws Exception {
        chemin = Paths.get(System.getProperty("user.home") + "\\Documents\\Jeu");
        try /// Verifier si le dossier  du Jeu existe déja ou pas et le créer dans ce cas
        {
        File f = new File(chemin.toString());
        if (!(f.exists() && f.isDirectory())) 
        {
             
        boolean    success = ( new File(chemin.toString())).mkdir();
        }
        }
       catch (Exception e)
        {//Catch exception if any
       System.err.println("Error: " + e.getMessage());
        }
       Operations_Fichiers f = new Operations_Fichiers();
    
        collectionJoueur = new ArrayList<Joueur>();
        collectionJoueur = f.ChargerJ();/// Charger la liste des joueurs
       
/// Charger l'interface du jeu 
        
        AnchorPane root = new AnchorPane();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        root = (AnchorPane) myLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("TP 2");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
       
       

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
