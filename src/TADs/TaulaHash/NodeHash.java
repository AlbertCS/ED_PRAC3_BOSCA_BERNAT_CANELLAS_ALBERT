package TADs.TaulaHash;

/**
 * Classe NodeHash
 * @param <K>
 * @param <V>
 */
public class NodeHash<K, V> {
	
	private K key;
	private V value;
	private NodeHash<K , V> seguent;
	
	/**
	 * Constructor del node Hash
	 * @param key la clau
	 * @param value el valor
	 * @param seguent el seguent node
	 */
	public NodeHash(K key, V value, NodeHash<K , V> seguent) {
		this.key=key;
		this.value=value;
		this.seguent=seguent;
	}
	
	/**
	 * Metode que crea un clon del node
	 * @param node node a copiar
	 * @return nuevo el nou node
	 */
	public NodeHash<K, V> clone2 (NodeHash<K, V> node){
		NodeHash<K, V> nuevo=null;
		if(node!=null) nuevo=new NodeHash<K, V>(node.getKey(),node.getValue(),this.clone2(node.getSeguent()));
		return nuevo;
	}
	
	/**
	 * Getter de la clau
	 * @return la clau
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Setter de la clau
	 * @param key la clau
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * Getter del valor
	 * @return value el valor
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Setter del valor
	 * @param value el valor
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * Metode que retorna el seguent node
	 * @return seguent el seguent node
	 */
	public NodeHash<K, V> getSeguent() {
		return seguent;
	}

	/**
	 * Setter del seguent node
	 * @param seguent el seguent node
	 */
	public void setSeguent(NodeHash<K, V> seguent) {
		this.seguent = seguent;
	}
}
