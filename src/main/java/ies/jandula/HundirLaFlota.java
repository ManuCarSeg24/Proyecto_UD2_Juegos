package ies.jandula;

import java.util.Scanner;

public class HundirLaFlota {
    public static void main(String[] args) {
        
        //NOTA: Todo lo que he realizado han sido pruebas y todas las variables son provisionales
        //Esto que he puesto aqui es una guia de como dividirnos el trabajo
        //Nada de esto es definitivo, puesto va a ir cambiando por si nos hace falta otros metodos
    
        Scanner teclado = new Scanner(System.in);

        //Declaracion de variables

        char[][] taleroJugador1 = {{'A', 'A', 'A'}, {'A', 'A', 'A'}, {'A', 'A', 'A'}};
        char[][] taleroJugador2 = {{'A', 'A', 'A'}, {'A', 'A', 'A'}, {'A', 'A', 'A'}};
        char[][] taleroFinalJugador1 = new char[3][3];
        char[][] taleroFinalJugador2 = new char[3][3];

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
        colocarBarcos(taleroJugador1);
        System.out.println("Jugador 2, por favor coloca tus barcos");
        colocarBarcos(taleroJugador2);

        //Guardo en una nueva tabla la posición de los barcos, para luego pintarlo por pantalla.
        copiarTablero(taleroJugador1, taleroFinalJugador1);
        copiarTablero(taleroJugador2, taleroFinalJugador2);

        //esctructura más bien orientativa.
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
        char[][] copia = new char[tableroOriginal.length][tableroOriginal[0].length];

        for (int i = 0; i < tableroOriginal.length; i++) {
            for (int j = 0; j < tableroOriginal[i].length; j++) {
                copia[i][j] = tableroOriginal[i][j];
            }
        }
        //Con esto nos gaurdamos el tablero original para cuando tengamos que pintar por pantalla el tablero
        //El tablero del jugador lo hiremos modificando y será peor a la hora de pintarlo.
        return copia;
    }
}
