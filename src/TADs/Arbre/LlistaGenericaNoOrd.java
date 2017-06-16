package TADs.Arbre;
/**
 * Llista genèrica no ordenada
 * 
 * @author Professors de l'assignatura 16-17
 *
 */
import java.util.*;

/**
 * Classe LListaGenericaNoOrd
 * @param <T>
 */
public class LlistaGenericaNoOrd<T> implements Iterable<T> {
	private T[] llista;
	private int num;
	
	/**
	 * Constructor de la llista generica no ordenada
	 * @param dim dimensio de la llista
	 */
	@SuppressWarnings("unchecked")
	public LlistaGenericaNoOrd(int dim) {
		llista=(T[])new Object[dim];
		num=0;
	}

	/**
	 * Metode que afegeix un element
	 * @param p element a afegir
	 */
	@SuppressWarnings("unchecked")
	public void afegirElement(T p) {
		if (num>=llista.length) {
			// amplio
			T[] nova=(T[]) new Object[llista.length*2];
			for (int i=0; i<llista.length; i++)
				nova[i]=llista[i];
			llista=nova;
		}
		// segur que tinc espai
		llista[num]=p;
		num++;
	}
	
	/**
	 * Metode que copia els elements
	 * @param ll llista a copiar
	 */
	public void afegirElement(LlistaGenericaNoOrd<T> ll) {
		int numElems=ll.getNum();
		for (int i=0; i<numElems; i++) {
			afegirElement(ll.consultarIessim(i));
		}
	}
	
	/**
	 * Metode que consulta en la posicio que es vol
	 * @param i posicio a consultar
	 * @return retorna el element en la posicio demanada, null en cas de no trobar-ne
	 */
	public T consultarIessim(int i) {
		if (i<num) return(llista[i]);
		else return(null);
	}
	
	/**
	 * Getter de num
	 * @return num numero d'elements
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Metode toString
	 * @return retorna el string de la llista
	 */
	@Override
	public String toString() {
		return "LlistaPunts [llista=" + Arrays.toString(llista) + ", num=" + num + "]";
	}
	
	/**
	 * Metode que fa el iterator
	 * @return pI el iterator
	 */
	@Override
	public Iterator<T> iterator() {
		MeuIterator<T> pI=new MeuIterator<T>(this);
		return pI;
	}
}
