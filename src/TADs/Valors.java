package TADs;

public class Valors {
	
	private int[] planes;
	private int numPlanes;
	private int[] linies;
	private int numLinies;
	
	public Valors (int dimPlanes, int dimLinies) {
		planes=new int[dimPlanes];
		linies=new int[dimLinies];
		numPlanes=0;
		numLinies=0;
	}
	
	public void novaPlana (int valor) {
		planes[numPlanes]=valor;
		numPlanes++;
	}
	
	public void novaLinia (int valor) {
		linies[numLinies]=valor;
		numLinies++;
	}
	
	public int getUltimaPlana() {
		if(numPlanes!=0) return planes[numPlanes-1];
		else return -1;
	}

	public int getUltimaLinia() {
		if(numLinies!=0) return linies[numLinies-1];
		else return -1;
	}
	
	public int[] getPlanes() {
		return planes;
	}

	public void setPlanes(int[] planes) {
		this.planes = planes;
	}

	public int[] getLinies() {
		return linies;
	}

	public void setLinies(int[] linies) {
		this.linies = linies;
	}

	public int getNumPlanes() {
		return numPlanes;
	}

	public void setNumPlanes(int numPlanes) {
		this.numPlanes = numPlanes;
	}

	public int getNumLinies() {
		return numLinies;
	}

	public void setNumLinies(int numLinies) {
		this.numLinies = numLinies;
	}
}