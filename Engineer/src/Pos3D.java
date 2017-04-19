import java.awt.Color;

public class Pos3D {
    public double x;
    public double y;
    public double z;

    public Pos3D() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Pos3D(double posX, double posY, double posZ) {
        x = posX;
        y = posY;
        z = posZ;
    }

    public void add(double posX, double posY, double posZ) {
        x += posX;
        y += posY;
        z += posZ;
    }

    public void add(Pos3D pos) {
        x += pos.x;
        y += pos.y;
        z += pos.z;
    }

    public static Pos3D add(Pos3D a, Pos3D b) {
        Pos3D c = new Pos3D();
        c.x = a.x + b.x;
        c.y = a.y + b.y;
        c.z = a.z + b.z;
        return c;
    }

    public Pos3D mult(Pos3D pos) {
        return new Pos3D(x * pos.x,
                y * pos.y,
                z * pos.z);
    }

    public Pos3D mult(double n) {
        return new Pos3D(x * n,
                y * n,
                z * n);
    }

    public static Pos3D mult(Pos3D a, Pos3D pos) {
        return new Pos3D(a.x * pos.x,
                a.y * pos.y,
                a.z * pos.z);
    }

    public static Pos3D midpoint(Pos3D a, Pos3D b) {
        if (a != null && b != null) {
            return new Pos3D(a.x * 0.5 + b.x * 0.5,
                    a.y * 0.5 + b.y * 0.5,
                    a.z * 0.5 + b.z * 0.5);
        } else
            return new Pos3D(0, 0, 0);
    }

    public static Pos3D mult(Pos3D a, double n) {
        return new Pos3D(a.x * n,
                a.y * n,
                a.z * n);
    }

    public static Pos3D negate(Pos3D pos) {
        return new Pos3D(-pos.x, -pos.y, -pos.z);
    }

    public static Color toColor(Pos3D in) {
        Color newcolor =
                new Color((int) (255 * in.x + 256) % 256,
                        (int) (255 * in.y + 256) % 256,
                        (int) (255 * in.z + 256) % 256);
        return newcolor;
    }

}
