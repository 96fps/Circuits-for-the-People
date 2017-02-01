import java.util.ArrayList;


public class Controlset {
	ArrayList<Boolean> buttons=new ArrayList<Boolean>();
	ArrayList<Double> doubles=new ArrayList<Double>();
	ArrayList<Integer> ints=new ArrayList<Integer>();
	
	public Controlset(){
	
	}
	public Controlset(ArrayList<Boolean> b, ArrayList<Double> d, ArrayList<Integer> i){
		buttons=b;
		doubles=d;
		ints=i;
	}
	public Controlset(ArrayList<Boolean> b){
		buttons=b;
	}
}
