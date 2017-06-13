package TADs;

public class Valors {
	
	private int[] planes;
	private int numPlanes;
	private int[] repeticions;
	private int numRepeticions;
	
	public Valors (int dimPlanes, int dimRepeticions) {
		planes=new int[dimPlanes];
		repeticions=new int[dimRepeticions];
		numPlanes=0;
		numRepeticions=0;
	}
	
	public void novaPlana (int valor) {
		planes[numPlanes]=valor;
		numPlanes++;
		numRepeticions=1;
	}
	
	public void novaRepeticio () {
		repeticions[numPlanes]=numRepeticions;
		numRepeticions++;
	}

	public int getUltimaPlana() {
		return planes[numPlanes-1];
	}
	
	public int[] getPlanes() {
		return planes;
	}

	public void setPlanes(int[] planes) {
		this.planes = planes;
	}

	public int[] getRepeticions() {
		return repeticions;
	}

	public void setRepeticions(int[] repeticions) {
		this.repeticions = repeticions;
	}

	public int getNumPlanes() {
		return numPlanes;
	}

	public void setNumPlanes(int numPlanes) {
		this.numPlanes = numPlanes;
	}

	public int getNumRepeticions() {
		return numRepeticions;
	}

	public void setNumRepeticions(int numRepeticions) {
		this.numRepeticions = numRepeticions;
	}
}