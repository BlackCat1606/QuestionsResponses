/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author saiyen
 */
public class menu extends AnchorPane{
    
    public menu()
    {
      Button b1 = new Button("Bouton 1");
      Button b2 = new Button("Bouton 2");
      b2.setTranslateX(40);
      
      this.getChildren().addAll(b1,b2);
      this.setPrefSize(500, 500);
    }
    
    
}
