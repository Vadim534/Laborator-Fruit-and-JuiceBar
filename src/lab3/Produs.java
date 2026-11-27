package lab3;

import java.util.Objects;

public class Produs {
    private static int contorId = 0;

    private final int id;
    protected String denumire;
    protected double pretBaza;
    protected int stoc;

    public Produs(String denumire, double pretBaza, int stoc) {
        if (denumire == null || denumire.isBlank()) {
            throw new IllegalArgumentException("Denumirea produsului nu poate fi goala.");
        }
        if (pretBaza < 0) {
            throw new IllegalArgumentException("Pretul de baza nu poate fi negativ.");
        }
        if (stoc < 0) {
            throw new IllegalArgumentException("Stocul nu poate fi negativ.");
        }

        this.id = ++contorId;
        this.denumire = denumire.trim();
        this.pretBaza = pretBaza;
        this.stoc = stoc;
    }

    public Produs(String denumire, double pretBaza) {
        this(denumire, pretBaza, 10);
    }

    public int getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public double getPretBaza() {
        return pretBaza;
    }

    public void setPretBaza(double pretBaza) {
        if (pretBaza < 0) {
            throw new IllegalArgumentException("Pretul de baza nu poate fi negativ.");
        }
        this.pretBaza = pretBaza;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        if (stoc < 0) {
            throw new IllegalArgumentException("Stocul nu poate fi negativ.");
        }
        this.stoc = stoc;
    }

    public double pretFinal() {
        return pretBaza;
    }

    @Override
    public String toString() {
        return String.format("#%d %s (baza: %.2f lei, final: %.2f lei, stoc: %d)",
                id, denumire, pretBaza, pretFinal(), stoc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produs produs)) return false;
        return Objects.equals(denumire, produs.denumire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denumire);
    }
}