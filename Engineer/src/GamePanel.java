import java.awt.Color;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.imageio.*;


import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements MouseListener, KeyListener, ActionListener {
	static public boolean running=true;
	
	static public boolean controls;
	int wait=1000/30;
	double itter=(double)wait/1000;
	Timer clock= new Timer(wait, this);
	double time=0;
	Controlset c= new Controlset();
	int sceneID=0;
	
	int tile_size=16;

	pos2d board_dim=new pos2d(44,27);
	
	pos2d gameRes=new pos2d((board_dim.x+1)*tile_size,(board_dim.y+1)*tile_size);
	
	IntroScene intro= new IntroScene();
	menuScene menu= new menuScene();
	circuitEditorScene editor= new circuitEditorScene(board_dim);
	
	@Override
	public void actionPerformed(ActionEvent e) {//new game "tick"
		time+=itter;
		
		if(sceneID==0){
			intro.dologicTick(c, itter);
			
			if(time>(double)intro.starttime+0.5 || intro.skip){sceneID=1;}
			}
		if(sceneID==1){
			menu.dologicTick(c, itter);

			if(time>(double)intro.starttime+2 || intro.skip){sceneID=2;}
			}
		if(sceneID==2){
			editor.dologicTick(c, itter);
			}
		if(running){
		//scenelogic
		
	}	
		repaint();
		//scenerepaint
		
	}	
	
	GamePanel()
	{
		clock.start();
		addKeyListener(this);

		setFocusable(true);
		
		addMouseListener(this);
		
		setPreferredSize(new Dimension((int)gameRes.x, (int)gameRes.y));
		
		repaint();
			
		
	}
	

	public void paintComponent(Graphics g)
	{	//System.out.println("valid");
		if (sceneID==0){
			intro.render(g,gameRes);
		}
		else if (sceneID==1){
			menu.render(g,gameRes);
		}
		else if (sceneID==2){
			editor.render(g,gameRes,tile_size);
		}
		
		}
	
	public void mouseClicked(MouseEvent e)
	{	
		//gui?
	}

	@Override
	
	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			controls=true;
		}
	/*	if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			controls=!control;
		}*/
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			controls=false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}