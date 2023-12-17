package src;

import OSPRNG.*;

public class Zadanie2 {

    public void vypocitaj() {
        TriangularRNG triangularRNG_A = new TriangularRNG(1.0,1.75,2.5);
        TriangularRNG triangularRNG_B = new TriangularRNG(0.7,1.2,1.7);

        //pri diskretnom treba davat pozor na zatvorky
        UniformDiscreteRNG uniformDiscreteRNG_A = new
                UniformDiscreteRNG(40,79);
        UniformDiscreteRNG uniformDiscreteRNG_B = new
                UniformDiscreteRNG(66,154);

        int pocetOpakovani = 1000000;
        int O_A = 70;
        int O_B = 90;
        int Cp_A = 3;
        int Cp_B = 2;
        double Hv_A = 0;
        double Hv_B = 0;

        //v tomto zadani nemame Co ani Z
        for (int i = 0; i < 1000000; i++) {
            int D_A = uniformDiscreteRNG_A.sample();
            int D_B = uniformDiscreteRNG_B.sample();

            int P_A = Math.min(D_A, O_A);
            int P_B = Math.min(D_B, O_B);

            double Cv_A = 0;
            double Cv_B = 0;

            for (int j = 0; j < O_A; j++) {
                Cv_A += triangularRNG_A.sample();
            }

            for (int j = 0; j < O_B; j++) {
                Cv_B += triangularRNG_B.sample();
            }

            Hv_A += (P_A*Cp_A) - (Cv_A);
            Hv_B += (P_B*Cp_B) - (Cv_B);
        }

        Hv_A = Hv_A/pocetOpakovani;
        Hv_B = Hv_B/pocetOpakovani;

        System.out.printf("A:" + Hv_A + "\n");
        System.out.printf("B:" + Hv_B);
    }
}
