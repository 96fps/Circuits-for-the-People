import java.awt.Color;
import java.awt.Graphics;

public class circuitEditorScene extends Scene{
    double timestep=0;
    pos2d dim;
    metalGrid circ;
    siliconGrid silly;
    
    boolean[][][] metalpower;
    boolean[][][] sillypower;
    boolean stable_state;
    
    boolean colorMetal=true;

    pos3d[][] colors;
    
    double timelast=0;
    int t_clone=0;
    public circuitEditorScene(pos2d board_dim){
        dim = board_dim;
        circ= new metalGrid(dim);
        silly= new siliconGrid(dim);
        metalpower = new boolean[2][(int)dim.x][(int)dim.y];
        sillypower = new boolean[2][(int)dim.x][(int)dim.y];
        metalpower[1][2][3]=true;
        stable_state=false;
        circ.stdTemplate();
        silly.stdTemplate();
        colors = new pos3d[(int)dim.x][(int)dim.y];
        colors[2][3] = new pos3d(Math.random(),Math.random(),Math.random());
    }
    public void dologicTick(Controlset c, double itter){

        //does internal logic once a frame
        
        timestep+=itter;

        
        for(int i = 0; i<1; i++)
        {
            spread1tile();
        }
        
        if(stable_state && (true && timestep>timelast+1)){
            circ= new metalGrid(dim);
            timelast = timestep;
            metalpower = new boolean[2][(int)dim.x][(int)dim.y];
            metalpower[1][2][3]=true;
            circ.stdTemplate();
            silly= new siliconGrid(dim);
            silly.stdTemplate();
            colors = new pos3d[(int)dim.x][(int)dim.y];
            colors[2][3] = new pos3d(Math.random(),Math.random(),Math.random());
        }
    }
    
    public void spread1tile(){
        metalpower[0]=metalpower[1];
        stable_state = true;
        metalpower[1] = new boolean[(int)dim.x][(int)dim.y];
        for(int i=0; i<(int)dim.x; i++){
            for(int j=0; j<(int)dim.y; j++){
                if(metalpower[0][i][j] && circ.layer[i][j]){
                    metalpower[1][i][j]=true;
                    
                    //propagate right
                    if( metalpower[0][i][j] 
                        && circ.horiz[i][j])
                    {
                        metalpower[1][(i+1)%(int)dim.x][j]=true;
                        
                        if(!metalpower[0][(i+1)%(int)dim.x][j])
                        {   stable_state=false;
                            colors[(i+1)%(int)dim.x][j] =
                              pos3d.add(pos3d.mult(colors[i][j],0.9),
                                       pos3d.mult(new pos3d(Math.random(),
                                                           Math.random(),
                                                          Math.random() ),0.1));
                        }
                    }
                    
                    //propagate left
                    if( metalpower[0][i][j] 
                        && circ.horiz[(i+(int)dim.x-1)%(int)dim.x][j] )
                    {
                        metalpower[1][(i-1)%(int)dim.x][j]=true;
                        
                        if(!metalpower[0][(i-1)%(int)dim.x][j])
                        {   stable_state=false;
                            colors[(i-1)%(int)dim.x][j] = 
                              pos3d.add(pos3d.mult(colors[i][j],0.9),
                                       pos3d.mult(new pos3d(Math.random(),
                                                           Math.random(),
                                                          Math.random() ),0.1));
                        }
                    }
                    //propagate down
                    if( metalpower[0][i][j] 
                        && circ.verti[i][j] )
                    {
                        metalpower[1][i][(j+1)%(int)dim.y]=true;
                    
                        if(!metalpower[0][i][(j+1)%(int)dim.y])
                        {   stable_state=false;
                            colors[i][(j+1)%(int)dim.y] = 
                              pos3d.add(pos3d.mult(colors[i][j],0.9), 
                                       pos3d.mult(new pos3d(Math.random(),
                                                           Math.random(),
                                                          Math.random()),0.1));
                        }
                    }
                    //propagate up
                    if( metalpower[0][i][j] 
                        && circ.verti[i][((int)dim.y+j-1)%(int)dim.y] )
                    {
                        metalpower[1][i][(j-1)%(int)dim.y]=true;
                        
                        if(!metalpower[0][i][(j-1)%(int)dim.y])
                        {   stable_state=false;
                            colors[i][(j-1)%(int)dim.y] = 
                              pos3d.add(pos3d.mult(colors[i][j],0.9), 
                                       pos3d.mult(new pos3d(Math.random(),
                                                           Math.random(),
                                                          Math.random()),0.1));
                        }
                    }
                }
            }
        }
    }
    
