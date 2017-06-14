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
				eD=new TaulaHash<String, Valors>(1000); break;
			case 2:
				eD=new Arbre<String, Valors>(); break;
			default: break;
		}
		return eD;
	}
	
	
	/**
	 * Metode que tractara les dades amb el metode triat
	 * @param nomFitxer	nom del fitxer d'on es llegirant les dades, amb aquest nom es creara el fitxer on guardar les dades
	 * @param cuaClau cua que conte la clau
	 * @param signe indica si s'ha de sumar(xifrar) o restar(desxifrar)
	 */
	public static void tractarDades(String nomFitxer, TAD<String, Valors> eD) {
		
		try {
			//Variables
			BufferedReader f=new BufferedReader(new FileReader(nomFitxer+".txt"));
			BufferedWriter g=new BufferedWriter(new FileWriter(nomFitxer+"_Index.txt"));
			String frase, paraula, paraula2, plana="<Plana numero=X>";
			Character a, b='$';
			Integer numPlana=1;
			int numLinies=1;
			
			//Llegim fitxer i carreguem index en el TAD
			frase=f.readLine();
			while(frase!=null){ 
				a=frase.charAt(14);
				plana.replace('X', a);
				if(plana.equals(frase)){	//Nova Plana
					numPlana=(int) (a)-48;
					numLinies=0;
				}
				else{
					StringTokenizer st = new StringTokenizer(frase, " ");
					while(st.hasMoreTokens()){
						paraula=st.nextToken();
						a=frase.charAt(0);
						paraula2=paraula.substring(1);			//Eliminem el caracter '$'
						paraula2=paraula2.toLowerCase();		//Evitem les mayuscules pasen a minuscula
						//paraula repetitiva
						if((a.equals(b))&&(eD.consultar(paraula2)==null)){
							Valors aux = new Valors(50,50);
							eD.afegir(paraula2, aux);
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
				frase = f.readLine();
				numLinies++;
			}
			
			//Creem fitxer Index a partir del TAD
			if(frase!=null) g.newLine();
			else g.write(String.valueOf('a'));
			
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
	 * Metode que comprova que la clau sigui correcta
	 * @param teclat variable de tipus Scanner
	 * @return clau retorna la clau
	 */
	public static String clauCorrecta(Scanner teclat) {
		int i=0;
		String clau;
		boolean isOk=true, nonum=true;
		
		System.out.println("Indica la clau:");
		clau=teclat.nextLine();
		while(isOk){
			while((nonum) && (i<clau.length())){
				if(Character.isDigit(clau.charAt(i))) i++;
				else nonum=false;				
			}
			if((nonum==false) && (i>0)){
				System.out.println("La clau que has introduit te algun caràcter incorrecte. Indica la clau:");
				clau=teclat.nextLine();
				isOk=true;
			}
			else isOk=false;
		}
		return clau;
	}
	/**
	 * 	/-Main-/
	 * @param args args
	 */
	public static void main(String[] args) {
		Scanner teclat=new Scanner(System.in);
		TAD<String, Valors> eD=null;
		String nomFitxer;
		int opcio;
		long tempsi, tempsf;
		
		//Tipus de implementació
		opcio=tipusImplementacio(teclat);
		
		//Nom fitxer
		nomFitxer=nomCorrecte(teclat);
		
		//Operacions
		tempsi=System.nanoTime();
		eD=implementacio(opcio, eD);
		tractarDades(nomFitxer, eD);
		tempsf=System.nanoTime();
		
		System.out.println("Les Dades s'han tractat correctament.\n");
		System.out.println("El programa ha tardat: "+(tempsf-tempsi)+"ns.");
		
		teclat.close();
	}
}
