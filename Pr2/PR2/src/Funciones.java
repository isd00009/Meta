import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Funciones {

    public static double Griewank(ArrayList<Double> x) {
        double sum = 0;
        double prod = 1;
        int d = x.size();

        for (int i = 0; i < d; i++) {
            sum += ((x.get(i) * x.get(i)) / 4000);
            prod *= Math.cos(x.get(i) / Math.sqrt(i + 1));
        }
        return sum - prod + 1;
    }

    public static double Ackley(ArrayList<Double> x) {

        int d = x.size();

        double c = 2 * Math.PI;
        double b = 0.2;
        double a = 20;

        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < d; i++) {
            double xi = x.get(i);
            sum1 += xi * xi;
            sum2 += Math.cos(c * xi);
        }

        double term1 = -a * Math.exp(-b * Math.sqrt(sum1 / d));
        double term2 = -Math.exp(sum2 / d);

        return term1 + term2 + a + Math.exp(1);

    }

    public static double Rastrigin(ArrayList<Double> x) {

        double d = x.size();
        double sum = 0;
        for (int i = 0; i < d; i++) {
            double xi = x.get(i);
            sum += ((xi * xi) - 10 * Math.cos(2 * Math.PI * xi));
        }

        return 10 * d + sum;

    }

    public static double Schwefel(ArrayList<Double> x) {

        double d = x.size();

        double sum = 0;
        for (int i = 0; i < d; i++) {
            double xi = x.get(i);
            sum = sum + xi * Math.sin(Math.sqrt(Math.abs(xi)));
        }

        return 418.9829 * d - sum;

    }

    public static double Perm0db(ArrayList<Double> x) {

        double d = x.size();
        int b = 10;
        double outer = 0;

        for (int i = 0; i < d; i++) {
            double inner = 0;
            for (int j = 0; j < d; j++) {
                double xj = x.get(j);
                double aux = j + b + 1;
                double aux1 = Math.pow(xj, i + 1);
                double aux2 = (1 / Math.pow(j + 1, i + 1));
                inner += aux * (aux1 - aux2);
            }
            outer += inner * inner;
        }

        return outer;
    }

    public static double Rothyp(ArrayList<Double> x) {

        double d = x.size();
        double outer = 0;

        for (int i = 0; i < d; i++) {
            double inner = 0;
            for (int j = 0; j < i; j++) {
                double xj = x.get(j);
                inner += xj * xj;
            }
            outer += inner;
        }

        return outer;
    }

    public static double Rosenbrock(ArrayList<Double> x) {

        double d = x.size();
        double sum = 0;

        for (int ii = 0; ii < (d - 1); ii++) {
            double xi = x.get(ii);
            double xnext = x.get(ii + 1);
            double nuevo = 100 * Math.pow(xnext - (xi * xi), 2) + Math.pow((xi - 1), 2);
            sum = sum + nuevo;
        }

        return sum;
    }

    public static double Michalewicz(ArrayList<Double> x) {
        double d = x.size();
        double m = 10;
        double suma = 0;
        for (int i = 0; i < d; i++) {
            suma += Math.sin(x.get(i))
                    * Math.pow(Math.sin((i + 1) * Math.pow(x.get(i), 2) / Math.PI), 2 * m);
        }
        return -suma;
    }

    public static double Trid(ArrayList<Double> x) {

        double d = x.size();
        double sum2 = 0;
        double sum1 = Math.pow(x.get(1) - 1, 2);

        for (int ii = 1; ii < d; ii++) {
            double xi = x.get(ii);
            double xold = x.get(ii - 1);
            sum1 += Math.pow((xi - 1), 2);
            sum2 += xi * xold;
        }

        return sum1 - sum2;
    }

    public static double DixonPr(ArrayList<Double> x) {

        double d = x.size();
        double x1 = x.get(0);

        double term1 = Math.pow((x1 - 1), 2);

        double sum = 0;
        for (int ii = 1; ii < d; ii++) {
            double xi = x.get(ii);
            double xold = x.get(ii - 1);
            double nuevo = ii * Math.pow((Math.pow(2 * xi, 2) - xold), 2);
            sum = sum + nuevo;
        }

        return term1 + sum;
    }

    public static double MAPE(ArrayList<Double> real, ArrayList<Double> estimation) {
        int N = real.size();
        double score;
        double sum = 0.0;
        double num = 0.0;
        for (int i = 0; i < N; i++) {
            if (real.get(i) != 0) {
                sum += Math.abs((real.get(i) - estimation.get(i)) / Math.abs(real.get(i)));
                num++;
            }
        }
        score = sum / num;
        return score;
    }

    public static double RMSE(ArrayList<Double> real, ArrayList<Double> estimation) {
        int N = real.size();
        double score;
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.pow(real.get(i) - estimation.get(i), 2);
        }
        score = Math.sqrt(1.0 / N * sum);
        return score;
    }

    public static double potencia(ArrayList<Double> a, ArrayList<ArrayList<Double>> observations,
            int tipo) {

        ArrayList<Double> real = new ArrayList<Double>();
        ArrayList<Double> estimation = new ArrayList<Double>();
        double p, error = 0;

        for (int i = 0; i < observations.size(); i++) {
            p = observations.get(i).get(0) * (a.get(0) + (a.get(1) * observations.get(i).get(0))
                    + (a.get(2) * observations.get(i).get(2))
                    + (a.get(3) * observations.get(i).get(3))
                    + (a.get(4) * observations.get(i).get(4)));
            estimation.add(p);
            real.add(observations.get(i).get(5));
        }

        if (tipo == 1) {
            error = MAPE(real, estimation);
        } else if (tipo == 2) {
            error = RMSE(real, estimation);
        } else {
            error = -1;
        }

        return error;
    }

    static double CalcularCoste(ArrayList<Double> x, int selector,
            ArrayList<ArrayList<Double>> observations, int tipo) {
        switch (selector) {
            case 1:
                return Griewank(x);
            case 2:
                return Ackley(x);
            case 3:
                return Rastrigin(x);
            case 4:
                return Schwefel(x);
            case 5:
                return Perm0db(x);
            case 6:
                return Rothyp(x);
            case 7:
                return Rosenbrock(x);
            case 8:
                return Michalewicz(x);
            case 9:
                return Trid(x);
            case 10:
                return DixonPr(x);
            case 11:
                return potencia(x, observations, tipo);
            default:
                return -1;
        }
    }

    static void cargarDaido(String archivo, ArrayList<ArrayList<Double>> observations) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("DNI")) {
                    continue;
                }
                String[] campos = linea.split(",");
                ArrayList<Double> obs = new ArrayList<Double>();
                for (int i = 0; i < campos.length; i++) {
                    obs.add(Double.parseDouble(campos[i]));
                }
                observations.add(obs);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el fichero");
        }

    }

    static void cargaAleatoria(ArrayList<Double> al, int tam, double rmin, double rmax, Random r) {
        for (int i = 0; i < tam; i++) {
            al.add(rmin + (rmax - rmin) * r.nextDouble());
        }
    }

    static void cruceMedia(ArrayList<Double> v, ArrayList<Double> w, ArrayList<Double> h, int tam) {
        for (int i = 0; i < tam; i++) {
            h.add(i, (v.get(i) + w.get(i)) / 2);
        }
    }

    static void mutacion(ArrayList<Double> al, int pos, double valor) {
        al.set(pos, valor);
    }

    static void cruceBLX(ArrayList<Double> v, ArrayList<Double> w, ArrayList<Double> h, int tam,
            double alpha, double rmin, double rmax, Random r) {
        double cMax, cMin, I;
        for (int i = 0; i < tam; i++) {
            cMax = Math.max(v.get(i), w.get(i));
            cMin = Math.min(v.get(i), w.get(i));
            I = cMax - cMin;
            double r1 = cMin - alpha * I;
            double r2 = cMax + alpha * I;
            if (r1 < rmin) {
                r1 = rmin;
            }
            if (r2 > rmax) {
                r2 = rmax;
            }
            h.add(i, r1 + (r2 - r1) * r.nextDouble());
        }
    }
}

