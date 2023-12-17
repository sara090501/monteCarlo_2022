package src;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformDiscreteRNG;

public class Zadanie2tri {
    void vypocitaj() {
        TriangularRNG nakladyAGen = new TriangularRNG(1.0,1.75, 2.5);
        TriangularRNG nakladyBGen = new TriangularRNG(0.7,1.2, 1.7);
        UniformDiscreteRNG dopytAGen = new UniformDiscreteRNG(40,79);
        UniformDiscreteRNG dopytBGen = new UniformDiscreteRNG(66,154);
        int vyrobenieA = 70;
        int vyrobenieB = 90;
        int predajnaCenaA = 3;
        int predajnaCenaB = 2;
        int pocetOpakovani = 1000000;
        double celkovyZiskA = 0.0;
        double celkovyZiskB = 0.0;

        for (int i = 0; i < pocetOpakovani; i++) {
            int dopytA = dopytAGen.sample();
            int dopytB = dopytBGen.sample();
            double nakladyA = 0.0;
            double nakladyB = 0.0;
            dopytA = Math.min(dopytA, vyrobenieA);
            dopytB = Math.min(dopytB, vyrobenieB);

            for (int j = 0; j < vyrobenieA; j++) {
                nakladyA += nakladyAGen.sample();
            }

            for (int j = 0; j < vyrobenieB; j++) {
                nakladyB += nakladyBGen.sample();
            }

            celkovyZiskA += dopytA*predajnaCenaA - nakladyA;
            celkovyZiskB += dopytB*predajnaCenaB - nakladyB;
        }

        celkovyZiskA = celkovyZiskA/pocetOpakovani;
        celkovyZiskB = celkovyZiskB/pocetOpakovani;
        System.out.println("Zisk A: " + celkovyZiskA);
        System.out.println("Zisk B: " + celkovyZiskB);
    }
}
