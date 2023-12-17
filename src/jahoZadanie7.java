package src;

import OSPRNG.*;

public class jahoZadanie7 {

    void vypocitaj() {
        TriangularRNG dopytGen = new TriangularRNG(1000.0, 4000.0, 8500.0);

        double minNaklady = Double.MAX_VALUE;
        int minNaSklade = 0;

        for (int naSklade = 1000; naSklade < 10000; naSklade++) {
            int dodatocneNaklady = 0;

            int dopyt = (int) (double) dopytGen.sample();
            if (dopyt > naSklade) {
                dodatocneNaklady += (dopyt - naSklade) * 150;
            } else if (naSklade > dopyt) {
                dodatocneNaklady += (naSklade - dopyt) * 50;
            }

            if (dodatocneNaklady < minNaklady) {
                minNaklady = dodatocneNaklady;
                minNaSklade = naSklade;
            }
        }

        System.out.println("Množstvo skladovanej vakcíny: " + minNaSklade + ", dodatočné náklady: " + minNaklady);
    }
}
