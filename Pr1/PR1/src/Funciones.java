import java.util.Vector;

public class Funciones {

    public static double Griewank(Vector<Double> x) {
        double sum = 0;
        double prod = 1;
        int d = x.size();

        for (int i = 0; i < d; i++) {
            sum += ((x.get(i) * x.get(i)) / 4000);
            prod *= Math.cos(x.get(i) / Math.sqrt(i + 1));
        }
        return sum - prod + 1;
    }

    public static double Ackley(Vector<Double> x) {

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

    public static double Rastrigin(Vector<Double> x) {

        double d = x.size();
        double sum = 0;
        for (int i = 0; i < d; i++) {
            double xi = x.get(i);
            sum += ((xi * xi) - 10 * Math.cos(2 * Math.PI * xi));
        }

        return 10 * d + sum;

    }

    public static double Schwefel(Vector<Double> x) {

        double d = x.size();

        double sum = 0;
        for (int i = 0; i < d; i++) {
            double xi = x.get(i);
            sum = sum + xi * Math.sin(Math.sqrt(Math.abs(xi)));
        }

        return 418.9829 * d - sum;

    }

    public static double Perm0db(Vector<Double> x) {

        double d = x.size();
        int b = 10;
        double outer = 0;

        for (int i = 0; i < d; i++) {
            double inner = 0;
            for (int j = 0; j < d; j++) {
                double xj = x.get(j);
                inner += (j + 1 + b) * (Math.pow(xj, i + 1) - (1 / Math.pow(j+1, i+1)));
            }
            outer += inner * inner;
        }

        return outer;
    }

    public static double Rothyp(Vector<Double> x) {

        double d = x.size();
        double outer = 0;

        for (int i = 0; i < d; i++) {
            double inner = 0;
            for (int j = 0; j < i; j++) {
                double xj = x.get(j);
                inner += xj * xj;
            }
            outer += outer * inner;
        }

        return outer;
    }

    public static double Rosenbrock(Vector<Double> x) {

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

    public static double Michalewicz(Vector<Double> x) {
        double d = x.size();
        double m = 10;
        double suma = 0;
        for (int i = 0; i < d; i++) {
            suma += Math.sin(x.get(i))*Math.pow(Math.sin((i+1)*Math.pow(x.get(i),2)/Math.PI),2*m);
        }
        return -suma;
    }

    public static double Trid(Vector<Double> x) {
    
        double d = x.size(); 
        double sum2 =0;
        double sum1 = Math.pow(x.get(1)-1,2);
        
        for (int ii=1; ii<d; ii++){
            double xi = x.get(ii);
            double xold = x.get(ii-1);
            sum1 += Math.pow((xi-1),2);
            sum2 += xi*xold;
        }

        return sum1 - sum2;
    }

    public static double DixonPr(Vector<Double> x) {
    
        double d = x.size(); 
        double x1 = x.get(0);
    
        double term1 = Math.pow((x1-1),2);

        double sum = 0;
        for (int ii = 1; ii < d; ii++){
            double xi = x.get(ii);
            double xold = x.get(ii-1);
            double nuevo = ii * Math.pow((Math.pow (2*xi,2) - xold),2);
            sum = sum + nuevo;
        }

        return term1 + sum;
    }

    static double CalcularCoste(Vector<Double> x, int selector){
        switch(selector){
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
            default:
                return -1;
        }
    }

}
