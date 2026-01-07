/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.model;

import static be.inf1.pokerspel.model.Fase.PRE_FLOP;
import java.util.ArrayList;

/**
 *
 * @author Jasper
 */
/*public class Pokerspel {
    private int spelerChips;
    private int pot;
    private int huidigeInzet;
    private int spelerInzet;
    private int aantalDeelnemers;
    private final ArrayList<Kaart> tafelKaarten = new ArrayList<>();
    public Fase fase = Fase.PRE_FLOP;
    private ArrayList<Kaart> kaarten;
    private Kaart eerste, tweede; // kaarten die je krijgt
    private Kaart river;//5de kaart op tafel
    private Kaart turn; // eertse 3 die komen
    private Kaart flop;// 4de kaart die komt
    private KaartDeck deck;
*/

public class Pokerspel {

    private int spelerChips = 500;
    private int pot = 0;
    private int huidigeInzet = 0;
    private int spelerInzet = 0;

    private final KaartDeck deck = new KaartDeck();

    private final ArrayList<Kaart> spelerKaarten = new ArrayList<>();
    private final ArrayList<Kaart> botKaarten = new ArrayList<>();
    private final ArrayList<Kaart> tafelKaarten = new ArrayList<>();

    private Fase fase = Fase.PRE_FLOP;
    private boolean spelerAanZet = true;

    public Pokerspel(int aantalDeelnemers) {
        spelerKaarten.add(deck.kaartTrekken());
        spelerKaarten.add(deck.kaartTrekken());

        botKaarten.add(deck.kaartTrekken());
        botKaarten.add(deck.kaartTrekken());
    }

    public void check() {
        controleerBeurt();
        spelerAanZet = false;
        botActieKlaar();
    }

    public void call() {
        controleerBeurt();
        int bedrag = huidigeInzet - spelerInzet;
        spelerChips -= bedrag;
        pot += bedrag;
        spelerInzet += bedrag;
        spelerAanZet = false;
        botActieKlaar();
    }

    public void raise() {
        controleerBeurt();
        int bedrag = 20;
        spelerChips -= bedrag;
        pot += bedrag;
        spelerInzet += bedrag;
        huidigeInzet = spelerInzet;
        spelerAanZet = false;
        botActieKlaar();
    }

    public void fold() {
        fase = Fase.SHOWDOWN;
    }

    private void botActieKlaar() {
        spelerAanZet = true;
        volgendeFase();
    }
//fases
    private void volgendeFase() {
        switch (fase) {
            case PRE_FLOP: {
                tafelKaarten.add(deck.kaartTrekken());
                tafelKaarten.add(deck.kaartTrekken());
                tafelKaarten.add(deck.kaartTrekken());
                fase = Fase.FLOP;
            }
            case FLOP: {
                tafelKaarten.add(deck.kaartTrekken());
                fase = Fase.TURN;
            }
            case TURN : {
                tafelKaarten.add(deck.kaartTrekken());
                fase = Fase.RIVER;
            }
            case RIVER : fase = Fase.SHOWDOWN;
        }
    }

    private void controleerBeurt() {
        if (!spelerAanZet) {
            throw new IllegalStateException("Niet jouw beurt");
        }
    }


    public ArrayList<Kaart> getSpelerKaarten() {
        return spelerKaarten; 
    }
    
    public ArrayList<Kaart> getBotKaarten() { 
        return botKaarten; 
    }
    
    public ArrayList<Kaart> getTafelKaarten() { 
        return tafelKaarten; 
    }

    public boolean isShowdown() { 
        return fase == Fase.SHOWDOWN; 
    }

    public int getSpelerChips() { 
        return spelerChips; 
    }
    
    public int getPot() { 
        return pot; 
    }
}