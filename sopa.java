package sopadelletres;

import Teclat.*;

public class SopaDeLletres {

    public static void main(String[] args) {

        //Creación del main sopa de letras M.Angel Altur 
        char[][] sopa;
        int columna;
        int fila;
        int opciones;
        int puntos;
        int jugada;
        int puntosTotal = 0;
        //crea el tamaño de la sopa
        sopa = creaSopa();

        //Muestra la sopa
        mostraSopa(sopa);

        do {

            //Hacer jugada & Acumular puntos
            puntos = ferJugada(sopa);
            puntosTotal += puntos;

            //Muestra la sopa
            mostraSopa(sopa);

            //Mostrar puntos    
            System.out.println("Puntos de la jugada " + puntos + ". Puntos totales " + puntosTotal);

        } while (fiPartida() == false);

    }//main

    /**
     * Fa la jugada: demana paraula i posició i la marca al tauler si estava
     *
     * @param tauler La sopa de lletres
     * @author Sergio M (a mitges)
     * @return els punts aconseguits en la jugada
     */
    static int ferJugada(char[][] tauler) {
        String paraula = Teclat.lligString("Dis-me la paraula");
        int fila = Teclat.lligInt("Posició inicial de la paraula en la fila: ", 1, tauler.length - 1);
        int columna = Teclat.lligInt("Posició inicial de la paraula en la columna: ", 1, tauler[0].length - 1);
        char direccio = Teclat.lligChar("Cap a quina direcció, Baix 'B' o Dreta 'D': ", "bd");
        int punts = 0;
        switch (direccio) {
            case 'B':
                if (existeixVertical(tauler, fila, columna, paraula)) {
                    marcaVertical(tauler, fila, columna, paraula.length());
                    punts = paraula.length();
                } else {
                    punts = -10;
                }
                break;
            case 'D':
                if (existeixHoritzontal(tauler, fila, columna, paraula)) {
                    marcaHoritzontal(tauler, fila, columna, paraula.length());
                    punts = paraula.length();
                } else {
                    punts = -10;
                }
                break;

        }
        return punts;
    }

    /**
     * @author Erik Pregunta si vols continuar
     * @return False en cas de voler continuar jugant, True en cas contrari
     */
    static boolean fiPartida() {
        return !Teclat.lligBoolean("Vols continuar la partida ");

    }

    /**
     *
     * Muestra la sopa Muestra la sopa
     *
     * @ author Fernando José Pereira
     * @param sopa Matriz que se le pasa
     */
    public static void mostraSopa(char sopa[][]) {

        /* Imprimimos la primera linea de numeros */
        System.out.println(cadenaNumeros(sopa[0].length - 1));

        /* Imprimimos las letras con el numero al final */
        for (int fil = 1; fil < sopa.length; fil++) {

            if (fil < 10) {
                System.out.print(fil + "  ");
            } else {
                System.out.print(fil + " ");

            }
            for (int col = 0; col < sopa[0].length; col++) {
                System.out.print(sopa[fil][col]);
                System.out.print("  ");

            }
            System.out.print(fil);

            System.out.println();
        }
        /* Imprimimos la linea final de numeros */
        System.out.println(cadenaNumeros(sopa[0].length - 1));
    }

    /**
     * Funcio en la que demana una quantitat de files i columnes i crea una
     * matriu de caràcters anomenada tauler.
     *
     * @return la matriu tauler amb eixa quantitat de files i columnes,
     * @author sergio
     */
    static char[][] creaSopa() {
        int qFiles = Teclat.lligInt("Quantes files tindrà el tauler: ", 5, 50);
        System.out.println();
        int qColumnes = Teclat.lligInt("Quantes columnes tindrà el tauler: ", 5, 50);
        char[][] tauler = new char[qFiles + 1][qColumnes + 1];
        System.out.println();
        char opcio = Teclat.lligChar("Aleatoria (A), o per teclat (T): ", "at");
        if (opcio == 'A') {
            ompliAleatori(tauler);
        } else {
            ompliPerTeclat(tauler);
        }
        return tauler;
    }

