/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.model;

/**
 *
 * @author Hannes Depoorter, Jasper Gebruers
 */
public class PokerBot {

    private final Pokerspel model;
    private boolean raised;
    public PokerBot(Pokerspel model) {
        this.model = model;
    }

    public void doeActie() {
        if (model.isShowdown()) return;
        raised = false;

        double kans = PokerSimulator.berekenWinkans(
                model.getBotKaarten(),
                model.getTafelKaarten(),
                10000
        );
        
        

        if (kans < 30) {
            raised = false;
            model.botFold();
            System.out.println("Bot fold");
        }
        else if (kans > 60) {
            raised = true;
            model.botRaise();
            System.out.println("Bot raise");
        }
        else {
            raised = false;
            model.botCall();
            System.out.println("Bot call");
        }
    }

   public void endRaise(){
       raised = false;
   }

    public boolean raised() {
        return raised;
    }
    
    
}