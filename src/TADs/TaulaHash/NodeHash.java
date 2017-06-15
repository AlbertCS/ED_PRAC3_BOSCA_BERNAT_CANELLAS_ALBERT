package TADs.TaulaHash;

public class NodeHash<K, V> {
	
	private K key;
	private V value;
	private NodeHash<K , V> seguent;
	
	public NodeHash(K key, V value, NodeHash<K , V> seguent) {
		this.key=key;
		this.value=value;
		this.seguent=seguent;
	}
	
	public NodeHash<K, V> clone2 (NodeHash<K, V> node){
		NodeHash<K, V> nuevo=null;
		if(node!=null) nuevo=new NodeHash<K, V>(node.getKey(),node.getValue(),this.clone2(node.getSeguent()));
		return nuevo;
	}
	
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public NodeHash<K, V> getSeguent() {
		return seguent;
	}

	public void setSeguent(NodeHash<K, V> seguent) {
		this.seguent = seguent;
	}
}
