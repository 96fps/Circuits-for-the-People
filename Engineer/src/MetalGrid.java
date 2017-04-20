
public class MetalGrid {

    Pos2D board_dim; //44 x 27

    public boolean[][] layer;
    public boolean[][] horiz;
    public boolean[][] verti;

    public MetalGrid() {
        this(new Pos2D(44, 27));
    }

    public MetalGrid(Pos2D dim) {
        board_dim = dim;
        layer = new boolean[dim.getX()][dim.getY()];
        horiz = new boolean[dim.getX()][dim.getY()];
        verti = new boolean[dim.getX()][dim.getY()];


        for (int i = 0; i < dim.getX(); i++) {
            for (int j = 0; j < dim.getY(); j++) {
                if (Math.random() > 0.01) layer[i][j] = true;

            }
        }

        for (int i = 0; i < dim.getX(); i++) {
            for (int j = 0; j < dim.getY() - 1; j++) {
                if (layer[i][j] && layer[i][j + 1])
                    if (Math.random() > 0.8)
                        verti[i][j] = true;

            }
        }
        for (int i = 0; i < dim.getX() - 1; i++) {
            for (int j = 0; j < dim.getY(); j++) {
                if (layer[i][j] && layer[i + 1][j])
                    if (Math.random() > 0.15)
                        horiz[i][j] = true;

            }
        }
    }

    public void clean() {
        for (int i = 0; i < board_dim.getX(); i++) {
            for (int j = 0; j < board_dim.getY() - 1; j++) {
                if (!layer[i][j] || !layer[i][j + 1])
                    verti[i][j] = false;

            }
        }
        for (int i = 0; i < board_dim.getX() - 1; i++) {
            for (int j = 0; j < board_dim.getY(); j++) {
                if (!layer[i][j] || !layer[i + 1][j])
                    horiz[i][j] = false;

            }
        }
    }

    public void stdTemplate() {

        //clean edges
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < board_dim.getY(); j++) {
                layer[i][j] = false;
            }
        }
        for (int i = board_dim.getX() - 4; i < board_dim.getX(); i++) {
            for (int j = 0; j < board_dim.getY(); j++) {
                layer[i][j] = false;
            }
        }
        //place pads
        //left
        for (int k = 0; k < 6; k++)
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    layer[i + 1][j + 2 + 4 * k] = true;
                    verti[i + 1][j + 2 + 4 * k] = true;
                    horiz[i][j + 2 + 4 * k] = true;
                }
        //right
        for (int k = 0; k < 6; k++)
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    layer[board_dim.getX() - 4 + i][j + 2 + 4 * k] = true;
                    verti[board_dim.getX() - 4 + i][j + 2 + 4 * k] = true;
                    horiz[board_dim.getX() - 4 + i][j + 2 + 4 * k] = true;
                }
        //</pads>
        this.clean();
    }
}
