package BatailleNavale;

/**
 * Created by fabie_000 on 01/05/2017.
 */
public class Coordonnee {
    private int ligne;
    private int colone;

    public Coordonnee(int ligne, int colone) { // OUAIISS !!!
        this.ligne = ligne;
        this.colone = colone;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColone() {
        return colone;
    }

    @Override
    public String toString() {
        return "Coordonnee{" +
                "ligne=" + ligne +
                ", colone=" + colone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordonnee that = (Coordonnee) o;

        if (ligne != that.ligne) return false;
        return colone == that.colone;
    }
}