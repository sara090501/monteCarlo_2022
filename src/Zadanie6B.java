package src;

import OSPRNG.*;

import java.util.ArrayList;

class AutoB {
    private String typ;
    private Double rychlost;
    private Integer body;

    public AutoB(String parTyp, Double parRychlost, Integer parBody) {
        this.typ = parTyp;
        this.rychlost = parRychlost;
        this.body = parBody;
    }

    String getTyp() {
        return this.typ;
    }

    Double getRychlost() {
        return this.rychlost;
    }

    Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }
}

public class Zadanie6B {
    public void vypocitaj() {
        final int pocetOpakovani = 100000;
        TriangularRNG fastTime = new TriangularRNG(40.0, 50.0, 75.0);
        TriangularRNG furiousTime = new TriangularRNG(35.0, 52.0, 80.0);

        int pocetFast = 0;
        int pocetFurious = 0;
        double pravdepodobnostFast;
        int pravdepodobnostVyssiehoZiskuBodovFast = 0;
        int pravdepodobnostVyssiehoZiskuBodovFurious = 0;


        for (int j = 0; j < pocetOpakovani; j++) {
            ArrayList<AutoB> listAut = new ArrayList<>();

            for (int l = 0; l < 15; l++) {
                for (int i = 0; i < 5; i++) {
                    listAut.add(new AutoB("fast", fastTime.sample(), 0));
                    listAut.add(new AutoB("furious", furiousTime.sample(), 0));
                }


                AutoB temp;
                for (int i = 0; i < listAut.size(); i++) {
                    for (int k = 0; k < listAut.size() - 1; k++) {
                        if (listAut.get(k).getRychlost() > listAut.get(k + 1).getRychlost()) {
                            temp = listAut.get(k + 1);
                            listAut.set(k + 1, listAut.get(k));
                            listAut.set(k, temp);
                        }
                    }
                }

                int body = 10;
                for (int i = 0; i < listAut.size(); i++) {
                    listAut.get(i).setBody(body);
                    if (listAut.get(i).getTyp().equals("fast")) {
                        pocetFast += listAut.get(i).getBody();
                    } else {
                        pocetFurious += listAut.get(i).getBody();
                    }
                    body--;
                }
            }

            if (pocetFurious > pocetFast) {
                pravdepodobnostVyssiehoZiskuBodovFurious++;
            } else if (pocetFast > pocetFurious) {
                pravdepodobnostVyssiehoZiskuBodovFast++;
            }
        }

        double avgFast = (double) pocetFast/pocetOpakovani;
        double avgFurious = (double) pocetFurious/pocetOpakovani;
        System.out.println("Furious body z 15 pretekov: " + pocetFurious);
        System.out.println("Fast body z 15 pretekov: " + pocetFast);
        System.out.println();
        System.out.println("Fast body z 15 pretekov, priemer: " + avgFast);
        System.out.println("Furious body z 15 pretekov, priemer: " + avgFurious);


        System.out.println("Furious ziska viac bodov s pravdepodobnostou " + ((double)pravdepodobnostVyssiehoZiskuBodovFurious/pocetOpakovani)*100);
        System.out.println("Fast ziska viac bodov s pravdepodobnostou " + ((double)pravdepodobnostVyssiehoZiskuBodovFast/pocetOpakovani)*100);

    }
}
