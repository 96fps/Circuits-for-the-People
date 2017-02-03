import java.awt.Color;
import java.awt.Graphics;


public class circuitEditorScene extends Scene{
	double timestep=0;
	pos2d dim;
	circuitDesign circ;
	
	boolean[][][] simstate;
	boolean stable_state;

	pos3d[][] colors;
	
	double timelast=0;
	int t_clone=0;
	public circuitEditorScene(pos2d board_dim){
		dim = board_dim;
		circ= new circuitDesign(dim);
		simstate = new boolean[2][(int)dim.x][(int)dim.y];
		simstate[1][2][3]=true;
		stable_state=false;
		circ.stdTemplate();
		colors = new pos3d[(int)dim.x][(int)dim.y];
		colors[2][3] = new pos3d(Math.random(),Math.random(),Math.random());
	}
	public void dologicTick(Controlset c, double itter){
		//do logic
		timestep+=itter;
		
		
		simstate[0]=simstate[1];
		stable_state = true;
		simstate[1] = new boolean[(int)dim.x][(int)dim.y];
		for(int i=0; i<(int)dim.x; i++){
			for(int j=0; j<(int)dim.y; j++){
				if(simstate[0][i][j] && circ.metal_layer[i][j]){
					simstate[1][i][j]=true;
					
					//propagate right
					if(simstate[0][i][j] && circ.metal_H[i][j]){
						simstate[1][(i+1)%(int)dim.x][j]=true;
						
						if(!simstate[0][(i+1)%(int)dim.x][j])
						{	stable_state=false;
							colors[(i+1)%(int)dim.x][j]=pos3d.add(pos3d.mult(colors[i][j],0.9), pos3d.mult(new pos3d(Math.random(),Math.random(),Math.random()),0.1));
						}
					}
					
					//propagate left
					if(simstate[0][i][j] && circ.metal_H[(i+(int)dim.x-1)%(int)dim.x][j]){
						simstate[1][(i-1)%(int)dim.x][j]=true;
						
						if(!simstate[0][(i-1)%(int)dim.x][j])
						{	stable_state=false;
							colors[(i-1)%(int)dim.x][j]=pos3d.add(pos3d.mult(colors[i][j],0.9), pos3d.mult(new pos3d(Math.random(),Math.random(),Math.random()),0.1));
						}
					}
					//propagate down
					if(simstate[0][i][j] && circ.metal_V[i][j]){
						simstate[1][i][(j+1)%(int)dim.y]=true;
					
						if(!simstate[0][i][(j+1)%(int)dim.y])
						{	stable_state=false;
							colors[i][(j+1)%(int)dim.y]=pos3d.add(pos3d.mult(colors[i][j],0.9), pos3d.mult(new pos3d(Math.random(),Math.random(),Math.random()),0.1));
						}
					}
					//propagate up
					if(simstate[0][i][j] && circ.metal_V[i][((int)dim.y+j-1)%(int)dim.y]){
						simstate[1][i][(j-1)%(int)dim.y]=true;
						
						if(!simstate[0][i][(j-1)%(int)dim.y])
						{	stable_state=false;
							colors[i][(j-1)%(int)dim.y]=pos3d.add(pos3d.mult(colors[i][j],0.9), pos3d.mult(new pos3d(Math.random(),Math.random(),Math.random()),0.1));
						}
					}
				}
			
			}
		}
		
		if(stable_state && (true && timestep>timelast+1)){
			circ= new circuitDesign(dim);
			timelast = timestep;
			simstate = new boolean[2][(int)dim.x][(int)dim.y];
			simstate[1][2][3]=true;
			circ.stdTemplate();
		}
		
		
	}
	public void render(Graphics g, pos2d gameRes){
		this.render(g, gameRes,10);
	}
	public void render(Graphics g, pos2d gameRes,int t){
//		t_clone = t_clone+1;
//		if(t_clone>Math.PI*20) t_clone=0;
//		if(t_clone<Math.PI*5)
//			t= (int)(((1-Math.sin((double)(t_clone)*0.1))*3+1)*t);
		g.setColor(new Color(128,128,128));
		g.fillRect(0, 0, (int)gameRes.x, (int)gameRes.y);
		
		g.setColor(new Color(255,255,255,64));
		g.fillRect(            4 *t+t/2,            0 +t/2,
				   ((int)dim.x-8)*t, (int)dim.y*t);
		
		drawGrid(g, gameRes, t);
		
		drawMetal(g, gameRes, t, circ);
		
		//powerstate
		for(int i=0; i<(int)dim.x; i++)
		 for(int j=0; j<(int)dim.y; j++){
			 if(simstate[0][i][j])
			 {	 g.setColor(new Color(0,0,0,32));
			 	g.setColor(pos3d.toColor(colors[i][j]));
			 	g.fillRect( i*t  +t/2 + 1, j*t+t/2  +1, t-1,t-1);
			}	
			else  if(simstate[1][i][j])
			{	 g.setColor(new Color(0,0,0,64));
			 	g.fillRect( i*t  +t/2 + 1, j*t+t/2  +1, t-1,t-1);
			}
				
				
		}
		//</power>
		
	}
	public void drawGrid(Graphics g, pos2d gameRes,int t){
		g.setColor(new Color(0,0,0,64));
		for(int i=0; i<=(int)dim.x; i++){
			g.drawLine(i*t+t/2,            0 +t/2,
					   i*t+t/2, (int)dim.y*t +t/2);
		}
		for(int j=0; j<=(int)dim.y; j++){
			g.drawLine(         0  +t/2, j*t+t/2,
					   (int)dim.x*t+t/2, j*t +t/2);
		
		}
	}
	public void drawMetal(Graphics g, pos2d gameRes,int t, circuitDesign circ){
		g.setColor(new Color(0,0,0,127));
		for(int i=0; i<(int)dim.x; i++){
			for(int j=0; j<(int)dim.y; j++){
				if(circ.metal_layer[i][j])
					g.fillRect( i*t  +t/2 + 2, j*t+t/2  +2, t-3,t-3);
				
				if(circ.metal_H[i][j])
					g.fillRect( i*t  +t/2 + t-1, j*t+t/2  +2, 3,t-3);
				
				if(circ.metal_V[i][j])
					g.fillRect( i*t  +t/2 +2, j*t+t/2  +t-1, t-3, 3);
			}
		}
		g.setColor(new Color(255,255,255,128));
		for(int i=0; i<(int)dim.x; i++){
			for(int j=0; j<(int)dim.y; j++){
				if(circ.metal_layer[i][j])
					g.fillRect( i*t  +t/2 + 3, j*t+t/2  +3, t-5,t-5);
				
				if(circ.metal_H[i][j])
					g.fillRect( i*t  + t/2 +t-2, j*t+t/2  +3, 5,t-5);
				
				if(circ.metal_V[i][j])
					g.fillRect( i*t  +t/2 +3, j*t  +t/2 +t-2, t-5, 5);
			}
		}
		
	}
	
}
