
package be.inf1.pokerspel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HandEvaluator {

    public static int bepaalHandSterkte(ArrayList<Kaart> kaarten) {
        
        Map<Kaart.Rang, Integer> rangTelling = new HashMap<>();
        Map<Kaart.Figuur, Integer> suitTelling = new HashMap<>();
        ArrayList<Integer> rangen = new ArrayList<>();

        for (Kaart k : kaarten) {
            rangTelling.put(k.getRang(), rangTelling.getOrDefault(k.getRang(), 0) + 1);
            suitTelling.put(k.getFiguur(), suitTelling.getOrDefault(k.getFiguur(), 0) + 1);
            rangen.add(k.getRang().getWaarde());
        }

        Collections.sort(rangen);

        boolean flush = suitTelling.values().stream().anyMatch(v -> v >= 5);
        boolean straight = isStraight(rangen);
        int hoogste = rangen.get(rangen.size() - 1);

        // ROYAL FLUSH
        if (flush && straight && hoogste == 14) return 1000;

        // STRAIGHT FLUSH
        if (flush && straight) return 900 + hoogste;

        // FOUR OF A KIND
        if (rangTelling.containsValue(4)) return 800 + hoogste;

        // FULL HOUSE
        if (rangTelling.containsValue(3) && rangTelling.containsValue(2)) return 700 + hoogste;

        // FLUSH
        if (flush) return 600 + hoogste;

        // STRAIGHT
        if (straight) return 500 + hoogste;

        // THREE OF A KIND
        if (rangTelling.containsValue(3)) return 400 + hoogste;

        // TWO PAIR
        long pairs = rangTelling.values().stream().filter(v -> v == 2).count();
        if (pairs >= 2) return 300 + hoogste;

        // ONE PAIR
        if (pairs == 1) return 200 + hoogste;

        // HIGH CARD
        return 100 + hoogste;
    }

    private static boolean isStraight(ArrayList<Integer> rangen) {
        Set<Integer> uniek = new HashSet<>(rangen);
        ArrayList<Integer> lijst = new ArrayList<>(uniek);
        Collections.sort(lijst);

        for (int i = 0; i <= lijst.size() - 5; i++) {
            boolean opeenvolgend = true;
            for (int j = 0; j < 4; j++) {
                if (lijst.get(i + j) + 1 != lijst.get(i + j + 1)) {
                    opeenvolgend = false;
                    break;
                }
            }
            if (opeenvolgend) return true;
        }
        return false;
    }
}




