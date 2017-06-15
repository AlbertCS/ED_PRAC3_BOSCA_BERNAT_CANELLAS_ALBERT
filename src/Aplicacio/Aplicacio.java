package Aplicacio;

import java.io.*;
import java.util.*;

import TADs.*;
import TADs.TaulaHash.*;
import TADs.Arbre.*;

/**
 * Class per testejar el programa
 * @author Albert Cañellas Sole
 * @author Bernat Bosca Candel
 *
 */


public class Aplicacio {
	
	/**
	 * Metode que mostra un menu per indicar el tipus d'implementacio.
	 * @param teclat variable de tipus Scanner
	 * @return	opcio escollida
	 */
	public static int tipusImplementacio(Scanner teclat) {
		boolean opcioOk=true;
		String op;
		int opcio=0;
		
		System.out.println("Indica tipus d'estructura de dades vols utilitzar:\n\t1. Taula Hash\n\t2. Arbre");
		op=teclat.nextLine();
		if(Character.isDigit(op.charAt(0))) opcio=Integer.parseInt(op);
		
		while(opcioOk){
			switch(opcio){
				case 1: 
					opcioOk=false; break;
				case 2: 
					opcioOk=false; break;
				default:
					System.out.println("Valor incorrecte. Indica tipus d'estructura de dades vols utilitzar:\n\t1. Taula Hash\n\t2. Arbre");
					op=teclat.nextLine();
					if(Character.isDigit(op.charAt(0))) opcio=Integer.parseInt(op); break;
			}
		}
		return opcio;
	}
	
	/**
	 * Metode que crea el tipus de cua corresponent
	 * @param opcio implementacio escollida
	 * @param mida dimensio de la cua
	 * @param cua	la cua implementada
	 * @param clau	la clau amb la que tractar el missatge
	 * @return retorna la cua corresponent
	 */
	public static TAD<String, Valors> implementacio(int opcio, TAD<String, Valors> eD) {
		switch(opcio){
			case 1:
				eD=new TaulaHash<String, Valors>(676); break;	//26*26
			case 2:
				eD=new Arbre<String, Valors>(); break;
			default: break;
		}
		return eD;
	}
	
