import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase principal que contiene el flujo de la pelea y el metodo main.
 */
public class JuegoLucha {
    
    private Personaje jugador1;
    private Personaje jugador2;

    // Constructor que recibe los dos personajes a enfrentar
    public JuegoLucha(Personaje p1, Personaje p2) {
        this.jugador1 = p1;
        this.jugador2 = p2;
    }
    
    /**
     * Metodo que inicializa y controla el ciclo de la pelea.
     * Recibe el Scanner creado en main.
     */
    public void iniciarPelea(Scanner scanner) {
        System.out.println("\n----------------------------------");
        System.out.println("       COMIENZA EL DUELO MMA      ");
        System.out.println("----------------------------------");
        System.out.println("La pelea es: " + jugador1.getNombre() + " (" + jugador1.getClass().getSimpleName() + ") vs. " 
                           + jugador2.getNombre() + " (" + jugador2.getClass().getSimpleName() + ")");

        Personaje atacante, defensor;
        int opcion = -1;

        // Bucle principal mientras ambos sigan vivos
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            
            // Turno del Jugador 1
            atacante = jugador1;
            defensor = jugador2;
            opcion = obtenerOpcion(atacante, defensor, scanner);
            turno(atacante, defensor, opcion);

            // Turno del Jugador 2 (solo si el J2 aun tiene vida)
            if (jugador2.estaVivo()) {
                 atacante = jugador2;
                 defensor = jugador1;
                 opcion = obtenerOpcion(atacante, defensor, scanner);
                 turno(atacante, defensor, opcion);
            }
        }

        // Determinar el ganador
        System.out.println("\n----------------------------------");
        if (jugador1.estaVivo()) {
            System.out.println("  FIN DE LA PELEA: " + jugador1.getNombre() + " ha ganado.  ");
        } else {
            System.out.println("  FIN DE LA PELEA: " + jugador2.getNombre() + " ha ganado.  ");
        }
        System.out.println("----------------------------------");
        scanner.close(); // Cierra el scanner al finalizar el juego
    }
    
    // Metodo para solicitar la accion al usuario
    private int obtenerOpcion(Personaje atacante, Personaje defensor, Scanner scanner) {
        int opcion = -1;
        System.out.println("\n--- Turno de " + atacante.getNombre() + " ---");
        System.out.println("Ataque MAX: " + atacante.ataqueMax());
        System.out.println("HP restantes de " + defensor.getNombre() + ": " + defensor.getPuntosDeVida());
        System.out.println("Elige acción:\n1. Ataque Básico (Daño 10-30)\n2. Ejecutar Combo (MAX/MIN Aleatorio)");
        
        try {
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            scanner.next(); // Limpia la entrada invalida
        }
        return opcion;
    }

    // Ejecuta la logica del turno segun la opcion elegida
    public void turno(Personaje atacante, Personaje defensor, int opcion) {
        
        if (opcion == 1) {
            atacante.atacar(defensor);
        } else if (opcion == 2) {
            String tipoCombo = (Math.random() < 0.5) ? "MAX" : "MIN";
            System.out.println("-> El sistema selecciona el Combo " + tipoCombo + " para " + atacante.getNombre() + "!");
            atacante.ejecutarCombo(defensor, tipoCombo); // Llama al metodo polimorfico
        } else {
            System.out.println("Opción no válida. Se realiza un ataque básico por defecto.");
            atacante.atacar(defensor);
        }

        System.out.println(defensor.getNombre() + " ahora tiene " + defensor.getPuntosDeVida() + " puntos de vida.");
    }
    
    /**
     * Metodo auxiliar para crear una instancia de Personaje basada en la opcion.
     * Se usa el nombre del luchador concatenado con la etiqueta de jugador (J1/J2).
     */
    private static Personaje crearPersonaje(int opcion, int numJugador, Scanner scanner) {
        String nombreBase;
        int opcionLuchador = opcion;
        
        switch (opcionLuchador) {
            case 1:
                nombreBase = "Mike 'The Iron' Tyson (J" + numJugador + ")";
                return new MikeTyson(nombreBase);
            case 2:
                nombreBase = "Khabib 'The Eagle' (J" + numJugador + ")";
                return new Khabib(nombreBase);
            case 3:
                nombreBase = "Bruce 'The Dragon' Lee (J" + numJugador + ")";
                return new BruceLee(nombreBase);
            default:
                // Opcion por defecto si la entrada fue invalida
                System.out.println("Opcion invalida. Asignando Mike Tyson por defecto.");
                return new MikeTyson("Mike 'The Iron' Tyson (J" + numJugador + ")");
        }
    }

    // Punto de entrada del programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int opcion1 = -1;
        int opcion2 = -1;
        
        // --- SELECCION JUGADOR 1 ---
        System.out.println("\n--- SELECCION DE PERSONAJE ---");
        System.out.println("JUGADOR 1, elige tu luchador:");
        System.out.println("1. Mike Tyson (Golpeador)");
        System.out.println("2. Khabib 'The Eagle' (Grappler)");
        System.out.println("3. Bruce Lee (Velocidad/Critico)");
        System.out.print("Introduce el numero de tu opcion: ");
        
        try {
            opcion1 = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Consumir entrada invalida
        }
        
        Personaje p1 = crearPersonaje(opcion1, 1, scanner); 
        
        // --- SELECCION JUGADOR 2 ---
        System.out.println("\n--- SELECCION DE PERSONAJE ---");
        System.out.println("JUGADOR 2, elige tu luchador:");
        System.out.println("1. Mike Tyson (Golpeador)");
        System.out.println("2. Khabib 'The Eagle' (Grappler)");
        System.out.println("3. Bruce Lee (Velocidad/Critico)");
        System.out.print("Introduce el numero de tu opcion: ");
        
        try {
            opcion2 = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Consumir entrada invalida
        }
        
        Personaje p2 = crearPersonaje(opcion2, 2, scanner);
        
        // Inicializacion y ejecucion del juego
        JuegoLucha juego = new JuegoLucha(p1, p2);
        // Pasa el Scanner a iniciarPelea para que continue con el flujo de turnos y lo cierre al final.
        juego.iniciarPelea(scanner);
    }
}