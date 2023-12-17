package src;

import OSPRNG.*;

import java.util.ArrayList;

public class Zadanie5 {

    void vypocitaj() {
        int pocetOpakovani = 10000000;
        double pravdepodobnostDve = 0.0;
        double pravdepodobnostTri = 0.0;
        int celkovyPocetDve = 0;
        int celkovyPocetTri = 0;

        UniformContinuousRNG continuousRNG = new UniformContinuousRNG(0.0,100.0);
        TriangularRNG triangularRNG = new TriangularRNG(30.0, 80.0, 100.0);

        for (int i = 0; i < pocetOpakovani; i++) {
            ArrayList<Double> carodejnice = new ArrayList<>();

            double uspesnost0 = triangularRNG.sample();
            double uspesnost1 = triangularRNG.sample();
            double uspesnost2 = triangularRNG.sample();

            carodejnice.add(continuousRNG.sample());
            carodejnice.add(continuousRNG.sample());
            carodejnice.add(continuousRNG.sample());

            if (carodejnice.get(0) < uspesnost0 == carodejnice.get(1) < uspesnost1) {
                celkovyPocetDve++;
                if (carodejnice.get(0) < uspesnost0) {
                    pravdepodobnostDve++;
                }
            }

            if ((carodejnice.get(0) < uspesnost0 == carodejnice.get(1) < uspesnost1)
                   && (carodejnice.get(1) < uspesnost1 == carodejnice.get(2) < uspesnost2)) {
                celkovyPocetTri++;
                if (carodejnice.get(0) < uspesnost0) {
                    pravdepodobnostTri++;
                }
            }
        }

        pravdepodobnostDve = pravdepodobnostDve/celkovyPocetDve*100;
        pravdepodobnostTri = pravdepodobnostTri/celkovyPocetTri*100;
        System.out.println("Pravdepodobnost, ze maju dve pravdu je: " + pravdepodobnostDve);
        System.out.println("Pravdepodobnost, ze maju tri pravdu je: " + pravdepodobnostTri);
    }
}
