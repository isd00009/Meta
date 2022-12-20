import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LeerArchivo {

    int semilla, iteraciones, poblacion;
    double greedy;
    int alfa, beta;
    double q0, p, fi;
    String ficheroALeer;
    ArrayList<ArrayList<Double>> dist;
    int n;

    public LeerArchivo() {}

    public void leerFich(String nombreFichero) {
        ArrayList<ArrayList<Double>> coordenadas = new ArrayList<ArrayList<Double>>();
        int x;
        // Read a file but ignore the first 3 lines
        try {
            FileReader fr = new FileReader(nombreFichero);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                if (i == 3) {
                    String[] campos = linea.split(":");
                    // read the numer after the colon
                    String[] campos2 = campos[1].split(" ");
                    // read the number after the space
                    n = Integer.parseInt(campos2[1]);
                } else if (i > 5 && i < n + 6) {
                    // store the first number in x and the rest in the coordinates matrix
                    String[] campos = linea.split(" ");
                    x = Integer.parseInt(campos[0]);
                    ArrayList<Double> aux = new ArrayList<Double>();
                    for (int j = 1; j < campos.length; j++) {
                        aux.add(Double.parseDouble(campos[j]));
                    }
                    coordenadas.add(aux);
                }
                i++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        dist = new ArrayList<ArrayList<Double>>(n);
        for (int i = 0; i < n; i++) {
            ArrayList<Double> aux = new ArrayList<Double>(n);
            for (int j = 0; j < n; j++) {
                aux.add(0.0);
            }
            dist.add(aux);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double distancia = Math.sqrt(
                        Math.pow(coordenadas.get(i).get(0) - coordenadas.get(j).get(0), 2) + Math
                                .pow(coordenadas.get(i).get(1) - coordenadas.get(j).get(1), 2));
                dist.get(i).set(j, distancia);
                dist.get(j).set(i, distancia);
            }
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
                    case "archivo":
                        ficheroALeer = campos[1];
                        break;
                    case "semilla":
                        semilla = Integer.parseInt(campos[1]);
                        break;
                    case "iteraciones":
                        iteraciones = Integer.parseInt(campos[1]);
                        break;
                    case "poblacion":
                        poblacion = Integer.parseInt(campos[1]);
                        break;
                    case "greedy":
                        greedy = Double.parseDouble(campos[1]);
                        break;
                    case "alpha":
                        alfa = Integer.parseInt(campos[1]);
                        break;
                    case "beta":
                        beta = Integer.parseInt(campos[1]);
                        break;
                    case "q0":
                        q0 = Double.parseDouble(campos[1]);
                        break;
                    case "p":
                        p = Double.parseDouble(campos[1]);
                        break;
                    case "fi":
                        fi = Double.parseDouble(campos[1]);
                        break;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int getSemilla() {
        return semilla;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public double getGreedy() {
        return greedy;
    }

    public int getAlfa() {
        return alfa;
    }

    public int getBeta() {
        return beta;
    }

    public double getQ0() {
        return q0;
    }

    public double getP() {
        return p;
    }

    public double getFi() {
        return fi;
    }

    public String getFicheroALeer() {
        return ficheroALeer;
    }

    public ArrayList<ArrayList<Double>> getDist() {
        return dist;
    }

    public int getN() {
        return n;
    }
}
