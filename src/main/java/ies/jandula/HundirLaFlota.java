package ies.jandula;

import java.util.Scanner;

public class HundirLaFlota {
    public static void main(String[] args) {

        //NOTA: Todo lo que he realizado han sido pruebas y todas las variables son provisionales
        //Esto que he puesto aqui es una guia de como dividirnos el trabajo
        //Nada de esto es definitivo, puesto va a ir cambiando por si nos hace falta otros metodos

        Scanner teclado = new Scanner(System.in);

        //Declaracion de variables

        char[][] tableroJugador1 = {{'A', 'A', 'A'}, {'A', 'A', 'A'}, {'A', 'A', 'A'}};
        char[][] tableroJugador2 = {{'A', 'A', 'A'}, {'A', 'A', 'A'}, {'A', 'A', 'A'}};
        char[][] tableroFinalJugador1 = new char[3][3];
        char[][] tableroFinalJugador2 = new char[3][3];
        boolean juegoAcabado = false;  //Alejandro

        //Introducimos un mensaje de bienvenida y una pequeña explicación del juego.
        System.out.println("¡Bienvenido jugadores al juego de hundir la flota!");
        System.out.println("Vamos a explicar brevemente las reglas del juego.");
        System.out.println("Cada jugador tendrá un tablero de 3x3 y dos barcos que colocará en secreto.");
        System.out.println("Cada barco ocupa dos lugares en el tablero.");
        System.out.println("Para colocarlos, debemos introducir la fila y la columna a la que queremos poner nuestro barco.");
        System.out.println("Las filas y las columnas estan formadas del 1 al 3.");
        System.out.println("Por ejemplo: mi barco esta colocado en la fila 1 columna 1 fila 2 columna 1");
        System.out.println("Una vez colocados, empezaremos a disparar al tablero del otro jugador.");
        System.out.println("Si no tocamos el barco, nos dirá que hemos dado en el Agua.");
        System.out.println("Si tocamos el barco, nos dirá Tocado y el barco estará dañado pero aun sigue participando.");
        System.out.println("Si tocamos por completo el barco, nos dira tocado y hundido, con esto eliminamos el barco.");
        System.out.println("Gana el jugador que hunda todos los barcos del jugador rival.");
        System.out.println("Aclarado todo esto, ¡Empezemos la partida!");
        System.out.println();
        System.out.println();

        //Le pedimos a los jugadores que nos introduzcan las posiciones de los barcos.
        System.out.println("Jugador 1, por favor coloca tus barcos");
        colocarBarcos(tableroJugador1);
        System.out.println("Jugador 2, por favor coloca tus barcos");
        colocarBarcos(tableroJugador2);

        //Guardo en una nueva tabla la posición de los barcos, para luego pintarlo por pantalla.
        tableroFinalJugador1 = copiarTablero(tableroJugador1);
        tableroFinalJugador2 = copiarTablero(tableroJugador2);
        
        while (!juegoAcabado) {
            // Turno Jugador 1
            System.out.println("Turno del Jugador 1:");
            juegoAcabado = realizarDisparo(tableroJugador2); //Disparo al tablero del jugador 2
            if (juegoAcabado) { //Si el disparo ha hundido todos los barcos del rival, termina el juego
                System.out.println("¡El Jugador 1 ha ganado!");
                break;
            }

            // Turno Jugador 2
            System.out.println("Turno del Jugador 2:");
            juegoAcabado = realizarDisparo(tableroJugador1); //Casi igual que arriba, disparo al tablero del jugador 1
            if (juegoAcabado) {  //Igual que como arriba si el disparo ha hundido todos los barcos del rival, termina el juego
                System.out.println("¡El Jugador 2 ha ganado!");
                break;
            }
        }

        mostrarTableroFinal(tableroFinalJugador1, "Jugador 1");
        mostrarTableroFinal(tableroFinalJugador2, "Jugador 2");

    
    }
    //Apartir de aqui, trabajaremos con funciones (mientras hablamos si tenemos problemas entre nosotros), cada uno nos repartiremos las funciones.
    //Uno de nosotros (Alumno A) se dedicará a introducir los barcos, validar las posiciones, que puedan estar adyacentes y no se solapen.
    //El Alumno B se dedicara a las funciones de los disparos, de verificar si ha tocado, si ha dado agua o si ha tocado y hundido.
    //Y el Alumno A se dedicará a recoger ese resultado, finalizar el juego y pintar por pantalla las posiciones de los barcos delos jugadores en los tableros.
    public static void colocarBarcos(char[][] tablero) {

        Scanner teclado = new Scanner(System.in);

        //declaración de variables
        int contadorBarcos = 1;
        int fila1 = 0;
        int col1 = 0;
        int fila2 = 0;
        int col2 = 0;

        //Realizamos un bucle para introducir los barcos.
        while (contadorBarcos <= 2) {
            System.out.println("Introduce la posición del barco " + contadorBarcos + " (debes introducir las coordenadas por fila y columna)");
            System.out.println("Introduce la fila 1: ");
            fila1 = teclado.nextInt() - 1;
            System.out.println("Introduce la columna 1: ");
            col1 = teclado.nextInt() - 1;
            System.out.println("Introduce la fila 2: ");
            fila2 = teclado.nextInt() - 1;
            System.out.println("Introduce la columna 2: ");
            col2 = teclado.nextInt() - 1;

            //Con este if me aseguro de que no meten un digito erroneo.
            if (fila1 < 0 || fila1 >= 3 || col1 < 0 || col1 >= 3 || fila2 < 0 || fila2 >= 3 || col2 < 0 || col2 >= 3) {
                System.out.println("Los parametros introducidos no son validos.");
                continue;
            }
            //Con este if me aseguro de que no coloquen barcos en el mismo sitio.
            if (tablero[fila1][col1] == 'B' || tablero[fila2][col2] == 'B') {
                System.out.println("No puedes colocar un barco en el mismo lugar.");
                continue;
            }
            //Con este if (me he tenido que ayudar de internet para sacar esto).
            //Hago que el barco sea de dos unidades adyacentes.
            //Reconozcon que sin internet, a lo mejor no lo sacaba, pero era lo que tenía en mente y queria hacer.
            if ((fila1 == fila2 && Math.abs(col1 - col2) == 1) || (col1 == col2 && Math.abs(fila1 - fila2) == 1)) {
                tablero[fila1][col1] = 'B';
                tablero[fila2][col2] = 'B';
                contadorBarcos++;
            } else {
                //Si meten algún segundo barco que solape con el primero o que las posiciones del barco no esten adyacentes, les saldrá este mensaje.
                System.out.println("Posición inválida. Asegúrate de que el barco es de dos posiciones adyacentes y no se solapan.");
            }

        }
    }
    public static char[][] copiarTablero(char[][] tableroOriginal) {

        //Esta función es básica, leemos el array del tablero de cualquier jugador y la copiamos en el nuevo tablero.
        char[][] copia = new char[tableroOriginal.length][tableroOrigina.length];

        for (int fila = 0; fila < tableroOriginal.length; fila++) {
            for (int col = 0; col < tableroOriginal[fila].length; col++) {
                copia[fila][col] = tableroOriginal[fila][col];
            }
        }
        //Con esto nos gaurdamos el tablero original para cuando tengamos que pintar por pantalla el tablero
        //El tablero del jugador lo hiremos modificando y será peor a la hora de pintarlo.
        return copia;
    }
    public static boolean realizarDisparo(char[][] tableroOponente) {

        Scanner teclado = new Scanner(System.in);

        int fila = 0;
        int col = 0;

        //Pedimos al jugador que introduzca las coordenadas del disparo
        System.out.println("Introduce las coordenadas de tu disparo (formato: fila columna):");
        System.out.println("Introduce la fila: ");
        fila = teclado.nextInt() - 1; //restamos 1 para ajustarlo a los índices del array
        System.out.println("Introduce la columna: ");
        col = teclado.nextInt() - 1;

        //Verificamos si la coordenada está dentro del tablero
        if (fila < 0 || fila >= 3 || col < 0 || col >= 3) {
            System.out.println("Coordenadas fuera de los límites. Pierdes tu turno.");
            return false;
        }

        // Verificamos si el disparo ya se realizó en esa posición
        if (tableroOponente[fila][col] == 'X' || tableroOponente[fila][col] == 'T') {
            System.out.println("Ya atacaste esta posición. Pierdes tu turno.");
            return false;
        }

        // Si hay un barco en la posición, lo marcamos como 'T' (Tocado)
        if (tableroOponente[fila][col] == 'B') {
            tableroOponente[fila][col] = 'T';
            System.out.println("¡Tocado!");

            // Verificamos si el barco ha sido completamente hundido
            if (hundirBarco(fila, col, tableroOponente)) {
                tableroOponente[fila][col] = 'X';
                System.out.println("    y   ");
                System.out.println("¡Hundido!");
            }

            // Revisamos si quedan barcos en el tablero
            for (int i = 0; i < tableroOponente.length; i++) {
                for (int j = 0; j < tableroOponente[i].length; j++) {
                    if (tableroOponente[i][j] == 'B') {
                        return false; // Aún quedan barcos, el juego sigue
                    }
                }
            }
            return true; //Todos los barcos hundidos.

        } else {
            tableroOponente[fila][col] = 'X';
            System.out.println("¡Agua!");
            return false;
        }
    }
    public static boolean hundirBarco(int fila, int col, char[][] tableroOponente) {
        // Verificar que el barco tocado está en dos partes
        if (tableroOponente[fila][col] == 'T') {
            // Buscar la otra parte del barco
            if (fila > 0 && tableroOponente[fila - 1][col] == 'T') {
                return true; // Parte del barco horizontal hacia la izquierda.
            } else if (fila < 2 && tableroOponente[fila + 1][col] == 'T') {
                return true; // Parte del barco horizontal hacia la derecha.
            } else if (col > 0 && tableroOponente[fila][col - 1] == 'T') {
                return true; // Parte del barco vertical hacia abajo.
            } else if (col < 2 && tableroOponente[fila][col + 1] == 'T') {
                return true; // Parte del barco vertical hacia arriba.
            }
        }
        return false;
    }
    public static void mostrarTableroFinal(char[][] tablero, String jugador) {
        //Creamos esta función para recorrer los tableros de los jugadores.
        
        System.out.println("Tablero final del " + jugador + " :");
        //Mostramos por pantalla que jugador es.

        //Con este bucle for anidado recorremos todo la arrya del jugador.
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int col = 0; col < tablero.length; col++) {

                //creamos un if, si encuentra una B en la posición, pinta por pantalla un 'B'.
                if (tablero[fila][col] == 'B') {
                    System.out.print("B ");
                    //nota: utilizamos un print para que escriba en la misma línea.
                } else {
                    //Si no encuenta un B, con el else nos mete aqui y nos pinta un 'A'.
                    System.out.print("A ");
                }
            }
            System.out.println();
            //Saltamos de línea.
        }
    }

}

