package TADs.TaulaHash;

/**
 * Classe iterator
 * @param <K>
 * @param <V>
 */
public class IteratorHash<K, V> {
	private TaulaHash<K, V> llista;	//nou atribut que ens guardarà una copia de la llista actual de punts
	private int posicioIterator;
	
	/**
	 * Constructor del iterator del Hash
	 * @param ll la taula de hash 
	 */
	public IteratorHash(TaulaHash<K, V> ll) {
		llista=new TaulaHash<K, V>(ll.getCapacitat());
		for (int i=0; i<ll.getCapacitat(); i++) {
			if(ll.consultarIessim(i)!=null) llista.afegir2(ll.consultarIessim(i).clone2(ll.consultarIessim(i)), i);
		}
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	
	/**
	 * Metode que mira si hi ha mes elemnts
	 * @return true si queden elements sino false
	 */
	public boolean hasNext2() {
		return ((posicioIterator<llista.getNumElem()));
	}

	/**
	 * Metode que retorna el seguent element
	 * @return aux el seguent element
	 */
	public NodeHash<K,V> next2() {
		NodeHash<K, V> aux= llista.consultarIessim(posicioIterator);
		posicioIterator++;
		return aux;
	}

}
