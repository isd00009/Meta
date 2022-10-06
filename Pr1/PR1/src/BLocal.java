import java.util.Vector;
import java.util.Random;

public class BLocal {

    public double Blocal3(int tam, long evaluaciones, Vector<Double> SolActual, double rmin, double rmax, int selector){

        Random rand = new Random();

        for (int i=0; i<tam; i++){   
            SolActual.add(i, Math.floor(Math.random()*(rmax-rmin+1)+rmin));
        }

        Aux.mostrarVector(SolActual);
        
        Vector<Double> vecino = new Vector<Double>(tam);
        Vector<Double> mejorVecino;
        mejorVecino = SolActual;
        double mejorCosteVecino;
        double mejorCoste = CalculaCoste(SolActual,selector);
        int iter=0;
        boolean mejora=true;
        
        while (mejora && iter<evaluaciones) {
            mejora=false;
            mejorCosteVecino=Integer.MAX_VALUE;
        for (int j=1; j<=3; j++){ 
                for (int k=0; k<tam; k++){    //	Para k = 1 hasta d
                    float uniforme = rand.nextFloat();  //1aleatorio[0,1]
                    if (uniforme<=0.3) { //Si aleatorio < 0.3
                        double inf,sup;
                        inf=SolActual.get(k)*0.9;
                        sup=SolActual.get(k)*1.1;
                        if (inf<rmin)
                            inf=rmin;
                        if (sup>rmax)
                           sup=rmax;
                    
                        vecino.add(k,Math.floor(Math.random()*(sup-inf+1)+sup));
                    }
                    else 
                        vecino.add(k,SolActual.get(k)); 
                    
                }
                double costeVecino = CalculaCoste(vecino,selector);  //GriewankEvaluate(vecino);
                if (costeVecino<mejorCosteVecino){
                    mejorVecino = vecino;
                    mejorCosteVecino = costeVecino;
                }
            }
            if (mejorCosteVecino<mejorCoste){
                SolActual=mejorVecino; 
                mejorCoste=mejorCosteVecino;
                mejora=true;
                iter++;
            }
        }
        
        System.out.println("***********Iteraciones:" + iter);
        return mejorCoste;
    }

}
