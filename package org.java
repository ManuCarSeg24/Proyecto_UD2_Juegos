

import java.util.Scanner;

while (!juegoAcabado) { //Mientras que no se acabe el juego sigue funcionando
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
    if (juegoAcabado) { //Igual que como arriba si el disparo ha hundido todos los barcos del rival, termina el juego
        System.out.println("¡El Jugador 2 ha ganado!");
        break;
    }
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
        tableroOponente[fila][col] = 'X'; // Se marca el agua con 'X'
        System.out.println("¡Agua!");
        return false; // No se ha terminado el juego
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
    return false; // Si la otra parte no ha sido tocada, el barco sigue a flote
}