    public void render(Graphics g, pos2d gameRes){
        this.render(g, gameRes,10);
        // the last parameter decides pixel size of a grid tile
    }
    public void render(Graphics g, pos2d gameRes,int t){

        g.setColor(new Color(128,128,128));
        g.fillRect(0, 0, (int)gameRes.x, (int)gameRes.y);
    
        drawGrid(g, gameRes, t);
        drawMetal(g, gameRes, t, circ);
        drawSilicon(g, gameRes, t, silly);
        
    }
    public void drawGrid(Graphics g, pos2d gameRes,int t){
        g.setColor(new Color(255,255,255,64));
        g.fillRect(4*t+t/2,
                       t/2, 
                   (int)(dim.x-8)*t, 
                   (int) dim.y   *t);
    
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
    
    public void drawMetal(Graphics g, pos2d gameRes,int t, metalGrid circ){
        g.setColor(new Color(0,0,0,127));
        for(int i=0; i<(int)dim.x; i++){
            for(int j=0; j<(int)dim.y; j++){
                
                if(circ.layer[i][j]){
                    if(metalpower[1][i][j] && colorMetal)
                         g.setColor(pos3d.toColor(colors[i][j]).brighter());
                    else g.setColor(new Color(0,0,0,96));
                
                    g.fillRect( i*t  +t/2 + 2, j*t+t/2  +2, t-3,t-3);
                }
                if(circ.horiz[i][j]){
                    if(metalpower[1][i][j] && metalpower[1][i+1][j] && colorMetal)
                         g.setColor( pos3d.toColor(
                                     pos3d.midpoint(colors[i][j],
                                                    colors[i+1][j])).brighter());
                    else g.setColor(new Color(0,0,0,96));
                    
                    g.fillRect( i*t  +t/2 + t-1, j*t+t/2  +2, 3,t-3);
                }
                if(circ.verti[i][j]){
                    if(metalpower[1][i][j] && metalpower[1][i][j+1] && colorMetal)
                         g.setColor( pos3d.toColor(
                                     pos3d.midpoint(colors[i][j],
                                                    colors[i][j+1])).brighter());
                    else g.setColor(new Color(0,0,0,96));
                    
                    g.fillRect( i*t  +t/2 +2, j*t+t/2  +t-1, t-3, 3);
                }
            }   
        }
        
        g.setColor(new Color(255,255,255,128));
        for(int i=0; i<(int)dim.x; i++){
            for(int j=0; j<(int)dim.y; j++){
                if(circ.layer[i][j]){
                    if(metalpower[1][i][j] && colorMetal)
                         g.setColor(pos3d.toColor(colors[i][j]));
                    else g.setColor(new Color(255,255,255,128));
                    
                    g.fillRect( i*t  +t/2 + 3, j*t+t/2  +3, t-5,t-5);
                }
                if(circ.horiz[i][j]){
                    if(metalpower[1][i][j] && metalpower[1][i+1][j] && colorMetal)
                         g.setColor( pos3d.toColor( 
                                     pos3d.midpoint(colors[i][j],
                                                    colors[i+1][j])));
                    else g.setColor(new Color(255,255,255,128));
                    
                    g.fillRect( i*t  + t/2 +t-2, j*t+t/2  +3, 5,t-5);
                }
                if(circ.verti[i][j]){
                    if(metalpower[1][i][j] && metalpower[1][i][j+1] && colorMetal)
                         g.setColor( pos3d.toColor(
                                     pos3d.midpoint(colors[i][j],
                                                    colors[i][j+1])));
                    else g.setColor(new Color(255,255,255,128));
                    
                    g.fillRect( i*t  +t/2 +3, j*t  +t/2 +t-2, t-5, 5);
                }
            }   
        }
        // draw propagation front
        g.setColor(new Color(0,0,0,128));
        
        for(int i=0; i<(int)dim.x; i++)
          for(int j=0; j<(int)dim.y; j++){
            if( !metalpower[0][i][j] && metalpower[1][i][j])
            {   
                g.setColor(pos3d.toColor(pos3d.midpoint(colors[i][j],new pos3d(0,0,0))));
                g.fillRect( i*t  +t/2 +1, j*t+t/2 +1, t-1,t-1);
            }   
        }
    }
    public void drawSilicon(Graphics g, pos2d gameRes,int t, siliconGrid silicon){
        g.setColor(new Color(0,0,0,127));
        
        for(int i=0; i<(int)dim.x; i++){
            for(int j=0; j<(int)dim.y; j++){
                if(silicon.layer[i][j] != null){
                    if(silicon.layer[i][j] == siliconGrid.siliconType.red)
                         g.setColor(Color.red.darker().darker());
                    else g.setColor(Color.yellow.darker().darker());
                    g.fillRect( i*t  +t/2 + 2, j*t+t/2  +2, t-3,t-3);
                }
                if(silicon.horiz[i][j]!= null){
                    if(silicon.horiz[i][j] == siliconGrid.siliconType.red)
                         g.setColor(Color.red.darker().darker());
                    else g.setColor(Color.yellow.darker().darker());
                    
                    g.fillRect( i*t  +t/2 + t-1, j*t+t/2  +2, 3,t-3);
                }
                if(silicon.verti[i][j]!= null){
                    if(silicon.verti[i][j] == siliconGrid.siliconType.red)
                         g.setColor(Color.red.darker().darker());
                    else g.setColor(Color.yellow.darker().darker());
                    
                    g.fillRect( i*t  +t/2 +2, j*t+t/2  +t-1, t-3, 3);
                }
            }   
        }
        
        g.setColor(new Color(255,255,255,128));
        for(int i=0; i<(int)dim.x; i++){
            for(int j=0; j<(int)dim.y; j++){
                
                
                if(silicon.layer[i][j]!=null){
                    if(silicon.layer[i][j] == siliconGrid.siliconType.red)
                         g.setColor(Color.red);
                    else g.setColor(Color.yellow);
                    
                    g.fillRect( i*t  +t/2 + 3, j*t+t/2  +3, t-5,t-5);
                }
                if(silicon.horiz[i][j]!=null)
                {   
                    if(silicon.horiz[i][j] == siliconGrid.siliconType.red)
                         g.setColor(Color.red);
                    else g.setColor(Color.yellow);
                    
                    g.fillRect( i*t  + t/2 +t-2, j*t+t/2  +3, 5,t-5);
                }
                if(silicon.verti[i][j]!=null)
                {
                    if(silicon.verti[i][j] == siliconGrid.siliconType.red)
                         g.setColor(Color.red);
                    else 
                        g.setColor(Color.yellow);
                    
                    g.fillRect( i*t  +t/2 +3, j*t  +t/2 +t-2, t-5, 5);
                }   
            }
        }
        // draw propagation front
        g.setColor(new Color(0,0,0,128));
        
        for(int i=0; i<(int)dim.x; i++)
          for(int j=0; j<(int)dim.y; j++){
            if( !sillypower[0][i][j] && sillypower[1][i][j])
            {   
//              g.fillRect( i*t  +t/2 +1, j*t+t/2 +1, t-1,t-1);
            }   
        }
    }
    
}
