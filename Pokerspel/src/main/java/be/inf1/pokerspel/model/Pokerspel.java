/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.model;

import static be.inf1.pokerspel.model.Fase.PRE_FLOP;
import java.util.ArrayList;

/**
 *
 * @author Jasper Gebruers, Hannes Depoorter
 */


public class Pokerspel {

    private int spelerChips = 500;
    private int pot = 0;
    private int huidigeInzet = 0;
    private int spelerInzet = 0;
    private int botInzet = 0;
    private String laatsteBotActie = "";
    private boolean call;
    private Status winnaar = Status.niet_bepaald;
    private boolean raiseOpen;


    private final KaartDeck deck = new KaartDeck();
    private final ArrayList<Kaart> spelerKaarten = new ArrayList<>();
    private final ArrayList<Kaart> botKaarten = new ArrayList<>();
    private final ArrayList<Kaart> tafelKaarten = new ArrayList<>();

    private Fase fase = Fase.PRE_FLOP;
    private boolean spelerAanZet = true;

    private boolean spelerHeeftGeacteerd = false;
    private boolean botHeeftGeacteerd = false;

    private PokerBot bot;
    private int botRaisesDezeRonde;

    public Pokerspel(int aantalDeelnemers) {
        call = false;
        raiseOpen = false;
        resetPot();
        spelerKaarten.add(deck.kaartTrekken());
        spelerKaarten.add(deck.kaartTrekken());

        botKaarten.add(deck.kaartTrekken());
        botKaarten.add(deck.kaartTrekken());

        // spelerkaarten zijn meteen zichtbaar
        for (Kaart k : spelerKaarten) {
            k.draaiOm();
        }

        bot = new PokerBot(this);
    }


    public void check() {
        controleerBeurt();
        spelerHeeftGeacteerd = true;
        spelerAanZet = false;
        botSpeelt();
    }
    
   

    public void call() {
        bot.endRaise();
        controleerBeurt();
        int bedrag = huidigeInzet - spelerInzet;
        spelerChips -= bedrag;
        pot += bedrag;
        spelerInzet += bedrag;

        spelerHeeftGeacteerd = true;
        spelerAanZet = false;
        raiseOpen = false;
        botSpeelt();
    }

    public void raise(int bedrag) {
        controleerBeurt();
        int totaal = (huidigeInzet - spelerInzet)+ bedrag;
        if (totaal > spelerChips){
            totaal = spelerChips;
        }
        
        spelerChips -= totaal;
        pot += totaal;
        spelerInzet += totaal;
        huidigeInzet = spelerInzet;
        raiseOpen = true;

        spelerHeeftGeacteerd = true;
        spelerAanZet = false;
        botSpeelt();
    }

    public void fold() {
        
        fase = Fase.SHOWDOWN;
    }
    
    public String getLaatsteBotActie(){
        return laatsteBotActie;
    }


    public void botCall() {
        
        int botCallBedrag = huidigeInzet - botInzet;
        pot += botCallBedrag;
        botInzet += botCallBedrag;
        botHeeftGeacteerd = true;
        raiseOpen = false;
        laatsteBotActie = "bot callt";
        
    }

    public void botRaise() {
        if (botRaisesDezeRonde >= 1) {
            botCall();
            return;
        }
        int botRaiseBedrag = 20;
        huidigeInzet += botRaiseBedrag;
        int teBetalen = huidigeInzet - botInzet;
        pot += teBetalen;
        botInzet += teBetalen;
        raiseOpen = true;
        botHeeftGeacteerd = true;
        botRaisesDezeRonde++;
        laatsteBotActie = "bot raised met 20 chips";
    }

    public void botFold() {
        fase = Fase.SHOWDOWN;
        botHeeftGeacteerd = true;
        laatsteBotActie = "bot foldt";
        bepaalWinnaar();
        
    }


    private void botSpeelt() {
        if (fase == Fase.SHOWDOWN) return;

        bot.doeActie();
        spelerAanZet = true;
        
        if(raiseOpen)return;

        if (spelerHeeftGeacteerd && botHeeftGeacteerd) {
            spelerHeeftGeacteerd = false;
            botHeeftGeacteerd = false;
            volgendeFase();
        }
    }
    
    public void resetInzetten(){
        spelerInzet = 0;
        huidigeInzet = 0;
    }
    
    public void resetPot(){
        pot = 0;
    }
    
    
    
    public void bepaalWinnaar(){
        ArrayList<Kaart> spelerHand = new ArrayList<>();
        spelerHand.addAll(spelerKaarten);
        spelerHand.addAll(tafelKaarten);
        
        ArrayList<Kaart> botHand = new ArrayList<>();
        botHand.addAll(botKaarten);
        botHand.addAll(tafelKaarten);
        
        int spelerScore = HandEvaluator.bepaalHandSterkte(spelerHand);
        int botScore = HandEvaluator.bepaalHandSterkte(botHand);
        
        if (spelerScore > botScore){
            winnaar = Status.speler_wint;
            System.out.println("Je hebt gewonnen!!!");
        }
        else{
            winnaar = Status.bot_wint;
            System.out.println("Je hebt verloren");
        }
        
        
        
    }
 


    private void volgendeFase() {
        resetInzetten();
        botInzet =0;
        botRaisesDezeRonde = 0;
        // methode switch en case via: https://www.geeksforgeeks.org/java/switch-statement-in-java/
        switch (fase) {
            case PRE_FLOP : {
                for (int i = 0; i < 3; i++) {
                Kaart k = deck.kaartTrekken();
                k.draaiOm();
                tafelKaarten.add(k);
                }
                fase = Fase.FLOP;
                break;
            }
            case FLOP : {
                Kaart k = deck.kaartTrekken();
                k.draaiOm();
                tafelKaarten.add(k);
                fase = Fase.TURN;
                break;
            }
            case TURN : {
                Kaart k = deck.kaartTrekken();
                k.draaiOm();
                tafelKaarten.add(k);
                fase = Fase.RIVER;
                break;
            }
            case RIVER : {
                for (Kaart k : botKaarten) {
                    k.draaiOm();
                }
                fase = Fase.SHOWDOWN;
                bepaalWinnaar();
                break;          
            }

           
            
        }
    }
    
    

    public void controleerBeurt() {
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