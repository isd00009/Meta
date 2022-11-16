

public class Main {
    public static void main(String[] args) throws Exception {

        int tam;
        String nombreAlg;
        int nAlg;
        double rmin, rmax;
        long semilla, evaluaciones;
        int op = 1;

        for (int i = 0; i < 5; i++) {
            LeerArchivo leer = new LeerArchivo("Ackley.txt");
            tam = leer.getTam();
            nombreAlg = leer.getNombreAlg();
            rmin = leer.getRmin();
            rmax = leer.getRmax();
            semilla = leer.getSemilla()[i];
            evaluaciones = leer.getEvaluaciones();
            nAlg = leer.getnAlg();

            EscribirArchivo escribir = new EscribirArchivo(nombreAlg, tam, rmin, rmax, semilla,
                    evaluaciones, nAlg, op);
        }


    }
}
