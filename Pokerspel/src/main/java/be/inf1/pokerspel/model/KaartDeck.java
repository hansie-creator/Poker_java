/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.model;

import be.inf1.pokerspel.model.Kaart.Figuur;
import be.inf1.pokerspel.model.Kaart.Rang;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author hannes Depoorter, Jasper Gebruers
 */
public class KaartDeck {
    private ArrayList<Kaart> kaarten;
    private Kaart kaart;
    
    public KaartDeck(){
        kaarten = new ArrayList<>();
        
        for (Figuur f : Figuur.values()){
            for (Rang r : Rang.values()){
                kaarten.add(new Kaart(r,f));
            }
        }
        schudden();
    }
        
    public void schudden() {
        /*
          * Shuffle zet het kaartendeck in een random volgorde
          * https://www.geeksforgeeks.org/java/collections-shuffle-method-in-java-with-examples/ 
        */
        
        Collections.shuffle(kaarten);
    }
    
    
    public Kaart kaartTrekken(){
        kaart = kaarten.get(0);
        kaarten.remove(0);
        return kaart;
    }
    
    
    public void verwijderKaart(Kaart kaart) {
        /*
        * removeIf verwijdert een element uit een lijst als aan bepaalde voorwaarde wordt voldaan
        * https://www.geeksforgeeks.org/java/arraylist-removeif-method-in-java/
        */
        
        kaarten.removeIf(k ->
            k.getRang() == kaart.getRang()
            && k.getFiguur() == kaart.getFiguur()
        );
    }
}
