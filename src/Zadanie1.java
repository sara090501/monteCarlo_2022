package src;

import OSPRNG.*;

public class Zadanie1 {

    public void vypocitaj() {
        int pocetPokusov = 1000000;
        double cenaNakup = 0.15;

        TriangularRNG cenaPredajGen = new TriangularRNG(0.25, 0.6, 0.95);
        UniformContinuousRNG dobaPredajaGen = new UniformContinuousRNG(250.0, 420.0);

        double maxZisk = 0.0;
        int maxBaliky = 0;

        for (int i = 10; i <= 20; i++) {
            double celkovyZisk = 0.0;

            for (int j = 0; j < pocetPokusov; j++) {
                double cenaPredaj = cenaPredajGen.sample();
                double dobaPredaja = dobaPredajaGen.sample();
                int nakupene = i * 10;

                int dopyt = (int) (dobaPredaja / 2.7) + 1;
                int predane = Math.min(dopyt, nakupene);

                double ziskDna = predane * cenaPredaj + (nakupene - predane) * cenaNakup * 0.65 - nakupene * cenaNakup;
                celkovyZisk += ziskDna;
            }

            double priemernyZisk = celkovyZisk / pocetPokusov;
            if (priemernyZisk > maxZisk) {
                maxZisk = priemernyZisk;
                maxBaliky = i;
            }
        }

        System.out.println("Maximalny zisk bol pri " + maxBaliky + " balikoch: " + maxZisk);
    }
}
