/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.view;

import be.inf1.pokerspel.model.Kaart;
import be.inf1.pokerspel.model.Pokerspel;
import javafx.scene.layout.Region;

/**
 *
 * @author Jasper
 */
public class PokerspelView extends Region {

    private final Pokerspel model;

    public PokerspelView(Pokerspel model) {
        this.model = model;
        update();
    }

    public void update() {
        getChildren().clear();

        
        int tafelStartX = -20;   // begin links in de ellips
        int tafelY = 70;        // hoogte binnen de ellips
        int spacing = 70;

        for (int i = 0; i < model.getTafelKaarten().size(); i++) {
            Kaart k = model.getTafelKaarten().get(i);
            KaartView kv = new KaartView(k);
            kv.setLayoutX(tafelStartX + i * spacing);
            kv.setLayoutY(tafelY);
            getChildren().add(kv);
        }

       
        int spelerStartX = 200;
        int spelerY = 320;

        for (int i = 0; i < model.getSpelerKaarten().size(); i++) {
            Kaart k = model.getSpelerKaarten().get(i);
            KaartView kv = new KaartView(k);
            kv.setLayoutX(spelerStartX + i * spacing);
            kv.setLayoutY(spelerY);
            getChildren().add(kv);
        }

        
        if (model.isShowdown()) {
            int botStartX = -50;
            int botY = 320;

            for (int i = 0; i < model.getBotKaarten().size(); i++) {
                Kaart k = model.getBotKaarten().get(i);
                KaartView kv = new KaartView(k);
                kv.setLayoutX(botStartX + i * 70);
                kv.setLayoutY(botY);
                getChildren().add(kv);
            }
        }
    }
}
