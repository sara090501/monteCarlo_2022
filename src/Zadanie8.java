package src;

import OSPRNG.*;

public class Zadanie8 {

    public void vypocitaj() {
        TriangularRNG zozate = new TriangularRNG(1.0,3.0,3.5);

        final int pocetOpakovani = 100000;
        final double rozloha = 300.0;

        int pocetKombainov = 0;
        double pravdepodobnostPrekrocenia = 0.0;

        do {
            pocetKombainov++;
            pravdepodobnostPrekrocenia = 0.0;

            for (int i = 0; i < pocetOpakovani; i++) {
                double zozataRozloha = 0.0;

                for (int j = 0; j < 20; j++) {
                    for (int k = 0; k < pocetKombainov; k++) {
                        zozataRozloha += zozate.sample();
                    }
                }

                if (rozloha > zozataRozloha) {
                    pravdepodobnostPrekrocenia++;
                }
            }

            pravdepodobnostPrekrocenia = (double) pravdepodobnostPrekrocenia/pocetOpakovani;
        } while (pravdepodobnostPrekrocenia >= 0.1);

        System.out.println("Pocet kombajnov: " + pocetKombainov + ", chyba:" + pravdepodobnostPrekrocenia);
    }

}
