import java.util.Vector;

public class Pr1 {
    public static void main(String[] args) throws Exception {
        
        Vector<Double> solActual;
        int tam;
        String nombreAlg;
        int nAlg;
        int tTabu = 10;
        double rmin, rmax;
        long semilla, evaluaciones;
        double aux = 0;

        LeerArchivo leer = new LeerArchivo("DixonPr.txt");
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

        //for(int i = 0; i < 3; i++){
            long startTime = System.currentTimeMillis();
            aux = BLocal.Blocal3(tam, evaluaciones, solActual, rmin, rmax, nAlg);
            //aux = MultiArranque.busquedaTabu(evaluaciones,tam, solActual,rmin,rmax,tTabu,nAlg);
            long endTime = System.currentTimeMillis();

            //System.out.println("Vector solucion: ");
            //for(int j = 0; j < tam; i++){
            //    System.out.print(solActual.elementAt(j) + " ");
            // }

            System.out.println("Coste BLocal3: " + aux);
            System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");

            
       // }
    }
}
