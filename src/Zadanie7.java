package src;

import OSPRNG.*;

public class Zadanie7 {

    public void vypocitaj() {
        TriangularRNG triangularRNG = new TriangularRNG(1000.0,4000.0,8500.0);

        int pocetOpakovani = 10000;
        int dopyt = 0;
        double minimalneNaklady = Double.MAX_VALUE;
        double celkoveMinimalneNaklady = 0.0;
        int minimalneNaSklade = 0;
        int celkoveMinimalneNaSklade = 0;

        //if (zasoby = dopyt) su dodatocne naklady spolocnosti nulove
        //if (dopyt > zasoby) potom plus strata spolocnosti 150e na kus
        //if (zasoby > dopyt) potom strata spolosnoti je 50e na kus
        //minimalizacia nakladov

        for (int i = 0; i < pocetOpakovani; i++) {
            for (int zasoby = 5000; zasoby < 6000; zasoby++) {
                int strataSpolocnosti = 0;

                dopyt = triangularRNG.sample().intValue();

                if (zasoby > dopyt) {
                    strataSpolocnosti += (zasoby - dopyt)*50;
                } else if (dopyt > zasoby) {
                    strataSpolocnosti += (dopyt - zasoby)*150;
                }

                if (strataSpolocnosti < minimalneNaklady) {
                    minimalneNaklady = strataSpolocnosti;
                    minimalneNaSklade = zasoby;
                    celkoveMinimalneNaklady += minimalneNaklady;
                    celkoveMinimalneNaSklade += minimalneNaSklade;
                }
            }
        }

        celkoveMinimalneNaklady = celkoveMinimalneNaklady/pocetOpakovani;
        celkoveMinimalneNaSklade = celkoveMinimalneNaSklade/pocetOpakovani;

        System.out.println("Mnozstvo skladovanej vakciny: " + minimalneNaSklade);
        System.out.println("Dodatocne naklady: " + minimalneNaklady);
    }

}
