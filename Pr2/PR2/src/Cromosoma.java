import java.util.ArrayList;

public class Cromosoma {

    private ArrayList<Double> cromosomas;
    private double coste;

    public Cromosoma(int tam) {
        cromosomas = new ArrayList<Double>(tam);
        coste = 0;
    }

    public ArrayList<Double> getCromosomas() {
        return cromosomas;
    }

    public void setCromosomas(ArrayList<Double> cromosomas) {
        this.cromosomas = cromosomas;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    @Override
    public String toString() {
        return "Cromosoma{" + "cromosomas=" + cromosomas + ", coste=" + coste + '}';
    }



}
