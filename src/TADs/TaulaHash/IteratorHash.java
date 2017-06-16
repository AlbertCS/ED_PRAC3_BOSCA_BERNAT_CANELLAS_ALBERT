package TADs.TaulaHash;

public class IteratorHash<K, V> {
	private TaulaHash<K, V> llista;	//nou atribut que ens guardarà una copia de la llista actual de punts
	private int posicioIterator;
	
	public IteratorHash(TaulaHash<K, V> ll) {
		llista=new TaulaHash<K, V>(ll.getCapacitat());
		for (int i=0; i<ll.getCapacitat(); i++) {
			if(ll.consultarIessim(i)!=null) llista.afegir2(ll.consultarIessim(i).clone2(ll.consultarIessim(i)), i);
		}
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	
	public boolean hasNext2() {
		return ((posicioIterator<llista.getNumElem()));
	}

	public NodeHash<K,V> next2() {
		NodeHash<K, V> aux= llista.consultarIessim(posicioIterator);
		posicioIterator++;
		return aux;
	}

}
