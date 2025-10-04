import java.util.Random;

/**
 * Clase base abstracta que define las caracteristicas y metodos comunes
 * de todos los combatientes. Implementa la interfaz Luchador.
 */
public abstract class Personaje implements Luchador {
 // Hacemos los campos privados para una correcta encapsulacion.
 private String nombre; 
 private int puntosDeVida;
 private final int MAX_DANO = 30;
 private final int MIN_DANO = 10;
 
 // Constructor
 public Personaje(String nombre) {
    this.nombre = nombre;
    this.puntosDeVida = 100;
 }
 
 /**
  * Implementa el ataque basico con dano aleatorio (10-30 HP).
  */
 public void atacar(Personaje oponente) {
    Random rand = new Random();
    int dano = rand.nextInt((MAX_DANO- MIN_DANO) + 1) + MIN_DANO;
    oponente.recibirDano(dano);
    // USO CORREGIDO: Usamos getNombre()
    System.out.println("   " + getNombre() + " ataca a " + oponente.getNombre() + " causando " + dano + " puntos de dano.");
 }
 
 public void recibirDano(int dano) {
    this.puntosDeVida-= dano;
    if (this.puntosDeVida < 0) {
        this.puntosDeVida = 0;
    }
 }
 
 public boolean estaVivo() {
    return this.puntosDeVida > 0;
 }
 
 // Metodos abstractos
 public abstract String ataqueMax();
 public abstract String ataqueMin();
 public abstract void ejecutarCombo(Personaje oponente, String tipoCombo);

 // Getter publico para acceder al nombre
 public String getNombre() {
    return this.nombre;
 }
 
 public int getPuntosDeVida() {
    return this.puntosDeVida;
 }
}