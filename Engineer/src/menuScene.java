import java.awt.Color;
import java.awt.Graphics;


public class menuScene extends Scene{
	double timestep=0;
	public menuScene(){
		//build
	}
	public void dologicTick(Controlset c, double itter){
		//do logic
		timestep+=itter;
	}
	public void render(Graphics g, pos2d gameRes){
		
		g.setColor(new Color(128,128,128));
		g.fillRect(0, 0, (int)gameRes.x, (int)gameRes.y);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(32, 32, (int)gameRes.x-64, 32);
		g.setColor(Color.white);
		g.drawString("Circuits for the People",42,52);
		
		
	}
}
