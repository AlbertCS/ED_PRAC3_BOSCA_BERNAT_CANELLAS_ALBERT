package TADs.Arbre;

/**
 * Interface per a definir l'arbre binari
 * 
 * @author Professors de l'assignatura 16-17
 *
 */
public interface TAD_ABC<K, V> {
	
	public void inserir( K k, V v);
	
	public K arrel();
	
	public void esborrar( K k);
	
	public boolean existeix( K k);
	
	public V buscarElement(K k);
	
	public int numNodes();
	
	public int alcada();
	
	public K maxim();
	
	public K minim();
	
	public K predecessor(K k);
	
	public K successor(K k);
	
	public TAD_ABC<K, V> fillEsq();
	
	public TAD_ABC<K, V> fillDret();
	
	public boolean esBuit();
	
	public LlistaGenericaNoOrd<K> preordre();
	
	public LlistaGenericaNoOrd<K> inordre();
	
	public LlistaGenericaNoOrd<K> postordre();

	public TAD_ABC<K, V> clone();

}
