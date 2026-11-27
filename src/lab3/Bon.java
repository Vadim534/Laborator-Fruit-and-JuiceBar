package lab3;

import java.math.BigDecimal;

public class Bon {
    private static int contorBon = 0;
    public static final int K_CADOU = 3 + (1 % 4); // N = 1 -> k = 4

    private final int numarBon;
    private final Comanda comanda;

    public Bon(Comanda comanda) {
        if (comanda == null) {
            throw new IllegalArgumentException("Bonul trebuie sa contina o comanda valida.");
        }
        this.numarBon = ++contorBon;
        this.comanda = comanda;
    }

    public int getNumarBon() {
        return numarBon;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public boolean areCadou() {
        return numarBon % K_CADOU == 0;
    }

    public void tipareste() {
        System.out.println("==========================================");
        System.out.println("       FRUIT & JUICE BAR - BON #" + numarBon);
        System.out.println("==========================================");
        for (LinieComanda linie : comanda.getLinii()) {
            System.out.println(linie);
        }
        System.out.println("------------------------------------------");
        BigDecimal brut = comanda.calculeazaSubtotalBrut();
        BigDecimal reducere = comanda.aplicaRegulaStand();
        BigDecimal total = comanda.calculeazaTotal();

        System.out.printf("Subtotal:                  %.2f lei%n", brut);
        if (reducere.compareTo(BigDecimal.ZERO) > 0) {
            System.out.printf("Reducere 3+ sucuri (-15%%): -%.2f lei%n", reducere);
        }
        System.out.printf("TOTAL DE PLATA:            %.2f lei%n", total);

        if (areCadou()) {
            System.out.println("*** CADOU BON #" + numarBon + ": Shot energizant de ghimbir & portocala (GRATIS) ***");
        }
        System.out.println("==========================================\n");
    }
}