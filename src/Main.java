
import javax.swing.JOptionPane;

/**
 *
 * @author Νίκος
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Emfanisi minimatos sston xristi gia tin kataxorisi tou onomatos tou
        String name = (String) JOptionPane.showInputDialog(null, "Δώστε το όνομά σας", "Όνομα Παίχτη", JOptionPane.PLAIN_MESSAGE);
        //kaloume tin klasi Panel gia tin dimiourgia twn grafikwn tis efarmogis 
        Panel panel = new Panel(name);
    }
    
}
