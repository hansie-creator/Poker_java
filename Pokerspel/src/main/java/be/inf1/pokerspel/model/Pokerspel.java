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

    private boolean spelerHeeftGeacteerd = false;
    private boolean botHeeftGeacteerd = false;

    private PokerBot bot;

    public Pokerspel(int aantalDeelnemers) {
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
        controleerBeurt();
        int bedrag = huidigeInzet - spelerInzet;
        spelerChips -= bedrag;
        pot += bedrag;
        spelerInzet += bedrag;

        spelerHeeftGeacteerd = true;
        spelerAanZet = false;
        botSpeelt();
    }

    public void raise() {
        controleerBeurt();
        int bedrag = 20;
        spelerChips -= bedrag;
        pot += bedrag;
        spelerInzet += bedrag;
        huidigeInzet = spelerInzet;

        spelerHeeftGeacteerd = true;
        spelerAanZet = false;
        botSpeelt();
    }

    public void fold() {
        fase = Fase.SHOWDOWN;
    }


    public void botCall() {
        pot += huidigeInzet;
        botHeeftGeacteerd = true;
    }

    public void botRaise() {
        huidigeInzet += 20;
        pot += huidigeInzet;
        botHeeftGeacteerd = true;
    }

    public void botFold() {
        fase = Fase.SHOWDOWN;
        botHeeftGeacteerd = true;
    }


    private void botSpeelt() {
        if (fase == Fase.SHOWDOWN) return;

        bot.doeActie();
        spelerAanZet = true;

        if (spelerHeeftGeacteerd && botHeeftGeacteerd) {
            spelerHeeftGeacteerd = false;
            botHeeftGeacteerd = false;
            volgendeFase();
        }
    }


    private void volgendeFase() {
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
                break;
            }
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