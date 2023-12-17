package src;

import OSPRNG.TriangularRNG;

import java.util.ArrayList;

class ZaznamTabulky {
    ZaznamTabulky(boolean isFast, double cas) {
        this.isFast = isFast;
        this.cas = cas;
    }

    private final boolean isFast;
    private final double cas;

    public boolean isFast() {
        return isFast;
    }

    public double getCas() {
        return cas;
    }
}

public class jeho {
    public static void main(String[] args) {
        final int pocetReplikacii = 1000000;

        TriangularRNG casFastGen = new TriangularRNG(40.0, 50.0, 75.0);
        TriangularRNG casFuriousGen = new TriangularRNG(35.0, 52.0, 80.0);

        int pocetPrveDveFast = 0;

        for (int i = 0; i < pocetReplikacii; i++) {
            ArrayList<ZaznamTabulky> vysledky = new ArrayList<>();

            for (int j = 0; j < 5; j++)
                pridajVysledok(vysledky, new ZaznamTabulky(true, casFastGen.sample()));

            for (int j = 0; j < 5; j++)
                pridajVysledok(vysledky, new ZaznamTabulky(false, casFuriousGen.sample()));

            if (vysledky.get(0).isFast() && vysledky.get(1).isFast())
                pocetPrveDveFast++;
        }

        System.out.println("Pravdepodobnosť, že na prvých 2 miestach bude fast: " + ((double) pocetPrveDveFast / pocetReplikacii) * 100.0);

        int viacBodovFast = 0;
        int viacBodovFurious = 0;
        for (int i = 0; i < pocetReplikacii; i++) {
            int bodyFast = 0;
            int bodyFurious = 0;

            for (int j = 0; j < 15; j++) {

                ArrayList<ZaznamTabulky> vysledky = new ArrayList<>();

                for (int k = 0; k < 5; k++)
                    pridajVysledok(vysledky, new ZaznamTabulky(true, casFastGen.sample()));

                for (int k = 0; k < 5; k++)
                    pridajVysledok(vysledky, new ZaznamTabulky(false, casFuriousGen.sample()));

                for (int k = 0; k < vysledky.size(); k++) {
                    if (vysledky.get(k).isFast())
                        bodyFast += 10 - k;
                    else
                        bodyFurious += 10 - k;
                }
            }

            if (bodyFast > bodyFurious)
                viacBodovFast++;
            else
                viacBodovFurious++;
        }

        System.out.println("Pravdepodobnosť, že v sezóne bude mať viac bodov Fast: " + ((double) viacBodovFast / pocetReplikacii) * 100.0);
        System.out.println("Pravdepodobnosť, že v sezóne bude mať viac bodov Furious: " + ((double) viacBodovFurious / pocetReplikacii) * 100.0);
    }

    //pridá záznam do výsledokv tak, aby výsledky boli zoradené podľa času
    private static void pridajVysledok(ArrayList<ZaznamTabulky> vysledky, ZaznamTabulky zaznam) {
        boolean pridany = false;

        for (int i = 0; i < vysledky.size(); i++) {
            if (vysledky.get(i).getCas() > zaznam.getCas()) {
                vysledky.add(i, zaznam);
                pridany = true;
                break;
            }
        }
        if (!pridany)
            vysledky.add(zaznam);
    }
}
