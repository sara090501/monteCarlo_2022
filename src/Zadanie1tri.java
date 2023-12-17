package src;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Zadanie1tri {
    void vypocitaj() {
        TriangularRNG predajnaCenaGen = new TriangularRNG(0.25,0.6, 0.95);
        UniformContinuousRNG dobaPredajaGen = new UniformContinuousRNG(250.0,420.0);
        double nakupnaCena = 0.15;
        double vykupnaCena = nakupnaCena*0.65;
        int pocetOpakovani = 1000000;
        double najlepsiZisk = 0.0;
        int pocetBalikov = 0;

        for (int i = 10; i < 20; i++) {
            double zisk = 0.0;
            for (int j = 0; j < pocetOpakovani; j++) {
                double predajnaCena = predajnaCenaGen.sample();
                double dobaPredaja = dobaPredajaGen.sample();
                int dopyt = (int)(dobaPredaja/2.7)+1;
                int objednaneMnozstvo = i*10;
                dopyt = Math.min(objednaneMnozstvo, dopyt);
                int zostatok = objednaneMnozstvo - dopyt;

                zisk += predajnaCena*dopyt + zostatok*vykupnaCena - nakupnaCena*objednaneMnozstvo;
            }
            zisk = zisk/pocetOpakovani;
            if (zisk > najlepsiZisk) {
                najlepsiZisk = zisk;
                pocetBalikov = i;
            }
        }

        System.out.println("Zisk: " + najlepsiZisk + ", pocek balikov: " + pocetBalikov);
    }
}
