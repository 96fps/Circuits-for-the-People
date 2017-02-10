
public class siliconGrid {

	pos2d board_dim; //44 x 27

	public enum siliconType { red, yellow }
	
	public siliconType[][] layer;  
	public siliconType[][] horiz;  
	public siliconType[][] verti;  
	
	public siliconGrid(){
		board_dim = new pos2d(44,27);
	}
	public siliconGrid(pos2d dim){
		board_dim = dim;
		layer = new siliconType[(int)dim.x  ][(int)dim.y  ];
		horiz = new siliconType[(int)dim.x  ][(int)dim.y  ];
		verti = new siliconType[(int)dim.x  ][(int)dim.y  ];
		
		
		for(int i=0; i<(int)dim.x; i++){
			for(int j=(int)dim.y/2; j<(int)dim.y; j++){
				if(Math.random()>0.6) layer[i][j]=siliconType.red;
				else if(Math.random()>0.5) layer[i][j]=siliconType.yellow;
			
			}
		}
		
		for(int i=0; i<(int)dim.x; i++){
			for(int j=0; j<(int)dim.y-1; j++){
				if(layer[i][j]!=null && layer[i][j+1]!=null){
					if(layer[i][j].equals(siliconType.yellow) && layer[i][j+1].equals(siliconType.yellow))
					{	if(Math.random()>0.2)
							verti[i][j]=siliconType.yellow;
					}
					else if(layer[i][j].equals(siliconType.red) && layer[i][j+1].equals(siliconType.red))
					{	if(Math.random()>0.2)
							verti[i][j]=siliconType.red;
					}
//					else{
//							if(Math.random()>0.9)
//							{
//								if(Math.random()>.5)
//									verti[i][j]=siliconType.yellow;
//								else 
//									verti[i][j]=siliconType.red;
//							}
//					}
				}
			}
		}
		for(int i=0; i<(int)dim.x-1; i++){
			for(int j=0; j<(int)dim.y; j++){
				if(layer[i][j]!=null && layer[i+1][j]!=null){
					if(layer[i][j].equals(siliconType.yellow) && layer[i+1][j].equals(siliconType.yellow))
					{	if(Math.random()>0.2)
							horiz[i][j]=siliconType.yellow;
					}
					else if(layer[i][j].equals(siliconType.red) && layer[i+1][j].equals(siliconType.red))
					{
						if(Math.random()>0.2)
							horiz[i][j]=siliconType.red;
					}
//					else{
//						if(Math.random()>0.9){
//							if(Math.random()>.5)
//								horiz[i][j]=siliconType.yellow;
//							else
//								horiz[i][j]=siliconType.red;
//						}
//					}
				}
			}
		}
	}

	public void clean(){
		for(int i=0; i<(int)board_dim.x; i++){
			for(int j=0; j<(int)board_dim.y-1; j++){
				if( layer[i][j]==null || layer[i][j+1]==null)
					verti[i][j]=null;
			}
		}
		for(int i=0; i<(int)board_dim.x-1; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				if( layer[i][j]==null || layer[i+1][j]==null)
					horiz[i][j]=null;
			}
		}
	}
	public void stdTemplate(){
		
		//clean edges
		for(int i=0; i<4; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				layer[i][j]=null;
			}
		}
		for(int i=(int)board_dim.x-4; i<(int)board_dim.x; i++){
			for(int j=0; j<(int)board_dim.y; j++){
				layer[i][j]=null;
			}
		}
		this.clean();
	}
}
