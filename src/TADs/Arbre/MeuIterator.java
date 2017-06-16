package TADs.Arbre;

import java.util.Iterator;

/**
 * Classe MeuIterator
 * @param <T>
 */
public class MeuIterator<T> implements Iterator<T> {
	private LlistaGenericaNoOrd<T> llista;	//nou atribut que ens guardarà una copia de la llista actual de punts
	private int posicioIterator;
	
	/**
	 * Contructor del iterator
	 * @param ll la llista del que es fara el iterator
	 */
	public MeuIterator(LlistaGenericaNoOrd<T> ll) {
		llista=new LlistaGenericaNoOrd<T>(ll.getNum());
		for (int i=0; i<ll.getNum(); i++) {
			llista.afegirElement(ll.consultarIessim(i));
		}
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	
	/**
	 * Metode que consulta si hi ha mes elements
	 * @return true si hi han mes elements sino false
	 */
	@Override
	public boolean hasNext() {
		return ((posicioIterator<llista.getNum()));
	}

	/**
	 * Metode que retorna el seguent element
	 * @return aux l'element seguent
	 */
	@Override
	public T next() {
		T aux=llista.consultarIessim(posicioIterator);
		posicioIterator++;
		return aux;
	}

}
