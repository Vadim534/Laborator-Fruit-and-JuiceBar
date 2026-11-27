package lab3;

public class SucFructe extends Produs {
    protected int volumMl;
    protected TipPreparare tipPreparare;

    public SucFructe(String denumire, double pretBaza, int stoc, int volumMl, TipPreparare tipPreparare) {
        super(denumire, pretBaza, stoc);
        if (volumMl <= 0) {
            throw new IllegalArgumentException("Volumul trebuie sa fie pozitiv.");
        }
        if (tipPreparare == null) {
            throw new IllegalArgumentException("Tipul de preparare nu poate fi null.");
        }
        this.volumMl = volumMl;
        this.tipPreparare = tipPreparare;
    }

    public int getVolumMl() {
        return volumMl;
    }

    public TipPreparare getTipPreparare() {
        return tipPreparare;
    }

    @Override
    public double pretFinal() {
        double multiplicatorVolum = (volumMl >= 400) ? 1.20 : 1.0;
        return super.pretFinal() * tipPreparare.getCoeficient() * multiplicatorVolum;
    }

    @Override
    public String toString() {
        return super.toString() + " [Suc: " + volumMl + "ml, " + tipPreparare + "]";
    }
}