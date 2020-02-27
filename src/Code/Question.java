/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import Code.Domaine;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author BLACKCAT
 */
public class Question implements Serializable ,Comparator,Comparable { //// Question posée 
    private String question;
    private String reponse;
    private String propo1,propo2,propo3;
    private Domaine domaine;
    private boolean used;

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getPropo1() {
        return propo1;
    }

    public void setPropo1(String propo1) {
        this.propo1 = propo1;
    }

    public String getPropo2() {
        return propo2;
    }

    public void setPropo2(String propo2) {
        this.propo2 = propo2;
    }

    public String getPropo3() {
        return propo3;
    }

    public void setPropo3(String propo3) {
        this.propo3 = propo3;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public Question() {
    }

    public Question(String question, String reponse, String propo1, String propo2, String propo3, Domaine domaine) {
        this.question = question;
        this.reponse = reponse;
        this.propo1 = propo1;
        this.propo2 = propo2;
        this.propo3 = propo3;
        this.domaine = domaine;
    }
    @Override
    public int compare(Object o1, Object o2) {
       Question q1 = (Question)o1;
       Question q2 = (Question)o2;
       return(q1.getQuestion().compareTo(q2.getQuestion()));
    }
    @Override
    public int compareTo(Object o) {
        Question q = (Question)o;
       return(this.getQuestion().compareTo(q.getQuestion()));
    }
    public boolean equals (Object o1,Object o2)
    {
        Question q1 = (Question)o1;
       Question q2 = (Question)o2;
         return(q1.getQuestion().equalsIgnoreCase(q2.getQuestion()));
    }
}
