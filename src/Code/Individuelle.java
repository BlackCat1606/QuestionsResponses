/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.io.Serializable;

/**
 *
 * @author BLACKCAT
 */
public class Individuelle  extends Case implements Serializable{ /// Case sur le trançon individuel

    public Individuelle() {
    }

    public Individuelle(int numéro, Joueur occupant, Domaine domaine) {
        super(numéro, occupant, domaine);
    }

 
    public void afficherDomaine()
    {
        
    }
}
