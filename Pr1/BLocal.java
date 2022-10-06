import java.util.Vector;

public class BLocal {

    public double Blocal3(int tam, long evaluaciones, Vector<Double> SolActual, double rmin, double rmax, int selector){

        for (int i=0; i<tam; i++){   
            SolActual.add(i, Randfloat(rmin, rmax));
        }
        
        mostrarVector(SolActual);
        
        vector<double> vecino;
        vecino.resize(tam);
        vector<double> mejorVecino;
        mejorVecino= SolActual;
        double mejorCosteVecino;
        double mejorCoste= CalculaCoste(SolActual,selector);
        int iter=0;
        bool mejora=true;
        
        while (mejora && iter<evaluaciones) {
            mejora=false;
            mejorCosteVecino=9999999999;
        for (int j=1; j<=3; j++){ 
                for (int k=0; k<tam; k++){    //	Para k = 1 hasta d
                    float uniforme= Randfloat(0,1.0001);  //1aleatorio[0,1]
                    if (uniforme<=0.3) { //Si aleatorio < 0.3
                        double inf,sup;
                        inf=SolActual[k]*0.9;
                        sup=SolActual[k]*1.1;
                        if (inf<rmin)
                            inf=rmin;
                        if (sup>rmax)
                           sup=rmax;
                    
                        vecino[k] = Randfloat(inf,sup);
                    }
                    else 
                        vecino[k] = SolActual[k];
                    
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
        
        cout << "***********Iteraciones:" << iter << endl;
        return mejorCoste;
    }

}
