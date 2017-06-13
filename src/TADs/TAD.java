package TADs;

/**
 * Interface per a definir una taula de hash generica.
 * 
 * @author Professors de l'assignatura 16-17
 *
 */
public interface TAD<K,V> {
	/**
	 * Afegeix un element a la taula de hash
	 * @param k - clau de l'element a afegir
	 * @param v - element a afegir
	 */
	public void afegir(K k,V v);
	
	/**
	 * Esborra un element a la taula de hash
	 * @param k - clau de l'element a esborrar
	 */	
	public V esborrar(K k);
	
	/**
	 * Consulta un element a la taula de hash
	 * @param k - clau de l'element a consultar
	 */	
	public V consultar(K k);
}
