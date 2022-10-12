import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class LeerArchivo {

    private String nombreAlg;
    private long semilla[];
    private int tam;
    private long evaluaciones;
    private double rmin, rmax;
    private int nAlg;


    public LeerArchivo(String fichero){

        semilla = new long[5];

        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split("=");
                switch(campos[0]){
                    case "function":
                        String[] v = campos[1].split(" ");
                        nombreAlg = v[0];
                        rmin = Double.parseDouble(v[1]);
                        rmax = Double.parseDouble(v[2]);
                        break;
                    case "dni":
                        String[] v2 = campos[1].split(" ");
                        for(int i=0; i<v2.length; i++){
                            semilla[i] = Long.parseLong(v2[i]);
                        }
                        break;
                    case "evaluaciones":
                        evaluaciones = Long.parseLong(campos[1]);
                        break;
                    case "d":
                        tam = Integer.parseInt(campos[1]);
                        break;
                    case "n":
                        nAlg = Integer.parseInt(campos[1]);
                        break;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void cargarSolucion(Vector<Integer> s, int tam){
        for(int i = 0; i < tam; i++){
            s.addElement(i);
        }

        int r;
        for(int i = tam - 1; i > 0; i--){
            r = (int) (Math.random() * (i + 1));
            int aux = s.elementAt(i);
            s.setElementAt(s.elementAt(r), i);
            s.setElementAt(aux, r);
        }
    }

    //Getters
    public String getNombreAlg() {
        return nombreAlg;
    }

    public long[] getSemilla() {
        return semilla;
    }

    public int getTam() {
        return tam;
    }

    public long getEvaluaciones() {
        return evaluaciones;
    }

    public double getRmin() {
        return rmin;
    }

    public double getRmax() {
        return rmax;
    }

    public int getnAlg() {
        return nAlg;
    }

}
