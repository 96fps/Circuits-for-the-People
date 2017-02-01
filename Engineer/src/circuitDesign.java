
public class circuitDesign {

	pos2d board_dim; //44 x 27

	public boolean[][] metal_layer;  
	public boolean[][] metal_H;  
	public boolean[][] metal_V;  
	
	public circuitDesign(){
		board_dim = new pos2d(44,27);
	}
	public circuitDesign(pos2d dim){
		board_dim = dim;
		metal_layer = new boolean[(int)dim.x  ][(int)dim.y  ];
		metal_H =     new boolean[(int)dim.x  ][(int)dim.y  ];
		metal_V =     new boolean[(int)dim.x  ][(int)dim.y  ];
		
		
		for(int i=0; i<(int)dim.x; i++){
			for(int j=0; j<(int)dim.y; j++){
				if(Math.random()>0.01) metal_layer[i][j]=true;
			
			}
		}
		
		for(int i=0; i<(int)dim.x; i++){
			for(int j=0; j<(int)dim.y-1; j++){
				if(metal_layer[i][j] && metal_layer[i][j+1])
					if(Math.random()>0.8)
						metal_V[i][j]=true;
			
			}
		}
		for(int i=0; i<(int)dim.x-1; i++){
			for(int j=0; j<(int)dim.y; j++){
				if(metal_layer[i][j] && metal_layer[i+1][j])
					if(Math.random()>0.15)
						metal_H[i][j]=true;
			
			}
		}
	}

	public void clean(){
		for(int i=0; i<(int)board_dim.x; i++){
			for(int j=0; j<(int)board_dim.y-1; j++){
				if(!metal_layer[i][j] || !metal_layer[i][j+1])
					metal_V[i][j]=false;
			
			}
		}
		for(int i=0; i<(int)board_dim.x-1; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				if(!metal_layer[i][j] || !metal_layer[i+1][j])
					metal_H[i][j]=false;
			
			}
		}
	}
	public void stdTemplate(){
		
		//clean edges
		for(int i=0; i<4; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				metal_layer[i][j]=false;
			}
		}
		for(int i=(int)board_dim.x-4; i<(int)board_dim.x; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				metal_layer[i][j]=false;
			}
		}
		//place pads
		//left
		for(int k=0;k<6;k++)
		 for(int i=0;i<3;i++)
		  for(int j=0;j<3;j++){
			  metal_layer[i+1][j+2+4*k]=true;
			  metal_V[i+1][j+2+4*k]=true;
			  metal_H[i][j+2+4*k]=true;
		}
		//right
		for(int k=0;k<6;k++)
			 for(int i=0;i<3;i++)
			  for(int j=0;j<3;j++){
				  metal_layer[(int)board_dim.x-4+i][j+2+4*k]=true;
				  metal_V[(int)board_dim.x-4+i][j+2+4*k]=true;
				  metal_H[(int)board_dim.x-4+i][j+2+4*k]=true;
			}
		//</pads>
		this.clean();
	}
}
