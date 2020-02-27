/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Code.Joueur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafxapplication3.Partie.Jcourant;

/**
 * FXML Controller class
 *
 * @author BLACKCAT
 */
public class ChoiceController implements Initializable { // Controleur des choix aprés le duel 
@FXML
JFXRadioButton p1;
@FXML
JFXRadioButton p2;
@FXML
JFXRadioButton p3;
@FXML
JFXButton Confirmer;
public static Joueur winner;
public static boolean challengeur;
public static int lastpos;
public static int newpos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
   Confirmer.setDisable(true);
   ChangeListener<Boolean> ConfirmerListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                  Confirmer.setDisable(p1.isSelected() ==false && p2.isSelected()==false && p3.isSelected()==false );
            }
   };
   p1.selectedProperty().addListener(ConfirmerListener);
   p2.selectedProperty().addListener(ConfirmerListener);
   p3.selectedProperty().addListener(ConfirmerListener);
        p1.setOnMouseClicked(v->{
            p2.setSelected(false);
            p3.setSelected(false);
        });
         p2.setOnMouseClicked(v->{
            p1.setSelected(false);
            p3.setSelected(false);
        });
          p3.setOnMouseClicked(v->{
            p2.setSelected(false);
            p1.setSelected(false);
        });
        
    }    
    
    public void confirmer() /// Confirmer votre choix
    {
        
        if(p1.isSelected())
        {
         Jcourant.setAvancement(newpos);
        }
        if(p2.isSelected())
        {
            Jcourant = winner;
            Partie.Namej.setText("Le tour est à : "+Jcourant.getNom());
        }
        if(p3.isSelected())
        {
            // prendre la main
        }
    }
    
}
