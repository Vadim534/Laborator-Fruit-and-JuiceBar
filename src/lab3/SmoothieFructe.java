package lab3;

public class SmoothieFructe extends SucFructe {
    private String baza;

    public SmoothieFructe(String denumire, double pretBaza, int stoc, int volumMl,
                          TipPreparare tipPreparare, String baza) {
        super(denumire, pretBaza, stoc, volumMl, tipPreparare);
        if (baza == null || baza.isBlank()) {
            throw new IllegalArgumentException("Baza smoothie-ului nu poate fi goala.");
        }
        this.baza = baza.trim();
    }

    public String getBaza() {
        return baza;
    }

    @Override
    public double pretFinal() {
        // Adauga 3.00 lei pentru baza cremoasa peste pretul sucului
        return super.pretFinal() + 3.00;
    }

    @Override
    public String toString() {
        return super.toString() + " -> Smoothie (baza: " + baza + ")";
    }
}