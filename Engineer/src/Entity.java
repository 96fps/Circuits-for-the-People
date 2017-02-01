import java.awt.Color;


public class Entity {
	pos2d location= new   pos2d();
	shape2d shape=  new shape2d();
	double rotationDeg=Math.random()*360;
	Color color= new Color(255,255,255);
	
	public Entity(){
		
	}
	public Entity(pos2d position, shape2d entityShape, double rotation, Color c){
		location=position; shape=entityShape; rotationDeg=rotation; color=c;
	}
	public Entity(pos2d position, shape2d entityShape){
		location=position; shape=entityShape;
	}
	
}
