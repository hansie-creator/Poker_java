/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.model;

/**
 *
 * @author Hannes
 */
public class PokerBot {

    private final Pokerspel model;

    public PokerBot(Pokerspel model) {
        this.model = model;
    }

    public void doeActie() {
        if (model.isShowdown()) return;

        double kans = PokerSimulator.berekenWinkans(
                model.getBotKaarten(),
                model.getTafelKaarten(),
                10000
        );

        if (kans < 40) {
            model.botFold();
            System.out.println("Bot fold");
        }
        else if (kans > 75) {
            model.botRaise();
            System.out.println("Bot raise");
        }
        else {
            model.botCall();
            System.out.println("Bot call");
        }
    }
}