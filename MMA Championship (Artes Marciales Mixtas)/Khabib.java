import java.util.Random;

/**
 * Clase que representa a Khabib.
 */
public class Khabib extends Personaje {

    public Khabib(String nombre) {
        super(nombre);
    }
    
    public String ataqueMax() {
        return "Rear-Naked Choke (Daño 35-45)";
    }

    public String ataqueMin() {
        return "Derribo de Control (Daño 10-20)";
    }
    
    /**
     * Implementacion polimorfica: Dano de Khabib.
     */
    public void ejecutarCombo(Personaje oponente, String tipoCombo) {
        Random rand = new Random();
        int dano = 0;
        
        if (tipoCombo.equals("MAX")) {
            dano = rand.nextInt(11) + 35;
            // USO CORREGIDO: Usamos getNombre()
            System.out.println("   " + getNombre() + " realiza un COMBO MAX: " + ataqueMax() + " causando " + dano + " HP.");
        } else {
            dano = rand.nextInt(11) + 10;
            // USO CORREGIDO: Usamos getNombre()
            System.out.println("   " + getNombre() + " realiza un COMBO MIN: " + ataqueMin() + " causando " + dano + " HP.");
        }
        
        oponente.recibirDano(dano);
    }
}