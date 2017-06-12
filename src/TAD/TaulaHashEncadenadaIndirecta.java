package TAD;

public class TaulaHashEncadenadaIndirecta<K, V> implements TADTaulaHashGenerica<K, V> {

	private NodeHash<K, V>[] taulaLlistes;
	private int numElem, capacitat;
	
	public TaulaHashEncadenadaIndirecta(int capacitat) {
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
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public float getFactorDeCarrega() {
		return ((float) numElem/capacitat);
	}

	@Override
	public void mostrarTaula() {
		for (int i=0; i<capacitat; i++) {
			System.out.println(i+": ");
		}
		
	}
}
