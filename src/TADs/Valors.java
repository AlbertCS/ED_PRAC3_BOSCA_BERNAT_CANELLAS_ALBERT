package TADs;

public class Valors {
	
	private int[] planes;
	private int numPlanes;
	private int[] linies;
	private int numLinies;
	
	/**
	 * Constructor de la classe
	 * @param dimPlanes dimensio maxima de la taula de planes
	 * @param dimLinies dimensio maxima de la taula de linies
	 */
	public Valors (int dimPlanes, int dimLinies) {
		planes=new int[dimPlanes];
		linies=new int[dimLinies];
		numPlanes=0;
		numLinies=0;
	}
	
	/**
	 * Afegir una nova plana
	 * @param valor indicador de la plana corresponent
	 */
	public void novaPlana (int valor) {
		planes[numPlanes]=valor;
		numPlanes++;
	}
	
	/**
	 * Afegir una nova linea
	 * @param valor indicador de la linia corresponent
	 */
	public void novaLinia (int valor) {
		linies[numLinies]=valor;
		numLinies++;
	}
	
	/**
	 * Retorna la ultima plana
	 * @return retorna la ultima plana
	 */
	public int getUltimaPlana() {
		if(numPlanes!=0) return planes[numPlanes-1];
		else return -1;
	}

	/**
	 * Getter de la ultima linia
	 * @return retorna la ultima linia
	 */
	public int getUltimaLinia() {
		if(numLinies!=0) return linies[numLinies-1];
		else return -1;
	}
	
	/**
	 * Metode que retorna el vector de planes
	 * @return  retorna el vector de planes
	 */
	public int[] getPlanes() {
		return planes;
	}

	/**
	 * Setter del vector de planes
	 * @param planes vector de planes
	 */
	public void setPlanes(int[] planes) {
		this.planes = planes;
	}

	/**
	 * Getter del vector de linies
	 * @return linies vector de linies
	 */
	public int[] getLinies() {
		return linies;
	}

	/**
	 * Setter del vector de linies
	 * @param linies vector de linies
	 */
	public void setLinies(int[] linies) {
		this.linies = linies;
	}

	/**
	 * Getter del numero de planes
	 * @return numPlanes el numero de planes
	 */
	public int getNumPlanes() {
		return numPlanes;
	}
	/**
	 * Setter del num de planes
	 * @param numPlanes el numero de planes
	 */
	public void setNumPlanes(int numPlanes) {
		this.numPlanes = numPlanes;
	}

	/**
	 * Getter del num de linies
	 * @return num de linies
	 */
	public int getNumLinies() {
		return numLinies;
	}

	/**
	 * Setter de numero de linies
	 * @param numLinies
	 */
	public void setNumLinies(int numLinies) {
		this.numLinies = numLinies;
	}

	/**
	 * Metode toString
	 * @return txt String a imprimir
	 */
	public StringBuffer toString2() {
		StringBuffer txt = new StringBuffer();
		int i=0;
		
		for(i=0;i<numLinies-1;i++){
			txt.append(" "+planes[i]+":"+linies[i]+",");
		}
		txt.append(" "+planes[i]+":"+linies[i]);
		return txt;
	}
}