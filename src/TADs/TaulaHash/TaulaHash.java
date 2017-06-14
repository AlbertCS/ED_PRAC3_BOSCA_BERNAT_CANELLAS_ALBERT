package TADs.TaulaHash;

import TADs.TAD;

public class TaulaHash<K, V> implements TAD<K, V> {

	private NodeHash<K, V>[] taulaLlistes;
	private int numElem, capacitat;
	
	@SuppressWarnings("unchecked")
	public TaulaHash(int capacitat) {
		taulaLlistes=new NodeHash[capacitat];
		numElem=0;
		this.capacitat=capacitat;
	}
	
	@Override
	public void afegir(K k, V v) {
		int clauHash=k.hashCode()%capacitat;
		if(taulaLlistes[clauHash]==null) {
			taulaLlistes[clauHash]=new NodeHash<K, V>(k, v, null);
			numElem++;
		}
		else{
			NodeHash<K, V> aux=taulaLlistes[clauHash];
			while((aux.getSeguent()!=null) && (aux.getKey()!=k)) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (aux.getKey()!=k)){
				aux.setSeguent(new NodeHash<K, V>(k, v, null));
				this.numElem++;
			}
			else aux.setValue(v);
		}
	}

	@Override
	public V esborrar(K k) {
		int clauHash=k.hashCode()%capacitat;
		if(taulaLlistes[clauHash]==null) {
			return null;
		}
		else{
			NodeHash<K, V> aux=taulaLlistes[clauHash];
			while((aux.getSeguent()!=null) && (aux.getKey()!=k)) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (aux.getKey()!=k)){
				return null;
			}
			else return (aux.getValue());
		}
	}

	@Override
	public V consultar(K k) {
		int clauHash=k.hashCode()%capacitat;
		if(taulaLlistes[clauHash]==null) {
			return null;
		}
		else{
			NodeHash<K, V> aux=taulaLlistes[clauHash];
			while((aux.getSeguent()!=null) && (aux.getKey()!=k)) aux=aux.getSeguent();
			if((aux.getSeguent()==null) && (aux.getKey()!=k)) return null;
			else return aux.getValue();
		}
	}
	
	private int hashCode(K k){
		String aux=(String) k;
		int code=0;
		for(int i=0;i<aux.length();i++){
			
		}
		return code;
	}
}
