package src;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Medovniky {
    void vypocitaj() {
        int dobaPredaja = 7*60;
        int pocetOpakovani = 100000;
        int vyrobky = 100;
        TriangularRNG predajnaCenaGen = new TriangularRNG(0.5, 0.75, 1.0);
        TriangularRNG kusGen = new TriangularRNG(2.0, 6.0, 10.0);
        UniformContinuousRNG rychleGen = new UniformContinuousRNG(1.0,3.0);
        double zisk = 0.0;
        int priemernyZostatok = 0;

        for (int i = 0; i < pocetOpakovani; i++) {
            double predajnaCena = predajnaCenaGen.sample();
            double rychlostPredaja = kusGen.sample();

            int dopyt = (int)Math.ceil((dobaPredaja/rychlostPredaja));
            int ponuka = Math.min(vyrobky, dopyt);
            int zostatok = vyrobky - ponuka;

            zisk += predajnaCena*ponuka;

            if (zostatok > 10) {
                rychlostPredaja = rychleGen.sample();
                predajnaCena = predajnaCena*0.25;
            }

            dopyt = (int)Math.ceil((60/rychlostPredaja));
            ponuka = Math.min(zostatok, dopyt);
            zostatok -= ponuka;
            zisk += predajnaCena*ponuka;
            priemernyZostatok += zostatok;
        }

        zisk = zisk/pocetOpakovani;
        priemernyZostatok = priemernyZostatok/pocetOpakovani;
        System.out.println("Priemerny zisk je: " + zisk + ", priemervy zostatok je: " + priemernyZostatok);
    }
}
