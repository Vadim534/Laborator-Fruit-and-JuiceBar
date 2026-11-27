package lab3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LinieComanda {
    private final Produs produs;
    private int cantitate;

    // Constructor nepublic (package-private): se apeleaza exclusiv din Comanda
    LinieComanda(Produs produs, int cantitate) {
        this.produs = produs;
        this.cantitate = cantitate;
    }

    public Produs getProdus() {
        return produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    void adaugaCantitate(int supliment) {
        this.cantitate += supliment;
    }

    public BigDecimal subtotal() {
        BigDecimal pret = BigDecimal.valueOf(produs.pretFinal());
        return pret.multiply(BigDecimal.valueOf(cantitate))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("%-28s x%d  =  %.2f lei",
                produs.getDenumire(), cantitate, subtotal());
    }
}