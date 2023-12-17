package src;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Triatlon {
    void vypocitaj() {
        TriangularRNG casTrvaniaPlavanie = new TriangularRNG(20.0, 32.0, 40.0);
        TriangularRNG casTrvaniaCyklistika = new TriangularRNG(60.0, 70.0, 86.0);
        TriangularRNG casTrvaniaBeh = new TriangularRNG(36.0, 46.0, 63.0);

        UniformContinuousRNG pravdepodobnost = new UniformContinuousRNG(0.0, 1.0);
        //jedno: 7%, dve: 4%, tri: 1,5%
        UniformContinuousRNG priDefekte = new UniformContinuousRNG(0.5,5.0);
        //jedno: 10%, dve: 4.5%
        UniformContinuousRNG zaviazanie = new UniformContinuousRNG(1.0, 3.0);

        int pocetOpakovani = 100000;
        int pocetPretekarov = 66;
        int kvalifikovani = 0;
        double pravdep = 0;

        for (int i = 0; i < pocetOpakovani; i++) {
            kvalifikovani = 0;
            for (int j = 0; j < pocetPretekarov; j++) {
                double plavanie = casTrvaniaPlavanie.sample();
                double cyklistika = casTrvaniaCyklistika.sample();
                double beh = casTrvaniaBeh.sample();

                double aktualnyCas = plavanie + cyklistika + beh;

                double prDefektu = pravdepodobnost.sample();
                double prTenisky = pravdepodobnost.sample();

                if (prDefektu < 0.07) {
                    aktualnyCas += priDefekte.sample();
                }
                if (prDefektu < 0.04) {
                    aktualnyCas += priDefekte.sample();
                }
                if (prDefektu < 0.015) {
                    aktualnyCas += priDefekte.sample();
                }

                if (prTenisky < 0.1) {
                    aktualnyCas += zaviazanie.sample();
                }
                if (prTenisky < 0.045) {
                    aktualnyCas += zaviazanie.sample();
                }

                if (aktualnyCas < 140) {
                    kvalifikovani++;
                }
            }
            pravdep += (double)kvalifikovani/pocetPretekarov;
        }
        pravdep = pravdep/pocetOpakovani;
        System.out.println("Kvalifikovani: " + pravdep*100 + "%.");
    }
}
