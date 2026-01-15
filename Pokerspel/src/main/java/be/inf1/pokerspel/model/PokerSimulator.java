/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.model;

import java.util.ArrayList;

/**
 *
 * @author hannes Depoorter, Jasper Gebruers
 */
public class PokerSimulator {

    public static double berekenWinkans(
            ArrayList<Kaart> spelerKaarten,
            ArrayList<Kaart> bekendeTafelKaarten,
            int simulaties
    ) {

        double score = 0.0;
        

        for (int i = 0; i < simulaties; i++) {

            KaartDeck deck = new KaartDeck();

            verwijderBekendeKaarten(deck, spelerKaarten);
            verwijderBekendeKaarten(deck, bekendeTafelKaarten);

            // Tafel kaarten toekennen
            ArrayList<Kaart> tafel = new ArrayList<>(bekendeTafelKaarten);
            while (tafel.size() < 5) {
                tafel.add(deck.kaartTrekken());
            }

            // Tegenstander kaarten toekennen
            ArrayList<Kaart> tegenstander = new ArrayList<>();
            tegenstander.add(deck.kaartTrekken());
            tegenstander.add(deck.kaartTrekken());

            
            ArrayList<Kaart> spelerHand = new ArrayList<>(spelerKaarten);
            spelerHand.addAll(tafel);

            ArrayList<Kaart> tegenstanderHand = new ArrayList<>(tegenstander);
            tegenstanderHand.addAll(tafel);

            int spelerScore = HandEvaluator.bepaalHandSterkte(spelerHand);
            int tegenScore = HandEvaluator.bepaalHandSterkte(tegenstanderHand);

            if (spelerScore > tegenScore) {
                score += 1.0;
            } else if (spelerScore == tegenScore) {
                score += 0.5;
            }
        }

        return (score / simulaties) * 100.0;
    }

    private static void verwijderBekendeKaarten(
            KaartDeck deck,
            ArrayList<Kaart> bekendeKaarten
    ) 
    {
        for (Kaart kaart : bekendeKaarten) {
            deck.verwijderKaart(kaart);
        }
    }
}
