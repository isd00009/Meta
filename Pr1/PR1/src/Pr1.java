import java.util.Vector;

public class Pr1 {
    public static void main(String[] args) throws Exception {
        
        Vector<Double> solActual;
        int tam;
        String nombreAlg;
        int nAlg;
        double rmin, rmax;
        long semilla, evaluaciones;

        LeerArchivo leer = new LeerArchivo("Trid.txt");
        tam = leer.getTam();
        nombreAlg = leer.getNombreAlg();
        rmin = leer.getRmin();
        rmax = leer.getRmax();
        semilla = leer.getSemilla()[0];
        evaluaciones = leer.getEvaluaciones();
        nAlg = leer.getnAlg();

        System.out.println("Nombre del algoritmo: " + nombreAlg);
        System.out.println("Tamaño del problema: " + tam);
        System.out.println("Rango de valores: [" + rmin + ", " + rmax + "]");
        System.out.println("Semilla: " + semilla);


        solActual = new Vector<Double>(tam);

        for(int i = 0; i < 3; i++){
            double aux = BLocal.Blocal3(tam, evaluaciones, solActual,rmin,rmax,nAlg);

            //System.out.println("Vector solucion: ");
            //for(int j = 0; j < tam; i++){
            //    System.out.print(solActual.elementAt(j) + " ");
            // }

            System.out.println("Coste BLocal3: " + aux);

            
        }
    }
}