	/**
	 * Función que elimina acentos y caracteres especiales de
	 * una cadena de texto.
	 * @param input
	 * @return cadena de texto limpia de acentos y caracteres especiales.
	 */
	public static String remove(String input) {
	    // Cadena de caracteres original a sustituir.
	    String original = "áàäéèëíìïóòöúùuñç";
	    // Cadena de caracteres ASCII que reemplazarán los originales.
	    String ascii = "aaaeeeiiiooouuunc";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }//for i
	    return output;
	}	
	
	/**
	 * Metode que elimina un fragment d'una paraula que no necessitem per al index
	 * 
	 * @param paraula
	 * @return paraula sense la part que contenia un punt, guió, aporstrof, etc.
	 */
	public static String solsParaula(String paraula) {
		int suprimir=-1;
		char a='a';
		
		for (int i=0; i<paraula.length(); i++) {
	        a=paraula.charAt(i);
	        if(!Character.isAlphabetic(a)){
	        	suprimir=paraula.indexOf(a);
	        	if(suprimir!=-1){
	        		if(suprimir>(paraula.length()/2)){
		        		paraula=paraula.substring(0, suprimir);	//Eliminem tot lo posterior al caracter
		        	}
		        	else {
		        		paraula=paraula.substring(suprimir+1);		//Eliminem tot lo anterior al caracter
		        	}
	        	}
	        }
	    }
	    return paraula;
	}
	
	/**
	 * Metode que tractara les dades amb el metode triat
	 * @param nomFitxer	nom del fitxer d'on es llegirant les dades, amb aquest nom es creara el fitxer on guardar les dades
	 * @param cuaClau cua que conte la clau
	 * @param signe indica si s'ha de sumar(xifrar) o restar(desxifrar)
	 */
	public static void tractarDades(String nomFitxer, TAD<String, Valors> eD, int opcio) {
		
		try {
			//Variables
			BufferedReader f=new BufferedReader(new FileReader(nomFitxer+".txt"));
			BufferedWriter g=new BufferedWriter(new FileWriter(nomFitxer+"_Index.txt"));
			
			//Llegim fitxer i carreguem index en el TAD
			//Variables
			String frase, paraula, plana="<Plana numero";
			Character a='a';
			Integer numPlana=1;
			int numLinies=1;
			frase=f.readLine();
			while(frase!=null){ 
				if(frase.length()>14){
					a=frase.charAt(14);
					plana=frase.substring(0, 13);
				} 
				else plana=frase.substring(0, frase.length());
				if(plana.equals("<Plana numero")){	//Nova Plana
					numPlana=(int) (a)-48;
					numLinies=1;
				}
				else{
					StringTokenizer st = new StringTokenizer(frase, " ");
					while(st.hasMoreTokens()){
						paraula=st.nextToken();
						a=paraula.charAt(0);
						if(a.equals('$')) paraula=paraula.substring(1);			//Eliminem el caracter '$'
						paraula=paraula.toLowerCase();			//Evitem les mayuscules, pasen a minuscula
						paraula=remove(paraula);				//Eliminem accents i caracters especials
						paraula=solsParaula(paraula);			//Eliminem un fragment inecessari d'una paraula
						//paraula repetitiva
						if(Character.isAlphabetic(paraula.charAt(0))){
							if((a.equals('$'))&&(eD.consultar(paraula)==null)){
								Valors aux = new Valors(50,50);
								eD.afegir(paraula, aux);
								aux.novaPlana(numPlana);
								aux.novaLinia(numLinies);
							}
							else{
								Valors aux2 = eD.consultar(paraula);
								//paraula repetitiva que ja existia
								if(aux2!=null){
									int ultimaPlana=aux2.getUltimaPlana();
									int ultimaLinia=aux2.getUltimaLinia();
									if((ultimaLinia!=numLinies)||(ultimaPlana!=numPlana)){
										aux2.novaPlana(numPlana);
										aux2.novaLinia(numLinies);
									}
								}
								//sino no la tractem perquè no és repetitiva o perque es trobarepetida en la mateixa plana i linia
							}
						}
					}
					numLinies++;
				}
				frase = f.readLine();				
			}
			
			//Creem fitxer Index a partir del TAD
			//Variables
			StringBuffer txt = new StringBuffer();
			NodeHash<String, Valors> hashNode=null;
			
			switch(opcio){
			case 1:
				TaulaHash<String, Valors> hashTaula=(TaulaHash<String, Valors>) eD;
				IteratorHash<String, Valors> iterator=new IteratorHash <String, Valors>(hashTaula);
				
				for(int i=0;i<(hashTaula.getCapacitat());i++){
					hashNode=iterator.next2();
					if(hashNode!=null){
						if(hashNode.getSeguent()==null){
							txt.append(hashNode.getKey()+hashNode.getValue().toString2());
							g.write(txt.toString());
							g.newLine();
							txt.delete(0, txt.length());
						}
						else{
							@SuppressWarnings("unchecked")
							NodeHash<String, Valors>[] ordenarPosicions= new NodeHash[20];
							NodeHash<String, Valors> guarda=null;
							int w=1;
							ordenarPosicions[0]=hashNode;
							while(hashNode.getSeguent()!=null){
								ordenarPosicions[w]=hashNode.getSeguent();
								w++;
								hashNode=hashNode.getSeguent();
							}
							for(int z=0;z<w;z++) {
					             for(int x=0;x<w-z;x++) {
					                 if(ordenarPosicions[x+1]!=null){
					                	 String pos1=ordenarPosicions[x].getKey();
					                	 String pos2=ordenarPosicions[x+1].getKey();
					                	 if (pos1.compareTo(pos2)>=0) {
					                		 guarda=ordenarPosicions[x];
					                     	ordenarPosicions[x]=ordenarPosicions[x+1];
					                     	ordenarPosicions[x+1]=guarda;
					                	 }
					                 }
					             }
					         }
							for(int h=0;h<w;h++){
								txt.append(ordenarPosicions[h].getKey()+ordenarPosicions[h].getValue().toString2());
								g.write(txt.toString());
								g.newLine();
								txt.delete(0, txt.length());
							}
						}
					}
				}
				break;
			case 2:
				//eD=new Arbre<String, Valors>(); break;
			default: break;
			}
			
			g.close();
			f.close();
		}catch (IOException e) {
			System.err.println("Error de tipus IOException.");
		}
	}
	
	/**
	 * Metode que comprova que el nom del fitxer sigui correcte
	 * @param teclat variable de tipus Scanner
	 * @return nomFitxer retorna el nom del teclat
	 */
	public static String nomCorrecte(Scanner teclat) {
		String nomFitxer;
		boolean isOk=true;
		
		System.out.println("Indica el nom del fitxer. Si no has creat cap, el nom que has de ficar és 'Text1'.");
		nomFitxer=teclat.nextLine();
		File nameFile = new File(nomFitxer+".txt");
		while(isOk){
			if(nameFile.isFile()) isOk=false;
			else {
				System.out.println("El fitxer amb el nom "+nomFitxer+" NO existeix. Indica un altre nom: ");
				nomFitxer=teclat.nextLine();
				nameFile = new File(nomFitxer+".txt");
			}
		}
		return nomFitxer;
	}
	
	/**
	 * 	/-Main-/
	 * @param args args
	 */
	public static void main(String[] args) {
		Scanner teclat=new Scanner(System.in);
		TAD<String, Valors> eD=null;
		String nomFitxer="Text4";
		int opcio=1;
		long tempsi, tempsf;
		
		//Tipus de implementació
	//	opcio=tipusImplementacio(teclat);
		
		//Nom fitxer
	//	nomFitxer=nomCorrecte(teclat);
		
		//Operacions
		tempsi=System.nanoTime();
		eD=implementacio(opcio, eD);
		tractarDades(nomFitxer, eD, opcio);
		tempsf=System.nanoTime();
		
		System.out.println("Les Dades s'han tractat correctament.\n");
		System.out.println("El programa ha tardat: "+(tempsf-tempsi)+"ns.");
		
		teclat.close();
	}
}
