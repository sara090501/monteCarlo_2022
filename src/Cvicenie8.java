package src;

import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;

public class Cvicenie8 {

    public void vypocitaj() {
        UniformContinuousRNG uniformContinuousRNG = new UniformContinuousRNG(0.4, 0.7);
        TriangularRNG triangularRNG = new TriangularRNG(70.0,90.0,110.0);

        int pocetOpakovani = 1000000;
        double vysledok = 0.0;

        double Cp = 0.89;
        int O = 100;

        for (int i = 0; i < pocetOpakovani; i++) {
            double Cn = uniformContinuousRNG.sample();
            int D = triangularRNG.sample().intValue();
            int Z = O - D;
            double Co = Cn/2;

            double Hv = (D * Cp) + (Z * Co) - (O * Cn);
            vysledok += Hv;
        }

        vysledok = vysledok/pocetOpakovani;

        System.out.println("Vysledok: " + vysledok);
    }
}
