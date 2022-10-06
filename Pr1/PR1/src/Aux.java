import java.util.Vector;

public class Aux {

    public static void mostrarVector(Vector<Double> v) {
        for (long k = 0; k < v.size(); k++) {
            System.out.print("," + v.get((int) k));
        }
        System.out.println();
    }


}
