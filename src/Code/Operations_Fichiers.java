/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static javafxapplication3.JavaFXApplication3.chemin;

/**
 *
 * @author BLACKCAT
 */
public class Operations_Fichiers { /// Fonctions pour enregistrer et charger les données du jeu
    
   
    
    public void Sauvegarder(Code.Partie partie) //// Sauvegarder une partie
    {
        FileChooser fileChooser = new FileChooser();
        Stage root = new Stage();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Game files (*.GAME), (*.game)", "*.game");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(chemin.toString()));

        File file = fileChooser.showSaveDialog(root);
        // Read file name
        if (file != null) 
        {
            try {
                try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                    oos.writeObject(partie);
                    oos.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
               

            } catch (IOException e) {
                e.printStackTrace();
               
            } finally {
                System.out.println("opération réussie");
            }
        }
    }

    public Code.Partie Ouvrir(Code.Partie partie) { //// Ouvrir une partie enregistrée
        Code.Partie ps = new Code.Partie();
        
        FileChooser fileChooser = new FileChooser();
        Stage root = new Stage();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Game files (*.GAME), (*.game)", "*.game");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(chemin.toString()));

        //Show save file dialog
        File file = fileChooser.showOpenDialog(root);
        if (file != null) {
            try {
                try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
             
                  ps = (Partie) ois.readObject();
                    ois.close();
               
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
              
            } catch (IOException e) {
                e.printStackTrace();
               
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
               
            }
        }
        return ps;
       
    }  
    public String nomQuest= "Questions.txt";
    
    
    public TreeSet<Question> Ouvrir_Questions( TreeSet<Question>  questions) throws IOException { /// Charger les questions du fichier texte
      
      TreeSet<Question> quest = new TreeSet<Question>();
       
         BufferedReader in = null;
        String ligne;
        
        try {
            in = new BufferedReader(new FileReader(nomQuest));
            
            while ((ligne = in.readLine())!= null)
                        
            {
                int i=0;
                String domaine ="",question="",Reponse="",p1="",p2="",p3="";
                i++;
                
                 
                  while(ligne.charAt(i)!='>')
                  {
                      domaine = domaine+ligne.charAt(i);
                      i++;
                  }
                  System.out.println(domaine);
                  i++;
                  i++;
                   while(ligne.charAt(i)!=';')
                  {
                      question = question+ligne.charAt(i);
                      i++;
                  }
                   System.out.println(question);
                   i++;
                    while(ligne.charAt(i)!=';')
                  {
                      Reponse = Reponse+ligne.charAt(i);
                      i++;
                  }
                   System.out.println(Reponse);
                   i++;
                    while(ligne.charAt(i)!=';')
                  {
                      p1 = p1+ligne.charAt(i);
                      i++;
                  }
                   System.out.println(p1);
                   i++;
                   while(ligne.charAt(i)!=';')
                  {
                      p2 = p2+ligne.charAt(i);
                      i++;
                  }
                   System.out.println(p2);
                   i++;
                   while(i<ligne.length())
                  {
                      p3 = p3+ligne.charAt(i);
                      i++;
                  }
                   System.out.println(p3);
                   i=0;
                   Question q = new Question();
                   q.setQuestion(question);
                   q.setReponse(Reponse);
                   q.setPropo1(p1);
                   q.setPropo2(p2);
                   q.setPropo3(p3);
                   if(domaine.equalsIgnoreCase("musique"))
                   {
                       q.setDomaine(Domaine.Musique);
                   }
                   if(domaine.equalsIgnoreCase("culturegenerale"))
                   {
                       q.setDomaine(Domaine.CultureGénerale);
                   }
                   if(domaine.equalsIgnoreCase("sport"))
                   {
                       q.setDomaine(Domaine.Sport);
                   }
                   if(domaine.equalsIgnoreCase("cinema"))
                   {
                       q.setDomaine(Domaine.Cinéma);
                   }
                   if(domaine.equalsIgnoreCase("geographie"))
                   {
                       q.setDomaine(Domaine.Géographie);
                   }
                   if(domaine.equalsIgnoreCase("science"))
                   {
                       q.setDomaine(Domaine.Science);
                   }
                   if(domaine.equalsIgnoreCase("histoire"))
                   {
                       q.setDomaine(Domaine.Politiques);
                   }
                 quest.add(q);
                  //} 
              
                 
         
               
            //System.out.println(ligne);
           // StringTokenizer tok = new StringTokenizer(ligne," ");
         /*   int nb = tok.countTokens();
            for( i=0; i<nb; i++)
            {
             System.out.println(tok.nextToken());            
            }   */
            }                      
        } finally {
            if (in != null) 
                in.close();                  
        }

       return quest;
    }
     String nomFichier = "Joueur.data";
    public void EnregistrerJ(ArrayList<Joueur> j) /// Enregistrer la liste des joueurs sur un TP
    {
        boolean success = false;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        // Read file name
       
        /// ****** Function of Saving File ****** ///
        try {

            fos = new FileOutputStream(nomFichier);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(j);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            success = false;

        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        } finally {
            System.out.println("opération réussie");
            success = true;
        }
        
    }
    public ArrayList<Joueur> ChargerJ()/// Charger la liste des joueurs du fichier
    {
        ArrayList<Joueur> j= new ArrayList<>();
        try {

            FileInputStream fis = new FileInputStream(nomFichier);
            ObjectInputStream ois = new ObjectInputStream(fis);
             j = (ArrayList<Joueur>)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       
        return j;
    }
}
