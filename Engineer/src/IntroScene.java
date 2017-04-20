import java.awt.Color;
import java.awt.Graphics;


public class IntroScene implements Scene {
    private double timestep = 0;
    private double blitz = 0;
    boolean skip = true;
    int starttime = 3;//10

    public IntroScene() {
        //build
    }

    public void doLogicTick(double itter) {
        //do logic
        timestep += itter;
    }

    public void render(Graphics g, Pos2D gameRes) {
        g.setColor(new Color(0, 0, 0));
        blitz = Math.pow(0.1, timestep % 1);
        if (timestep < starttime)
            g.setColor(new Color((int) (32 * blitz), (int) (32 * blitz), (int) (32 * blitz)));
        else g.setColor(new Color(64, 0, 0));

        g.fillRect(0, 0, gameRes.getX(), gameRes.getY());
        g.setColor(Color.white);
        g.drawLine((int) (gameRes.getY() * (Math.sin(timestep * 2 * Math.PI / 60)) + gameRes.getX() / 2),
                (int) (gameRes.getY() * (-Math.cos(timestep * 2 * Math.PI / 60)) + gameRes.getY() / 2),
                gameRes.getX() / 2,
                gameRes.getY() / 2
        );
        g.drawLine((int) (gameRes.getY() * (Math.sin(timestep * 2 * Math.PI)) + gameRes.getX() / 2),
                (int) (gameRes.getY() * (-Math.cos(timestep * 2 * Math.PI)) + gameRes.getY() / 2),
                gameRes.getX() / 2,
                gameRes.getY() / 2
        );

        g.drawString((starttime - (int) timestep) + " seconds", gameRes.getX() / 2, gameRes.getY() / 2);
        /*
		g.drawLine((int)( gameRes.getY()/4*( Math.sin(timestep)+Math.cos(timestep)) +gameRes.getX()/2), 
		           (int)( gameRes.getY()/4*( Math.sin(timestep)-Math.cos(timestep)) +gameRes.getY()/2), 
		           (int)( gameRes.getY()/4*(-Math.sin(timestep)+Math.cos(timestep)) +gameRes.getX()/2), 
		           (int)( gameRes.getY()/4*(+Math.sin(timestep)+Math.cos(timestep)) +gameRes.getY()/2)
					);*/

    }
}
