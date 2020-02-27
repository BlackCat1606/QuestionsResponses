/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author BLACKCAT
 */
public class Joueur implements Serializable { // Les données du joueur
    private String nom;
    private TreeSet<Domaine> Domaines;
    private int score;
    private int avancement;
    private boolean role;

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TreeSet<Domaine> getDomaines() {
        return Domaines;
    }

    public void setDomaines(TreeSet<Domaine> Domaines) {
        this.Domaines = Domaines;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAvancement() {
        return avancement;
    }

    public void setAvancement(int avancement) {
        this.avancement = avancement;
    }

    public Joueur() {
    }

    public Joueur(String nom, TreeSet<Domaine> Domaines, int score, int avancement) {
        this.nom = nom;
        this.Domaines = Domaines;
        this.score = score;
        this.avancement = avancement;
    }
    
    @Override
    public boolean equals (Object o)
    { Joueur m = (Joueur )o;
     return(/*m.getAvancement() == this.getAvancement() && m.getScore() == this.getScore()&&*/ m.getNom().equals(this.getNom()));  
    }
}
