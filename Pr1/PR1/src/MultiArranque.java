import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class MultiArranque {

    private final static double probabilidad = 0.3;
    private final static double intervaloBajo = 1 - 0.1;
    private final static double intervaloAlto = 1 + 0.1;

    public double busquedaTabu(int iteraciones, int tam, Vector<Double> solActual, double rmin,
            double rmax, int tenenciaTabu, int selector) {

        Random rand = new Random();
        int cont;
        double inf, sup;

        for (int i = 0; i < tam; i++) {
            solActual.add(i, Math.floor(Math.random() * (rmax - rmin + 1) + rmin));
        }

        System.out.println("Vector solucion inicial: ");
        Aux.mostrarVector(solActual);

        double costeActual = Funciones.CalcularCoste(solActual, selector);
        double costeMejorPeor, costeGlobal = costeActual, costeMejorMomento = Integer.MAX_VALUE;
        int OEMejoraI = 0, OEnoMejoraI = 0, OEMejoraD = 0, OEnoMejoraD = 0, osc = 0;

        long memFrec[][] = new long[10][10];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                memFrec[i][j] = 0;
            }
        }

        List<Vector<Double>> listaTabu = new Vector<Vector<Double>>();

        List<Vector<Integer>> listaTabuMov = new Vector<Vector<Integer>>();

        Vector<Integer> cambiosVecino = new Vector<Integer>(tam);
        Vector<Integer> cambiosMejorVecino = new Vector<Integer>();

        listaTabu.add(solActual);

        Vector<Double> mejorPeores, SolGlobal = solActual, nuevaSol;
        nuevaSol = new Vector<Double>(tam);
        mejorPeores = new Vector<Double>();

        int iterador;
        cont = 0;
        boolean mejora;

        Vector<Double> vecino = new Vector<Double>(tam);
        Vector<Double> mejorVecino = new Vector<Double>();
        double mejorCosteVecino = Integer.MAX_VALUE;

        int contNoTabu;
        int multiArranque = 1;

        iterador = 0;

        while (iterador < iteraciones) {
            iterador++;
            mejora = false;
            costeMejorPeor = Integer.MAX_VALUE;
            contNoTabu = 0;
            int x = (int) Math.floor(Math.random() * (10 - 4 + 1) + 4);
            for (int j = 1; j <= x; j++) {
                for (int k = 0; k < tam; k++) {
                    float uniforme = rand.nextFloat();
                    if (uniforme <= probabilidad) {
                        cambiosVecino.add(k, 1);
                        if (multiArranque == 1) {
                            inf = solActual.get(k) * intervaloBajo;
                            sup = solActual.get(k) * intervaloAlto;

                            if (solActual.get(k) < 0) {
                                double aux = inf;
                                inf = sup;
                                sup = aux;
                            }

                            if (inf < rmin) {
                                inf = rmin;
                            }
                            if (sup > rmax) {
                                sup = rmax;
                            }
                            vecino.add(k, Math.floor(Math.random() * (sup - inf + 1) + inf));
                        } else {
                            if (multiArranque == 2) {
                                vecino.add(k, Math.floor(Math.random() * (rmax - rmin + 1) + rmin));
                            } else {
                                vecino.add(k, solActual.get(k) * -1);
                            }
                        }
                    } else {
                        vecino.add(k, solActual.get(k));
                        cambiosVecino.add(k, 0);
                    }
                }
                // Aux.mostrarVector(solActual);
                // Aux.mostrarVector(vecino);
                boolean tabu = false;

                for (Vector<Double> v : listaTabu) {
                    int c = 0;
                    for (int i = 0; i < tam; i++) {
                        double valor = v.get(i);
                        inf = valor * 0.99;
                        sup = valor * 1.01;
                        if (valor < 0) {
                            double aux = inf;
                            inf = sup;
                            sup = aux;
                        }
                        if (vecino.get(i) < inf || vecino.get(i) > sup) {
                            c++;
                            break;
                        }
                    }
                    if (c == 0) {
                        tabu = true;
                        break;
                    }
                }

                if (!tabu) {
                    contNoTabu++;
                    double costeVecino = Funciones.CalcularCoste(vecino, selector);
                    if (costeVecino < mejorCosteVecino) {
                        mejorVecino = vecino;
                        mejorCosteVecino = costeVecino;
                        cambiosMejorVecino = cambiosVecino;
                    }
                }

            }

            if (contNoTabu != 0) {
                double ancho = (rmax - rmin - 1) / 10;
                for (int i = 0; i < tam; i++) {
                    int posCol = 0;
                    for (double j = rmin; j < rmax; j += ancho) {
                        if (j < 0) {
                            if (Math.abs(mejorVecino.get(i)) >= Math.abs(j)
                                    && Math.abs(mejorVecino.get(i)) < Math.abs(j + ancho)) {
                                memFrec[i][posCol]++;
                                break;
                            }
                        } else {
                            if (mejorVecino.get(i) >= j && mejorVecino.get(i) < j + ancho) {
                                memFrec[i][posCol]++;
                                break;
                            }
                        }
                        posCol++;
                    }
                }

                listaTabu.add(mejorVecino);
                if (listaTabu.size() > tenenciaTabu) {
                    listaTabu.remove(0);
                }

                listaTabuMov.add(cambiosMejorVecino);
                if (listaTabuMov.size() > tenenciaTabu) {
                    listaTabuMov.remove(0);
                }

                if (mejorCosteVecino < costeActual) {
                    mejora = true;
                    costeActual = mejorCosteVecino;
                    solActual = mejorVecino;
                } else {
                    if (mejorCosteVecino < costeMejorPeor) {
                        costeMejorPeor = mejorCosteVecino;
                        mejorPeores = solActual;
                    }
                }

                if (!mejora) {
                    costeActual = costeMejorPeor;
                    solActual = mejorPeores;
                    cont++;

                    multiArranque = (multiArranque + 1) % 4;
                    if (multiArranque == 0) {
                        multiArranque = 1;
                    }
                } else {
                    cont = 0;
                    if (costeActual < costeGlobal) {
                        costeGlobal = costeActual;
                        SolGlobal = solActual;
                    }
                }

                if (cont == 50) {
                    if (osc == 0) {
                        if (costeMejorMomento > costeActual) {
                            costeMejorMomento = costeActual;
                            OEMejoraD++;
                        } else {
                            OEnoMejoraD++;
                        }
                    } else {
                        if (costeMejorMomento > costeActual) {
                            costeMejorMomento = costeActual;
                            OEMejoraI++;
                        } else {
                            OEnoMejoraI++;
                        }
                    }
                    cont = 0;

                    int prob = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1);
                    if (prob <= 50) {
                        osc = 0;
                        menosVisitados(memFrec, nuevaSol, rmin, rmax);
                    } else {
                        osc = 1;
                        masVisitados(memFrec, nuevaSol, rmin, rmax);
                    }

                    solActual = nuevaSol;
                    costeActual = Funciones.CalcularCoste(solActual, selector);

                    if (costeActual < costeGlobal) {
                        costeGlobal = costeActual;
                        SolGlobal = solActual;
                    }

                    for (int i = 0; i < tam; i++) {
                        for (int j = 0; j < 10; j++) {
                            memFrec[i][j] = 0;
                        }
                    }

                    listaTabu.clear();
                }

            }

        }

        System.out.println();
        solActual = SolGlobal;

        return costeGlobal;

    }

    public void masVisitados(long[][] memFrec, Vector<Double> nuevaSol, double rmin, double rmax) {
        memFrec = new long[10][10];
        int tam = nuevaSol.size();
        double mayor;
        int pc = 0;
        int columnas[];
        columnas = new int[3];

        for (int i = 0; i < tam; i++) {
            for (int k = 0; k < 3; k++) {
                mayor = -1;
                for (int j = 0; j < 10; j++) {
                    if (memFrec[i][j] >= mayor) {
                        mayor = memFrec[i][j];
                        pc = j;
                    }
                }
                columnas[k] = pc;
                memFrec[i][pc] = -1;
            }

            int aleatorio = (int) Math.floor(Math.random() * (2 - 0 + 1) + 0);
            int col = columnas[aleatorio];
            double ancho = (rmax - rmin + 1) / 10;
            double ini = rmin + (col * ancho);
            double fin = ini + ancho;
            nuevaSol.add(i, Math.floor(Math.random() * (fin - ini + 1) + ini));
        }
    }

    public void menosVisitados(long[][] memFrec, Vector<Double> nuevaSol, double rmin,
            double rmax) {
        memFrec = new long[10][10];
        int tam = nuevaSol.size();
        double menor;
        int pc = 0;
        int columnas[];
        columnas = new int[3];

        for (int i = 0; i < tam; i++) {
            for (int k = 0; k < 3; k++) {
                menor = Integer.MAX_VALUE;
                for (int j = 0; j < 10; j++) {
                    if (memFrec[i][j] <= menor) {
                        menor = memFrec[i][j];
                        pc = j;
                    }
                }
                columnas[k] = pc;
                memFrec[i][pc] = Integer.MAX_VALUE;
            }

            int aleatorio = (int) Math.floor(Math.random() * (2 - 0 + 1) + 0);
            int col = columnas[aleatorio];
            double ancho = (rmax - rmin + 1) / 10;
            double ini = rmin + (col * ancho);
            double fin = ini + ancho;
            nuevaSol.add(i, Math.floor(Math.random() * (fin - ini + 1) + ini));
        }
    }

}
