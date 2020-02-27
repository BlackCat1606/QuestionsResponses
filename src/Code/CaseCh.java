/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;



import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Circle;

/**
 *
 * @author BLACKCAT
 */
public  class CaseCh  implements Serializable,Comparator,Comparable { /// Case pour la sérialisation et le chargement de la partie
    private int numéro;
    private Joueur occupant = null;
    private Domaine domaine;
    private Question question;
    private boolean played = false;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }




    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getNuméro() {
        return numéro;
    }

    public void setNuméro(int numéro) {
        this.numéro = numéro;
    }

    public Joueur getOccupant() {
        return occupant;
    }

    public void setOccupant(Joueur occupant) {
        this.occupant = occupant;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public CaseCh() {
    }

    public CaseCh(int numéro, Domaine domaine, Question question, String color) {
        this.numéro = numéro;
        this.domaine = domaine;
        this.question = question;
        this.color = color;
    }

    



    @Override
    public boolean equals(Object o)
    { 
       return(this.getNuméro()== ((Case)o).getNuméro()); 
    
    }

    @Override
    public int compare(Object o1, Object o2) {
        int m = 0;
        Case e = (Case) o1;
        Case s = (Case) o2;
        if(e.getNuméro()== s.getNuméro()) m=0;
        if(e.getNuméro()> s.getNuméro()) m =1;
        if(e.getNuméro()< s.getNuméro()) m =-1;
        return m;
    }

    @Override
    public int compareTo(Object o) 
    {
        int m=0;
        Case s = (Case) o;
        if(this.getNuméro()== s.getNuméro()) m=0;
        if(this.getNuméro()> s.getNuméro()) m =1;
        if(this.getNuméro()< s.getNuméro()) m =-1;
        return m;
    }
    
    
}

