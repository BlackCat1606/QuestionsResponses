/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author BLACKCAT
 */
public class Partie implements Serializable { //// Classe utiliser pour la sérialisation de la partie graphique
    private String Id;
    private int numéro;
    private Joueur A,B;
    private Joueur first;
    private ArrayList<CaseCh> parcours ;
    private TreeSet<Question> Questions;

    public TreeSet<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(TreeSet<Question> Questions) {
        this.Questions = Questions;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getNuméro() {
        return numéro;
    }

    public void setNuméro(int numéro) {
        this.numéro = numéro;
    }

    public Joueur getA() {
        return A;
    }

    public void setA(Joueur A) {
        this.A = A;
    }

    public Joueur getB() {
        return B;
    }

    public void setB(Joueur B) {
        this.B = B;
    }

   

    public Partie() {
    }

    public Partie(String Id, int numéro, Joueur A, Joueur B, ArrayList<CaseCh> parcours,TreeSet<Question> Questions) {
        this.Id = Id;
        this.numéro = numéro;
        this.A = A;
        this.B = B;
        this.parcours = parcours;
        this.Questions = Questions;
    }

    public Joueur getFirst() {
        return first;
    }

    public void setFirst(Joueur first) {
        this.first = first;
    }

    public ArrayList<CaseCh> getParcours() {
        return parcours;
    }

    public void setParcours(ArrayList<CaseCh> parcours) {
        this.parcours = parcours;
    }
    
 

  

   

    
}
