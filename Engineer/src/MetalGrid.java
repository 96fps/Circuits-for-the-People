
public class MetalGrid {

	Pos2D board_dim; //44 x 27

	public boolean[][] layer;  
	public boolean[][] horiz;  
	public boolean[][] verti;  
	
	public MetalGrid(){
		board_dim = new Pos2D(44,27);
	}
	public MetalGrid(Pos2D dim){
		board_dim = dim;
		layer = new boolean[(int)dim.x  ][(int)dim.y  ];
		horiz =     new boolean[(int)dim.x  ][(int)dim.y  ];
		verti =     new boolean[(int)dim.x  ][(int)dim.y  ];
		
		
		for(int i=0; i<(int)dim.x; i++){
			for(int j=0; j<(int)dim.y; j++){
				if(Math.random()>0.01) layer[i][j]=true;
			
			}
		}
		
		for(int i=0; i<(int)dim.x; i++){
			for(int j=0; j<(int)dim.y-1; j++){
				if(layer[i][j] && layer[i][j+1])
					if(Math.random()>0.8)
						verti[i][j]=true;
			
			}
		}
		for(int i=0; i<(int)dim.x-1; i++){
			for(int j=0; j<(int)dim.y; j++){
				if(layer[i][j] && layer[i+1][j])
					if(Math.random()>0.15)
						horiz[i][j]=true;
			
			}
		}
	}

	public void clean(){
		for(int i=0; i<(int)board_dim.x; i++){
			for(int j=0; j<(int)board_dim.y-1; j++){
				if(!layer[i][j] || !layer[i][j+1])
					verti[i][j]=false;
			
			}
		}
		for(int i=0; i<(int)board_dim.x-1; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				if(!layer[i][j] || !layer[i+1][j])
					horiz[i][j]=false;
			
			}
		}
	}
	public void stdTemplate(){
		
		//clean edges
		for(int i=0; i<4; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				layer[i][j]=false;
			}
		}
		for(int i=(int)board_dim.x-4; i<(int)board_dim.x; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				layer[i][j]=false;
			}
		}
		//place pads
		//left
		for(int k=0;k<6;k++)
		 for(int i=0;i<3;i++)
		  for(int j=0;j<3;j++){
			  layer[i+1][j+2+4*k]=true;
			  verti[i+1][j+2+4*k]=true;
			  horiz[i][j+2+4*k]=true;
		}
		//right
		for(int k=0;k<6;k++)
			 for(int i=0;i<3;i++)
			  for(int j=0;j<3;j++){
				  layer[(int)board_dim.x-4+i][j+2+4*k]=true;
				  verti[(int)board_dim.x-4+i][j+2+4*k]=true;
				  horiz[(int)board_dim.x-4+i][j+2+4*k]=true;
			}
		//</pads>
		this.clean();
	}
}
