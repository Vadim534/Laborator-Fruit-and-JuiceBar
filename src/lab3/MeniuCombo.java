package lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeniuCombo extends Produs {
    public static final int PROCENT_REDUCERE = 10 + (1 % 6); // N = 1 -> 11%
    private final List<Produs> componente;

    public MeniuCombo(String denumire, List<Produs> componente) {
        super(denumire, calculeazaSumaBaza(componente), calculeazaStocMinim(componente));
        this.componente = new ArrayList<>(componente);
    }

    private static double calculeazaSumaBaza(List<Produs> lista) {
        if (lista == null || lista.size() < 2) {
            throw new IllegalArgumentException("Un combo trebuie sa contina cel putin 2 produse.");
        }
        double suma = 0;
        for (Produs p : lista) {
            suma += p.pretFinal();
        }
        return suma;
    }

    private static int calculeazaStocMinim(List<Produs> lista) {
        int min = Integer.MAX_VALUE;
        for (Produs p : lista) {
            if (p.getStoc() < min) {
                min = p.getStoc();
            }
        }
        return min;
    }

    public List<Produs> getComponente() {
        return Collections.unmodifiableList(componente);
    }

    public boolean areComponenteInStoc(int cantitateCombo) {
        for (Produs p : componente) {
            if (p.getStoc() < cantitateCombo) {
                return false;
            }
        }
        return true;
    }

    public void consumaStocComponente(int cantitateCombo) {
        for (Produs p : componente) {
            p.setStoc(p.getStoc() - cantitateCombo);
        }
        this.stoc = calculeazaStocMinim(componente);
    }

    @Override
    public double pretFinal() {
        double suma = 0;
        for (Produs p : componente) {
            suma += p.pretFinal();
        }
        return suma * (1.0 - PROCENT_REDUCERE / 100.0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("#%d [COMBO -%d%%] %s (pret final: %.2f lei, disponibil: %d) -> contine: ",
                getId(), PROCENT_REDUCERE, denumire, pretFinal(), calculeazaStocMinim(componente)));
        for (int i = 0; i < componente.size(); i++) {
            sb.append(componente.get(i).getDenumire());
            if (i < componente.size() - 1) sb.append(" + ");
        }
        return sb.toString();
    }
}