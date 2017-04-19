
public class Shape2D {
	//stores simple closed 2d shapes
	int numPoints=4;
	Pos2D[] vertices={new Pos2D(1,1),new Pos2D(1,-1),new Pos2D(-1,-1),new Pos2D(-1,1)};
	
	public Shape2D(){
		
	}
	public Shape2D(int numSides, Pos2D[] vertexList){
		numPoints=numSides;
		vertices=vertexList;
	}
	public Shape2D(Pos2D[] vertexList){
		numPoints=vertexList.length;
		vertices=vertexList;
	}
}
