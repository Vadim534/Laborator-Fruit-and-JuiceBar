package lab3;

public class SalataDulceFructe extends Produs {
    private int numarFructe;
    private String topping;

    public SalataDulceFructe(String denumire, double pretBaza, int stoc, int numarFructe, String topping) {
        super(denumire, pretBaza, stoc);
        if (numarFructe < 2) {
            throw new IllegalArgumentException("O salata dulce contine cel putin 2 fructe.");
        }
        this.numarFructe = numarFructe;
        this.topping = (topping == null || topping.isBlank()) ? "fara topping" : topping.trim();
    }

    public int getNumarFructe() {
        return numarFructe;
    }

    public String getTopping() {
        return topping;
    }

    @Override
    public double pretFinal() {
        // 1.00 leu suplimentar pentru fiecare fruct peste 3 fructe incluse
        double extraFructe = Math.max(0, numarFructe - 3) * 1.00;
        return super.pretFinal() + extraFructe;
    }

    @Override
    public String toString() {
        return super.toString() + " [Salata dulce: " + numarFructe + " fructe, topping: " + topping + "]";
    }
}