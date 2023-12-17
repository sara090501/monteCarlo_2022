package src;

import OSPRNG.*;

public class Zadanie8dva {
    void vypocitaj() {
        int pocetOpakovani = 100000;
        double rozloha = 300.0;
        //za dva dni
        double pravdepodobnostCelkovo = 0.1;
        TriangularRNG triangularRNG = new TriangularRNG(1.0,3.0,3.5);
        int dobaZatia = 10*2;
        int pocetKombainov = 0;
        double pravdepodobnostPresiahnutia = 0.0;

        do {
            pocetKombainov++;
            pravdepodobnostPresiahnutia = 0.0;

            for (int i = 0; i < pocetOpakovani; i++) {
                double zozateAktualne = 0.0;


                for (int k = 0; k < pocetKombainov; k++) {
                    zozateAktualne += triangularRNG.sample()*dobaZatia;
                }

                if (rozloha > zozateAktualne) {
                    pravdepodobnostPresiahnutia++;
                }
            }
            pravdepodobnostPresiahnutia = pravdepodobnostPresiahnutia/pocetOpakovani;
        } while (pravdepodobnostPresiahnutia >= pravdepodobnostCelkovo);

        System.out.println("Pocet kombainov je: " + pocetKombainov + ", presiahnutie je : " + pravdepodobnostPresiahnutia*100);
    }
}
