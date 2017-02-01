import java.awt.Color;
import java.awt.Graphics;


public class IntroScene extends Scene{
	double timestep=0;
	double blitz=0;
	boolean skip=true;
	int starttime=3;//10
	public IntroScene(){
		//build
	}
	public void dologicTick(Controlset c, double itter){
		//do logic
		timestep+=itter;
		if(false) skip=true;
	}
	public void render(Graphics g, pos2d gameRes){
		g.setColor(new Color(0,0,0));
		blitz=Math.pow(0.1,timestep%1);
		if(timestep<starttime)
		g.setColor(new Color((int)(32*blitz),(int)(32*blitz),(int)(32*blitz)));
		else g.setColor(new Color(64,0,0));
		
		g.fillRect(0, 0, (int)gameRes.x, (int)gameRes.y);
		g.setColor(Color.white);
		g.drawLine((int)( gameRes.y*(  Math.sin(timestep*2*Math.PI/60)) +gameRes.x/2), 
		           (int)( gameRes.y*( -Math.cos(timestep*2*Math.PI/60)) +gameRes.y/2), 
		           (int)(gameRes.x/2), 
		           (int)(gameRes.y/2)
					);
		g.drawLine((int)( gameRes.y*(  Math.sin(timestep*2*Math.PI)) +gameRes.x/2), 
		           (int)( gameRes.y*( -Math.cos(timestep*2*Math.PI)) +gameRes.y/2), 
		           (int)(gameRes.x/2), 
		           (int)(gameRes.y/2)
					);
		
		g.drawString((starttime-(int)timestep)+" seconds", (int) gameRes.x/2,(int) gameRes.y/2);
		/*
		g.drawLine((int)( gameRes.y/4*( Math.sin(timestep)+Math.cos(timestep)) +gameRes.x/2), 
		           (int)( gameRes.y/4*( Math.sin(timestep)-Math.cos(timestep)) +gameRes.y/2), 
		           (int)( gameRes.y/4*(-Math.sin(timestep)+Math.cos(timestep)) +gameRes.x/2), 
		           (int)( gameRes.y/4*(+Math.sin(timestep)+Math.cos(timestep)) +gameRes.y/2)
					);*/
		
	}
}
