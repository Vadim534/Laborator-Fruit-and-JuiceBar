package lab3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comanda {
    private final List<LinieComanda> linii = new ArrayList<>();

    public void adauga(Produs p, int cantitate) {
        if (p == null || cantitate <= 0) {
            System.out.println("[Refuz] Produs invalid sau cantitate <= 0.");
            return;
        }

        // Verificare speciala pentru MeniuCombo (Etapa 6.2)
        if (p instanceof MeniuCombo combo) {
            if (!combo.areComponenteInStoc(cantitate)) {
                System.out.println("[Refuz stoc] Combo-ul '" + p.getDenumire()
                        + "' nu poate fi vandut deoarece o componenta nu are stoc suficient.");
                return;
            }
            combo.consumaStocComponente(cantitate);
        } else {
            if (p.getStoc() < cantitate) {
                System.out.println("[Refuz stoc] Stoc insuficient pentru '" + p.getDenumire()
                        + "' (cerut: " + cantitate + ", disponibil: " + p.getStoc() + ").");
                return;
            }
            p.setStoc(p.getStoc() - cantitate);
        }

        // Daca produsul exista deja in comanda (conform equals), marim cantitatea
        for (LinieComanda linie : linii) {
            if (linie.getProdus().equals(p)) {
                linie.adaugaCantitate(cantitate);
                return;
            }
        }

        linii.add(new LinieComanda(p, cantitate));
    }

    public List<LinieComanda> getLinii() {
        return Collections.unmodifiableList(linii);
    }

    public BigDecimal calculeazaSubtotalBrut() {
        BigDecimal suma = BigDecimal.ZERO;
        for (LinieComanda linie : linii) {
            suma = suma.add(linie.subtotal());
        }
        return suma.setScale(2, RoundingMode.HALF_UP);
    }

    // Regula proprie a standului (Etapa 5):
    // Daca clientul comanda >= 3 produse din categoria SucFructe (inclusiv SmoothieFructe),
    // primeste 15% reducere la totalul acestor bauturi.
    public BigDecimal aplicaRegulaStand() {
        int numarSucuri = 0;
        BigDecimal totalSucuri = BigDecimal.ZERO;

        for (LinieComanda linie : linii) {
            if (linie.getProdus() instanceof SucFructe) {
                numarSucuri += linie.getCantitate();
                totalSucuri = totalSucuri.add(linie.subtotal());
            }
        }

        if (numarSucuri >= 3) {
            return totalSucuri.multiply(new BigDecimal("0.15"))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculeazaTotal() {
        return calculeazaSubtotalBrut()
                .subtract(aplicaRegulaStand())
                .setScale(2, RoundingMode.HALF_UP);
    }
}