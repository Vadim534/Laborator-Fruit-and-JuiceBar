package lab3;

import java.util.ArrayList;
import java.util.List;

public class Meniu {
    private final String denumire;
    private final List<Produs> produse = new ArrayList<>();

    public Meniu(String denumire) {
        this.denumire = denumire;
    }

    public void adaugaProdus(Produs p) {
        if (p != null && !produse.contains(p)) {
            produse.add(p);
        }
    }

    public void afiseaza() {
        System.out.println("--- " + denumire + " ---");
        for (Produs p : produse) {
            System.out.println("  * " + p);
        }
    }
}