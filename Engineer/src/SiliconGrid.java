
public class SiliconGrid {

    private Pos2D board_dim; //44 x 27

    public enum siliconType {red, yellow}

    public siliconType[][] layer;
    public siliconType[][] horiz;
    public siliconType[][] verti;

    public SiliconGrid() {
        board_dim = new Pos2D(44, 27);
    }

    public SiliconGrid(Pos2D dim) {
        board_dim = dim;
        layer = new siliconType[dim.getX()][dim.getY()];
        horiz = new siliconType[dim.getX()][dim.getY()];
        verti = new siliconType[dim.getX()][dim.getY()];


        for (int i = 0; i < dim.getX(); i++) {
            for (int j = dim.getY() / 2; j < dim.getY(); j++) {
                if (Math.random() > 0.6) layer[i][j] = siliconType.red;
                else if (Math.random() > 0.5) layer[i][j] = siliconType.yellow;

            }
        }

        for (int i = 0; i < dim.getX(); i++) {
            for (int j = 0; j < dim.getY() - 1; j++) {
                if (layer[i][j] != null && layer[i][j + 1] != null) {
                    if (layer[i][j].equals(siliconType.yellow) && layer[i][j + 1].equals(siliconType.yellow)) {
                        if (Math.random() > 0.2)
                            verti[i][j] = siliconType.yellow;
                    } else if (layer[i][j].equals(siliconType.red) && layer[i][j + 1].equals(siliconType.red)) {
                        if (Math.random() > 0.2)
                            verti[i][j] = siliconType.red;
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
        for (int i = 0; i < dim.getX() - 1; i++) {
            for (int j = 0; j < dim.getY(); j++) {
                if (layer[i][j] != null && layer[i + 1][j] != null) {
                    if (layer[i][j].equals(siliconType.yellow) && layer[i + 1][j].equals(siliconType.yellow)) {
                        if (Math.random() > 0.2)
                            horiz[i][j] = siliconType.yellow;
                    } else if (layer[i][j].equals(siliconType.red) && layer[i + 1][j].equals(siliconType.red)) {
                        if (Math.random() > 0.2)
                            horiz[i][j] = siliconType.red;
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

    public void clean() {
        for (int i = 0; i < board_dim.getX(); i++) {
            for (int j = 0; j < board_dim.getY() - 1; j++) {
                if (layer[i][j] == null || layer[i][j + 1] == null)
                    verti[i][j] = null;
            }
        }
        for (int i = 0; i < board_dim.getX() - 1; i++) {
            for (int j = 0; j < board_dim.getY(); j++) {
                if (layer[i][j] == null || layer[i + 1][j] == null)
                    horiz[i][j] = null;
            }
        }
    }

    public void stdTemplate() {

        //clean edges
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < board_dim.getY(); j++) {
                layer[i][j] = null;
            }
        }
        for (int i = board_dim.getX() - 4; i < board_dim.getX(); i++) {
            for (int j = 0; j < board_dim.getY(); j++) {
                layer[i][j] = null;
            }
        }
        this.clean();
    }
}
