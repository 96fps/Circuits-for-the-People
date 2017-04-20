import java.awt.Color;
import java.awt.Graphics;


public class MenuScene extends Scene {
    private double timestep = 0;

    public MenuScene() {
        //build
    }

    public void doLogicTick(ControlSet c, double itter) {
        //do logic
        timestep += itter;
    }

    public void render(Graphics g, Pos2D gameRes) {

        g.setColor(new Color(128, 128, 128));
        g.fillRect(0, 0, (int) gameRes.getX(), (int) gameRes.getY());
        g.setColor(Color.DARK_GRAY);
        g.fillRect(32, 32, (int) gameRes.getX() - 64, 32);
        g.setColor(Color.white);
        g.drawString("Circuits for the People", 42, 52);


    }
}
