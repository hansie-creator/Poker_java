/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.model;

import java.util.ArrayList;


/**
 *
 * @author Hannes
 */
public class PokerHand {

    private final ArrayList<Kaart> kaarten = new ArrayList<>();

    public PokerHand(KaartDeck deck) {
        kaarten.add(deck.kaartTrekken());
        kaarten.add(deck.kaartTrekken());
    }

    public ArrayList<Kaart> getKaarten() {
        return kaarten;
    }
}
