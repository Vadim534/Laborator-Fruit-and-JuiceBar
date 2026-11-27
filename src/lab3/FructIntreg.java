package lab3;

public class FructIntreg extends Produs {
    private int gramajGrame;

    public FructIntreg(String denumire, double pretBaza, int stoc, int gramajGrame) {
        super(denumire, pretBaza, stoc);
        if (gramajGrame <= 0) {
            throw new IllegalArgumentException("Gramajul trebuie sa fie pozitiv.");
        }
        this.gramajGrame = gramajGrame;
    }

    public int getGramajGrame() {
        return gramajGrame;
    }

    @Override
    public double pretFinal() {
        // Fructele peste 200g au un adaos de 1.50 lei fata de pretul de baza
        double adaos = (gramajGrame > 200) ? 1.50 : 0.0;
        return super.pretFinal() + adaos;
    }

    @Override
    public String toString() {
        return super.toString() + " [Fruct intreg: " + gramajGrame + "g]";
    }
}