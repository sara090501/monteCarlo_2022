package src;

import OSPRNG.*;

public class Zadanie1dva {
    void vypocitaj() {
        // hospodarskyVysledok = predajneMnozstvo * predajnaCena
        //                      + zostatokNaKonciDna * vykupnaCena
        //                      + objednaneMnozstvo * nakupnaCena
        TriangularRNG triangularRNG = new TriangularRNG(0.25, 0.6,0.95);
        UniformContinuousRNG uniformContinuousRNG = new UniformContinuousRNG(250.0, 420.0);

        int pocetOpakovani = 1000000;
        double nakupnaCena = 0.15;
        double vykupnaCena = nakupnaCena*0.65;

        double maxZisk = 0.0;
        int najlepsiPocetBalikov = 0;

        for (int aktualnyPocetBalikov = 10; aktualnyPocetBalikov < 20; aktualnyPocetBalikov++) {
            double celkovyZisk = 0.0;
            for (int i = 0; i < pocetOpakovani; i++) {
                double predajnaCena = triangularRNG.sample();
                int dopyt = (int) (uniformContinuousRNG.sample()/2.7) + 1;

                int objednaneMnozstvo = aktualnyPocetBalikov*10;

                int predane = Math.min(dopyt, objednaneMnozstvo);
                double zostatokNaKonciDna = objednaneMnozstvo - predane;

                double ziskDna = predane*predajnaCena + zostatokNaKonciDna*vykupnaCena - objednaneMnozstvo*nakupnaCena;
                celkovyZisk += ziskDna;
            }

            double priemernyZisk = celkovyZisk/pocetOpakovani;
            if (maxZisk < priemernyZisk) {
                maxZisk = priemernyZisk;
                najlepsiPocetBalikov = aktualnyPocetBalikov;
            }
        }

        System.out.println("Maximalny zisk bol pri " + najlepsiPocetBalikov + " balikoch: " + maxZisk);
    }
}