    /**
     * Funció per a comprovar si existeix la paraula en horitzontal.
     *
     * @param tauler es la matriu de la sopa
     * @param filIni la fila d'inici on comença la paraula
     * @param colIni la columna d'inici on comença la paraula
     * @param paraula paraula que comprovarem si existeix
     * @return true o false depenent de si existeix la paraula
     * @autor Isabel Valiente Pardo
     */
    public static boolean existeixHoritzontal(char tauler[][], int filIni, int colIni, String paraula) {
        for (int i = 0; i < paraula.length(); i++) {

            if (!(paraula.charAt(i) == tauler[filIni][colIni + i])) {
                return false;

            }

        }
        return true;
    }

    /**
     * Posa en mayuscula la paraula seleccionada en vertical
     *
     * @param tauler sopa de lletres
     * @param filaI fila on comença la paraula
     * @param columnaI columna on comença la paraula
     * @param longitut quantes lletres te la paraula
     * @author Rubén Altur
     *
     */
    private static void marcaVertical(char[][] tauler, int filaI, int columnaI, int longitut) {
        for (int fila = 0; fila < longitut; fila++) {
            tauler[filaI + fila][columnaI] = (Character.toString(tauler[filaI + fila][columnaI]).toUpperCase()).charAt(0);
        }

    }

    /**
     * @author Hugo Vaya Simeon
     * @param tauler es on esta la sopa de lletres
     * @param filaInicial es la fila on comença la paraula
     * @param columnaInicial es la columna on comença la paraula
     * @param paraula es la paraula que es comprova
     * @return true retorna si es correcta la paraula
     */
    public static boolean existeixVertical(char tauler[][], int filaInicial, int columnaInicial, String paraula) {

        for (int i = 0; i < paraula.length(); i++) {

            if (!(paraula.charAt(i) == tauler[filaInicial + i][columnaInicial])) {

                return false;
            }
        }

        return true;
    }

    /**
     * @author Daniel Zanón López Transforma la cantidad de numeros (int) a
     * string
     * @param cantCol Cantidad de columnas
     * @return Devuelve la candidad de columnas en string
     */
    static String cadenaNumeros(int cantCol) {
        String cadena = "     ";
        for (int i = 1; i <= cantCol; i++) {

            cadena += i;
            if (i < 10) {
                cadena += "  ";
            } else {
                cadena += " ";
            }
        }
        return cadena;
    }

    /**
     * @author Perpiña Piles Juan Omplir la matriu sopa amb lletres aleatóries
     * @param sopa Matriu on es posaran les lletres de forma aleatoria
     */
    static void ompliAleatori(char sopa[][]) {

        for (int fila = 1; fila < sopa.length; fila++) {
            for (int columna = 1; columna < sopa[0].length; columna++) {
                sopa[fila][columna] = lletraAleatoria();
            }
        }
    }

    /**
     * Ompli la sopa de paraules introduïdes per teclat.
     *
     * @author Frank Carreres Catalá
     * @param sopa El tauler de la sopa de lletres
     */
    static void ompliPerTeclat(char sopa[][]) {

        for (int fila = 1; fila < sopa.length; fila++) {

            String paraula = "";

            while (paraula.length() < sopa[0].length) {

                paraula = Teclat.lligString("- Quina paraula vols colocar a la fila " + fila + ": ");
            }
            for (int col = 1; col < sopa[0].length; col++) {

                sopa[fila][col] = paraula.charAt(col - 1);

            }

        }
    }

    /**
     * @author Joel Malonda ৻( •̀ ᗜ •́ ৻) Calcula una lletra aleatòria i la
     * retorna
     * @return retornarà una lletra aleatòria
     */
    public static char lletraAleatoria() {

        int num;
        String lletres = "abcdefghijklmnñopqrstuvwxyz";

        num = (int) (Math.random() * lletres.length());

        char lletra = lletres.charAt(num);

        return lletra;

    }

    /**
     * @author Antonio
     * @param tauler es el tablero de sopa
     * @param filainicial fila donde comienza la palabra
     * @param columnainicial columna donde comienza la palabra
     * @param longitudParaula longitud de palabra Pone en mayuscula la palabra
     * en horizontal
     */
    private static void marcaHoritzontal(char tauler[][], int filainicial, int columnainicial, int logitudParaula) {
        for (int c = columnainicial; c < columnainicial + logitudParaula; c++) {
            tauler[filainicial][c] = Character.toUpperCase(tauler[filainicial][c]);
        }
    }
}
