/**
 * Interfaz que define el contrato basico de un Luchador.
 */
public interface Luchador {
    
    String getNombre();
    String ataqueMax();
    String ataqueMin();

    void ejecutarCombo(Personaje oponente, String tipoCombo);
}