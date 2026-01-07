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
        int x = 0;

        for (Kaart k : model.getTafelKaarten()) {
            k.draaiOm();
            KaartView kv = new KaartView(k);
            kv.setLayoutX(x * 70);
            kv.setLayoutY(20);
            getChildren().add(kv);
            x++;
        }

        x = 0;
        for (Kaart k : model.getSpelerKaarten()) {
            k.draaiOm();
            KaartView kv = new KaartView(k);
            kv.setLayoutX(x * 70);
            kv.setLayoutY(120);
            getChildren().add(kv);
            x++;
        }

        if (model.isShowdown()) {
            x = 0;
            for (Kaart k : model.getBotKaarten()) {
                k.draaiOm();
                KaartView kv = new KaartView(k);
                kv.setLayoutX(x * 70);
                kv.setLayoutY(220);
                getChildren().add(kv);
                x++;
            }
        }
    }
}
