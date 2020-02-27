/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.io.Serializable;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author BLACKCAT
 */
public class Jeu implements Serializable{ /// La classe du jeu
    private TreeSet<Joueur> players;
    private ArrayList<Partie> parties;
    private String nom;

    public TreeSet<Joueur> getPlayers() {
        return players;
    }

    public void setPlayers(TreeSet<Joueur> players) {
        this.players = players;
    }

    public ArrayList<Partie> getParties() {
        return parties;
    }

    public void setParties(ArrayList<Partie> parties) {
        this.parties = parties;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void ouvrirJeu(){}
    public void fermerJeu(){
    exit(0);
    }
    public void sauvegarderJeu(){}
    public void reprendreJeu(){}
}
