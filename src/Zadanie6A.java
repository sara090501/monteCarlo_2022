package src;

import OSPRNG.*;

import java.util.ArrayList;

class AutoA {
    private String typ;
    private Double rychlost;
    private Integer body;

    public AutoA(String parTyp, Double parRychlost, Integer parBody) {
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

public class Zadanie6A {
    public void vypocitaj() {
        final int pocetOpakovani = 1000000;
        TriangularRNG fastTime = new TriangularRNG(40.0, 50.0, 75.0);
        TriangularRNG furiousTime = new TriangularRNG(35.0, 52.0, 80.0);

        int pocetFast = 0;
        double pravdepodobnostFast;

        for (int j = 0; j < pocetOpakovani; j++) {
            ArrayList<AutoA> listAut = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                listAut.add(new AutoA("fast", fastTime.sample(), 0));
                listAut.add(new AutoA("furious", furiousTime.sample(), 0));
            }


            AutoA temp;
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
                body--;
            }

            if (listAut.get(0).getTyp().equals("fast") && listAut.get(1).getTyp().equals("fast")) {
                pocetFast++;
            }
        }

        pravdepodobnostFast = ((double) pocetFast/pocetOpakovani)*100;
        System.out.println("Pravdopodobnost, ze sa na prvych dvoch miestach umiestnia vozidla typu fast je " + pravdepodobnostFast + ".");
    }
}
