package lab3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ZiLaStand {
    // Constantele personale pentru N = 1
    public static final int N = 1;
    public static final int MIN_PRODUSE = 6 + (N % 3);       // 7
    public static final int REDUCERE_COMBO = 10 + (N % 6);   // 11%
    public static final int K_BON_CADOU = 3 + (N % 4);       // 4

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        System.out.println("=== DATE PERSONALE STUDENT N = " + N + " ===");
        System.out.println("Minim produse: " + MIN_PRODUSE + " | Reducere combo: "
                + REDUCERE_COMBO + "% | Cadou la fiecare al " + K_BON_CADOU + "-lea bon\n");

        // --- VERIFICARE ETAPA 2: validari si contor static ---
        try {
            new Produs("", 10, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("[Test Etapa 2 OK] Exceptie denumire goala: " + e.getMessage());
        }
        try {
            new Produs("Test", -5, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("[Test Etapa 2 OK] Exceptie pret negativ: " + e.getMessage());
        }

        // --- ETAPA 3: Crearea celor 7 produse din stand ---
        FructIntreg mar = new FructIntreg("Mar Golden", 5.00, 15, 180);
        FructIntreg banana = new FructIntreg("Banana", 6.00, 12, 160);
        FructIntreg portocala = new FructIntreg("Portocala dulce", 7.00, 10, 230); // id = 3!

        SucFructe sucPortocale = new SucFructe("Suc natural portocale", 14.00, 10, 300, TipPreparare.STORS_LA_RECE);
        SucFructe sucAnanas = new SucFructe("Suc ananas & mango", 16.00, 8, 400, TipPreparare.PRESAT_LENT);
        SmoothieFructe smoothieCapsuni = new SmoothieFructe("Smoothie capsuni & banana", 18.00, 6, 350,
                TipPreparare.MIXAT_CU_GHEATA, "iaurt grecesc");

        SalataDulceFructe salataTropicala = new SalataDulceFructe("Salata dulce tropicala", 20.00, 5, 4, "miere si cocos");
        SalataDulceFructe salataSezon = new SalataDulceFructe("Salata dulce de sezon", 17.00, 2, 3, "scortisoara");

        System.out.println("\n[Test Etapa 2 OK] ID-ul celui de-al treilea produs (Portocala): " + portocala.getId());

        List<Produs> catalog = List.of(mar, banana, portocala, sucPortocale,
                sucAnanas, smoothieCapsuni, salataTropicala, salataSezon);

        System.out.println("\n=== ETAPA 3: CATALOGUL STANDULUI (" + catalog.size() + " produse) ===");
        for (Produs p : catalog) {
            System.out.println(p);
        }

        // --- ETAPA 4: Experiment BigDecimal ---
        System.out.println("\n=== ETAPA 4: EXPERIMENT BIGDECIMAL ===");
        double s = 0;
        for (int i = 0; i < 10; i++) s += 0.10;
        System.out.println("double s = " + s);

        BigDecimal b = BigDecimal.ZERO;
        for (int i = 0; i < 10; i++) b = b.add(new BigDecimal("0.10"));
        System.out.println("BigDecimal b = " + b);

        // --- ETAPA 6.1: Demonstratia agregarii ---
        System.out.println("\n=== ETAPA 6.1: DEMONSTRATIE AGREGARE MENIURI ===");
        Meniu meniuDimineata = new Meniu("Meniul Diminetii");
        meniuDimineata.adaugaProdus(sucPortocale);
        meniuDimineata.adaugaProdus(banana);

        Meniu meniuZilei = new Meniu("Meniul Zilei");
        meniuZilei.adaugaProdus(sucPortocale);
        meniuZilei.adaugaProdus(salataTropicala);

        System.out.println("1) Initial (sucPortocale in ambele meniuri):");
        meniuDimineata.afiseaza();
        meniuZilei.afiseaza();

        sucPortocale.setPretBaza(15.00);
        System.out.println("2) Dupa modificarea pretului la sucPortocale (15.00 lei):");
        meniuDimineata.afiseaza();
        meniuZilei.afiseaza();

        meniuDimineata = null;
        System.out.println("3) Dupa meniuDimineata = null, produsul exista in continuare: " + sucPortocale);

        // --- ETAPA 6.2: MeniuCombo ---
        MeniuCombo comboEnergiaZilei = new MeniuCombo("Combo Energia Zilei",
                List.of(sucPortocale, salataTropicala, banana));
        System.out.println("\n=== ETAPA 6.2: MENIU COMBO ===");
        System.out.println(comboEnergiaZilei);

        // --- ETAPA 7: Simularea unei zile la stand (5 comenzi) ---
        System.out.println("\n=== ETAPA 7: SIMULARE ZI LA STAND (5 COMENZI) ===\n");
        List<Bon> bonuriEmise = new ArrayList<>();

        // Comanda 1: acelasi produs adaugat de 2 ori -> o singura linie
        Comanda c1 = new Comanda();
        c1.adauga(mar, 2);
        c1.adauga(mar, 1);
        c1.adauga(banana, 2);
        Bon b1 = new Bon(c1);
        b1.tipareste();
        bonuriEmise.add(b1);

        // Comanda 2: regula proprie activa (3+ sucuri -> -15% la sucuri)
        Comanda c2 = new Comanda();
        c2.adauga(sucPortocale, 2);
        c2.adauga(smoothieCapsuni, 1);
        Bon b2 = new Bon(c2);
        b2.tipareste();
        bonuriEmise.add(b2);

        // Comanda 3: contine MeniuCombo + refuz partial de stoc
        Comanda c3 = new Comanda();
        c3.adauga(comboEnergiaZilei, 1);
        c3.adauga(salataSezon, 5); // stocul e doar 2 -> refuzat fara a modifica stocul!
        c3.adauga(salataSezon, 1);
        Bon b3 = new Bon(c3);
        b3.tipareste();
        bonuriEmise.add(b3);

        // Comanda 4: BONUL #4 (k = 4) -> PRIMESTE CADOU!
        Comanda c4 = new Comanda();
        c4.adauga(sucAnanas, 2);
        c4.adauga(portocala, 3);
        Bon b4 = new Bon(c4);
        b4.tipareste();
        bonuriEmise.add(b4);

        // Comanda 5: comanda standard + incercare combo cu componenta epuizata
        Comanda c5 = new Comanda();
        c5.adauga(salataSezon, 1); // ultimul din stoc -> stoc salataSezon devine 0
        MeniuCombo comboSezon = new MeniuCombo("Combo Sezon", List.of(salataSezon, mar));
        c5.adauga(comboSezon, 1);  // refuzat deoarece salataSezon are stoc 0
        c5.adauga(smoothieCapsuni, 1);
        Bon b5 = new Bon(c5);
        b5.tipareste();
        bonuriEmise.add(b5);

        // --- RAPORT DE INCHIDERE A ZILEI ---
        BigDecimal incasariTotale = BigDecimal.ZERO;
        Map<String, Integer> vanzariPeProdus = new LinkedHashMap<>();

        for (Bon bon : bonuriEmise) {
            incasariTotale = incasariTotale.add(bon.getComanda().calculeazaTotal());
            for (LinieComanda linie : bon.getComanda().getLinii()) {
                String nume = linie.getProdus().getDenumire();
                vanzariPeProdus.put(nume, vanzariPeProdus.getOrDefault(nume, 0) + linie.getCantitate());
            }
        }

        String celMaiVandut = "-";
        int maxVandute = 0;
        for (Map.Entry<String, Integer> entry : vanzariPeProdus.entrySet()) {
            if (entry.getValue() > maxVandute) {
                maxVandute = entry.getValue();
                celMaiVandut = entry.getKey() + " (" + maxVandute + " buc.)";
            }
        }

        System.out.println("==========================================");
        System.out.println("        RAPORT INCHIDERE ZI STAND         ");
        System.out.println("==========================================");
        System.out.println("Numar total bonuri emise: " + bonuriEmise.size());
        System.out.printf("Incasari totale:          %.2f lei%n",
                incasariTotale.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Produsul cel mai vandut:  " + celMaiVandut);
        System.out.println("\nStoc ramas pe fiecare produs:");
        for (Produs p : catalog) {
            System.out.printf("  - %-28s : %d buc.%n", p.getDenumire(), p.getStoc());
        }
        System.out.println("==========================================");
    }
}