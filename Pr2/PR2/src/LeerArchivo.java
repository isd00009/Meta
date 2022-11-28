import java.io.BufferedReader;
import java.io.FileReader;

public class LeerArchivo {

    private String nombreAlg;
    private int semillas[];
    private int semilla;
    private int tam;
    private int evaluaciones;
    private double rmin, rmax;
    private int nAlg;
    private String algoritmoEjecucion;

    public LeerArchivo() {
        nombreAlg = "";
        semillas = new int[5];
        tam = 0;
        evaluaciones = 0;
        rmin = 0;
        rmax = 0;
        nAlg = 0;
    }

    private void leerFuncEv(String nombreFichero) {
        semillas = new int[5];

        try {
            FileReader fr = new FileReader(nombreFichero);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split("=");
                switch (campos[0]) {
                    case "function":
                        String[] v = campos[1].split(" ");
                        nombreAlg = v[0];
                        rmin = Double.parseDouble(v[1]);
                        rmax = Double.parseDouble(v[2]);
                        break;
                    case "dni":
                        String[] v2 = campos[1].split(" ");
                        for (int i = 0; i < v2.length; i++) {
                            semillas[i] = Integer.parseInt(v2[i]);
                        }
                        switch (semilla) {
                            case 1:
                                semilla = semillas[0];
                                break;
                            case 2:
                                semilla = semillas[1];
                                break;
                            case 3:
                                semilla = semillas[2];
                                break;
                            case 4:
                                semilla = semillas[3];
                                break;
                            case 5:
                                semilla = semillas[4];
                                break;
                            default:
                                System.out.println("Semilla no válida");
                                break;
                        }
                        break;
                    case "evaluaciones":
                        evaluaciones = Integer.parseInt(campos[1]);
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

    public void leerConfig() {
        try {
            FileReader fr = new FileReader("./config.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split("=");
                switch (campos[0]) {
                    case "Algoritmo(EvM,EvBLX,ED)":
                        algoritmoEjecucion = campos[1];
                        break;
                    case "Semilla(1-5)":
                        semilla = Integer.parseInt(campos[1]);
                        break;
                    case "Función de evaluación":
                        leerFuncEv(campos[1] + ".txt");
                        break;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    // Getters

    public String getAlgoritmoEjecucion() {
        return algoritmoEjecucion;
    }

    public String getNombreAlg() {
        return nombreAlg;
    }

    public int getSemilla() {
        return semilla;
    }

    public int getTam() {
        return tam;
    }

    public int getEvaluaciones() {
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
