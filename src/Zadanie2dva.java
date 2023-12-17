package src;

import OSPRNG.*;

public class Zadanie2dva {
    void vypocitaj() {
        TriangularRNG nakladyNaA = new TriangularRNG(1.0, 1.75, 2.5);
        TriangularRNG nakladyNaB = new TriangularRNG(0.7, 1.2, 1.7);
        UniformDiscreteRNG dopytPreA = new UniformDiscreteRNG(40,79);
        UniformDiscreteRNG dopytPreB = new UniformDiscreteRNG(66,154);
        int pocetOpakovani = 1000000;
        int predajA = 3;
        int predajB = 2;

        int pocetVyrobenychA = 70;
        int pocetVyrobenychB = 90;

        double priemernyZiskA = 0.0;
        double priemernyZiskB = 0.0;
        double celkoveNakladyNaA = 0.0;
        double celkoveNakladyNaB = 0.0;

        for (int i = 0; i < pocetOpakovani; i++) {

            int dopytA = dopytPreA.sample();
            int dopytB = dopytPreB.sample();

            int predajnaCenaA = Math.min(pocetVyrobenychA, dopytA);
            int predajnaCenaB = Math.min(pocetVyrobenychB, dopytB);

            celkoveNakladyNaA = 0;
            celkoveNakladyNaB = 0;

            for (int j = 0; j < pocetVyrobenychA; j++) {
                celkoveNakladyNaA += nakladyNaA.sample();
            }

            for (int j = 0; j < pocetVyrobenychB; j++) {
                celkoveNakladyNaB += nakladyNaB.sample();
            }

            priemernyZiskA += predajnaCenaA*predajA - celkoveNakladyNaA;
            priemernyZiskB += predajnaCenaB*predajB - celkoveNakladyNaB;
        }

        priemernyZiskA = priemernyZiskA/pocetOpakovani;
        priemernyZiskB = priemernyZiskB/pocetOpakovani;

        System.out.println("Priemerny zisk vyrobku A: " + priemernyZiskA);
        System.out.println("Priemerny zisk vyrobku B: " + priemernyZiskB);
    }
}
