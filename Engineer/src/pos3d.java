import java.awt.Color;

public class pos3d {
	public double x;
	public double y;
	public double z;
	
	public pos3d(){
		x=0;
		y=0;
		z=0;
	}
	public pos3d(double posX, double posY, double posZ){
		x=posX;
		y=posY;
		z=posZ;
	}
	public void add(double posX, double posY, double posZ){
		x+=posX;
		y+=posY;
		z+=posZ;
	}
	public void add(pos3d pos){
		x+=pos.x;
		y+=pos.y;
		z+=pos.z;
	}public static pos3d add(pos3d a, pos3d b){
		pos3d c= new pos3d();
		c.x=a.x+b.x;
		c.y=a.y+b.y;
		c.z=a.z+b.z;
		return c;
	}
	public pos3d mult(pos3d pos){
		return new pos3d(x*pos.x,
						 y*pos.y,
						 z*pos.z);
	}public pos3d mult(double n){
		return new pos3d(x*n,
				 		 y*n,
				 		 z*n);
	}
	public static pos3d mult(pos3d a, pos3d pos){
		return new pos3d(a.x*pos.x,
				 a.y*pos.y,
				 a.z*pos.z);
	}public static pos3d mult(pos3d a,double n){
		return new pos3d(a.x*n,
		 		 a.y*n,
		 		 a.z*n);
}
	public static pos3d negate(pos3d pos){
		return new pos3d(-pos.x, -pos.y, -pos.z);
	}
	public static Color toColor(pos3d in){
		Color newcolor = new Color((int)(255*in.x+256)%256,(int)(255*in.y+256)%256,(int)(255*in.z+256)%256);
		return newcolor;
	}
	
}
