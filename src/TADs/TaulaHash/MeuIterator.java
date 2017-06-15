package TADs.TaulaHash;

import java.util.Iterator;

public class MeuIterator<K, V> implements Iterator<MeuIterator<K, V>> {
	private TaulaHash<K, V> llista;	//nou atribut que ens guardarà una copia de la llista actual de punts
	private int posicioIterator;
	
	public MeuIterator(TaulaHash<K, V> ll) {
		llista=new TaulaHash<K, V>(ll.getNumElem());
		for (int i=0; i<ll.getNumElem(); i++) {
			llista.afegir(ll.consultarIessim(i).getKey(),ll.consultarIessim(i).getValue());
		}
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	
	@Override
	public boolean hasNext() {
		return ((posicioIterator<llista.getNumElem()));
	}

	public MeuIterator<K, V> next() {
		@SuppressWarnings("unchecked")
		NodeHash<K, V> aux= llista.consultarIessim(posicioIterator);
		posicioIterator++;
		return aux;
	}

}
