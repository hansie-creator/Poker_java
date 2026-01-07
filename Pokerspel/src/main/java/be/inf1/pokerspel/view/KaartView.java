/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.view;

import be.inf1.pokerspel.model.Kaart;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Jasper
 */
public class KaartView extends Region{
    private Kaart model;
    private boolean forceShow;
    public static final int BREEDTE = 50;
    public static final int HOOGTE = 70;
    
    
    public KaartView(Kaart model){
        this.model = model;
        Rectangle kaart = new Rectangle(0,0,BREEDTE,HOOGTE);
        if (model.isOmgedraaid() || forceShow){
            kaart.setFill(Color.WHITE);
            Text t = new Text(model.getRang()+"" + model.getFiguur());
            t.setLayoutX(BREEDTE/2 - 3);
            t.setLayoutY(HOOGTE/2-5);
            getChildren().addAll(kaart, t );
        }
        else{
            kaart.setFill(Color.GOLD);
            getChildren().addAll(kaart);
        }
        
       
           
    }
}
