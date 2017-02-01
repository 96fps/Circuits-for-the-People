
public class shape2d {
	//stores simple closed 2d shapes
	int numPoints=4;
	pos2d[] vertices={new pos2d(1,1),new pos2d(1,-1),new pos2d(-1,-1),new pos2d(-1,1)};
	
	public shape2d(){
		
	}
	public shape2d(int numSides, pos2d[] vertexList){
		numPoints=numSides;
		vertices=vertexList;
	}
	public shape2d(pos2d[] vertexList){
		numPoints=vertexList.length;
		vertices=vertexList;
	}
}
