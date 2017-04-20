import java.awt.Graphics;

public interface Scene {

    void doLogicTick(double itter);

    void render(Graphics g, Pos2D gameRes);
}
