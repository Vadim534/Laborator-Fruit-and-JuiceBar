package lab3;

public enum TipPreparare {
    STORS_LA_RECE(1.0),
    MIXAT_CU_GHEATA(1.15),
    PRESAT_LENT(1.25);

    private final double coeficient;

    TipPreparare(double coeficient) {
        this.coeficient = coeficient;
    }

    public double getCoeficient() {
        return coeficient;
    }
}