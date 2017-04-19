import javax.swing.JFrame;
import javax.swing.JPanel;


public class CircuitSim extends JFrame {

    /**
     * @param args
     */


    public static void main(String[] args) {
        CircuitSim window = new CircuitSim();
        JPanel p = new JPanel();
        p.add(new GamePanel());  //  add a class that extends JPanel
        window.setTitle("Circuits for the People");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(p);


        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}